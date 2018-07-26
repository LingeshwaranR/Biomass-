package com.example.useri.myapplication4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class farmerdb extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    ProgressDialog progressDialog;
    private Button btn;
    private EditText inputName,inputMnum,inputAnum;
    Spinner spinner1,spinner;
    String[] SPINNERVALUES = {"Tomato","Potato","Onion","carrot","Beans","Spinach"};
    String[] SPINNER1VALUES = {"Madurai","Chennai","Udaipur","Bikaner","jaipur","Jhodpur"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmerdb);

        inputName = (EditText) findViewById(R.id.editText_name);
        inputMnum=(EditText)findViewById(R.id.mnum);
        inputAnum=findViewById(R.id.anum);
        btn=(Button)findViewById(R.id.button);
        spinner =(Spinner) findViewById(R.id.spinner1);
        spinner1=(Spinner)findViewById(R.id.spinner2);
        spinner.setPrompt("Crops");
        spinner1.setPrompt("Cities");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(farmerdb.this, android.R.layout.simple_list_item_checked, SPINNERVALUES);
        spinner.setAdapter(adapter);

        //Adding setOnItemSelectedListener method on spinner.
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                spinner.getPopupBackground().setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(farmerdb.this, android.R.layout.simple_list_item_checked, SPINNER1VALUES);
        spinner1.setAdapter(adapter1);

        //Adding setOnItemSelectedListener method on spinner.
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                spinner1.getPopupBackground().setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mFirebaseInstance =FirebaseDatabase.getInstance();
                mFirebaseDatabase = mFirebaseInstance.getReference("Farmers");



                String name = inputName.getText().toString();
                String location= spinner1.getSelectedItem().toString();
                String crops= spinner.getSelectedItem().toString();
                String mnum=inputMnum.getText().toString();
                String anum=inputAnum.getText().toString();
                String dp ="nul";

                progressDialog = new ProgressDialog( farmerdb.this,R.style.AppCompatAlertDialogStyle);
                progressDialog.setMessage("Submitting Details..."); // Setting Message
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

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if (TextUtils.isEmpty(location)) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Enter location!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(crops)) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Enter crops!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mnum)) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Enter Mobile Number!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(anum)) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Enter Mobile Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!anum.equals("1234123412341234")) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Enter valid Aadhaar Number", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(farmerdb.this, BlockLogin.class));


                    return;
                }
                userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                User user = new User(name,location,crops,mnum,dp);
                mFirebaseDatabase.child(userId).setValue(user);

                progressDialog.dismiss();
                startActivity(new Intent(farmerdb.this, Main3Activity.class));

            }
        });

    }
}