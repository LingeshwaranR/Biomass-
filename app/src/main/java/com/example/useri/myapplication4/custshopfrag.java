package com.example.useri.myapplication4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link marketplaceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link marketplaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class custshopfrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView story;
    private DatabaseReference database;

    public custshopfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment marketplaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static marketplaceFragment newInstance(String param1, String param2) {
        marketplaceFragment fragment = new marketplaceFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_custshopfrag, container, false);
        database= FirebaseDatabase.getInstance().getReference().child("Posts");
        story=(RecyclerView)view.findViewById(R.id.recycle);
        story.setHasFixedSize(true);
        story.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<blog,BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<blog, BlogViewHolder>(

                blog.class,
                R.layout.story_activity,
                BlogViewHolder.class,
                database
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, blog model, int position) {

                viewHolder.setItem(model.getItem());
                viewHolder.setprice(model.getPrice());
                viewHolder.setImage(getContext(),model.getImage());
            }
        };
        story.setAdapter(firebaseRecyclerAdapter);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View nView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            nView=itemView;
            CardView cv= nView.findViewById(R.id.cvv);
            Context context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent;
                    intent =  new Intent(context, Main6Activity.class);
                    context.startActivity(intent);
            }
            });

        }
        public void setItem(String item){
            TextView post_item=(TextView)nView.findViewById(R.id.storyitem);
            post_item.setText(item);
        }
        public void setprice(String price){
            TextView post_price=(TextView)nView.findViewById(R.id.storyprice);
            post_price.setText(price);
        }
        public void setImage(Context ctx,String image){
            ImageView post_image=(ImageView)nView.findViewById(R.id.storyimg);
            Picasso.with(ctx).load(image).into(post_image);
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Toast.makeText(context,"Marketplace",Toast.LENGTH_LONG).show();
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
