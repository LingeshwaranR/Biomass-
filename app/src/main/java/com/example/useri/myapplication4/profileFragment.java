package com.example.useri.myapplication4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.InputStream;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link profileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */

public class profileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private OnFragmentInteractionListener mListener;
    ProgressDialog progressDialog;
    private static final int GALLERY_REQUEST=1;
    private ImageButton image;
    private  Button set,future;
    private DatabaseReference databaseusers;
    private FirebaseDatabase ref;
    private StorageReference storageimage;
    Bitmap Imagebitmap;
    String dp1;


    private Uri imageUri=null,resultUri=null;
    Activity a;
    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {




        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Button btn = (Button) view.findViewById(R.id.check);
        Button future = (Button) view.findViewById(R.id.fut);

        TextView locat=(TextView)view.findViewById(R.id.loc);
        TextView names=(TextView)view.findViewById(R.id.nm);
        TextView mnumber=(TextView)view.findViewById(R.id.num);
        TextView crop=(TextView)view.findViewById(R.id.item);

        image=(ImageButton)view.findViewById(R.id.img);
        set=view.findViewById(R.id.set1);
        String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageimage= FirebaseStorage.getInstance().getReference().child("Dp");
        databaseusers= FirebaseDatabase.getInstance().getReference().child("Farmers");


        auth=FirebaseAuth.getInstance();

        future.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity().getApplication(), futurepredict.class);
                startActivity(in);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                progressDialog = new ProgressDialog(getActivity(),R.style.AppCompatAlertDialogStyle);
                progressDialog.setMessage("Logged Out..."); // Setting Message
                progressDialog.setTitle(""); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null) {

                    // user auth state is changed - user is null
                    // launch login activity
                    progressDialog.dismiss();
                    Intent intent = new Intent(getActivity().getApplication(), splashscreen.class);
                    startActivity(intent);
                }

//                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
//                startActivity(intent);
                else{
                    Toast.makeText(getActivity().getApplication(), getString(R.string.auth_failed), Toast.LENGTH_LONG).show();

                }}

        });




        {





            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Farmers").child(user_id);
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String mnum = dataSnapshot.child("mnum").getValue(String.class);
                    String location = dataSnapshot.child("location").getValue(String.class);
                    String crops = dataSnapshot.child("crops").getValue(String.class);
                    String dp=dataSnapshot.child("dp").getValue(String.class);

                    locat.setText(location);
                    names.setText(name);
                    crop.setText(crops);
                    mnumber.setText(mnum);
                    new ImageLoaderClass().execute(dp);

                    try{
                        if(dp.equals("nul"))
                        {

                            image.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent gallery=new Intent(Intent.ACTION_GET_CONTENT);
                                    gallery.setType("image/*");
                                    startActivityForResult(gallery,GALLERY_REQUEST);
                                }
                            });
                            set.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    StartSetupAccount();
                                    progressDialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
                                    progressDialog.setMessage("Setting Dp..."); // Setting Message
                                    progressDialog.setTitle(""); // Setting Title
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                                    progressDialog.show(); // Display Progress Dialog
                                    progressDialog.setCancelable(false);


                                    new Thread(new Runnable() {
                                        public void run() {
                                            try {


                                                Thread.sleep(7000);
                                                progressDialog.dismiss();
                                                startActivity(new Intent(getActivity(), Main3Activity.class));

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }).start();



                                }
                            });
                        }



                    }
                    catch (Exception e){
                        startActivity(new Intent(getActivity(), MainActivity.class));

                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            reference.addListenerForSingleValueEvent(eventListener);
        }

        return view;
    }

    private void StartSetupAccount() {

        if(imageUri!=null)
        {
            StorageReference filepath = storageimage.child(imageUri.getLastPathSegment());
            String userid=auth.getCurrentUser().getUid();
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloaduri=taskSnapshot.getDownloadUrl();


                    databaseusers.child(userid).child("dp").setValue(downloaduri.toString());
                }

            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK);
        imageUri=data.getData();
        image.setImageURI(imageUri);

    }
    private class ImageLoaderClass extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                Imagebitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return Imagebitmap;
        }

        protected void onPostExecute(Bitmap image1) {

            if(image1 != null){
                image.setImageBitmap(image1);

            }
        }
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}