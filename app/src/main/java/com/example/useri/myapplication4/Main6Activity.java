package com.example.useri.myapplication4;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main6Activity extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private Button btn;
    private EditText inputName,inputMnum;
    Spinner spinner1,spinner;
    String[] SPINNERVALUES = {"Tomato","Potato","Onion","carrot","Beans","Spinach"};
    String[] SPINNER1VALUES = {"1kg","2kg","3kg","4kg","5kg","6kg","7kg","8kg","9kg","10kg"};






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        inputName = (EditText) findViewById(R.id.editText_name);
        inputMnum=(EditText)findViewById(R.id.mnum);
        btn=(Button)findViewById(R.id.button1);
        spinner =(Spinner) findViewById(R.id.spinner1);
        spinner1=(Spinner)findViewById(R.id.spinner2);
        spinner.setPrompt("Crops");
        spinner1.setPrompt("Cities");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main6Activity.this, android.R.layout.simple_list_item_checked, SPINNERVALUES);
        spinner.setAdapter(adapter);

        //Adding setOnItemSelectedListener method on spinner.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Main6Activity.this, android.R.layout.simple_list_item_checked, SPINNER1VALUES);
        spinner1.setAdapter(adapter1);

        //Adding setOnItemSelectedListener method on spinner.
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

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
                mFirebaseDatabase = mFirebaseInstance.getReference("Orders");



                String name = inputName.getText().toString();
                String Quantity= spinner1.getSelectedItem().toString();
                String crops= spinner.getSelectedItem().toString();
                String mnum=inputMnum.getText().toString();



                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();

                    return;
                }

                if (TextUtils.isEmpty(Quantity)) {

                    Toast.makeText(getApplicationContext(), "Enter Quantity!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(crops)) {

                    Toast.makeText(getApplicationContext(), "Enter crops!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mnum)) {

                    Toast.makeText(getApplicationContext(), "Enter Mobile Number!", Toast.LENGTH_SHORT).show();
                    return;
                }



                User1 user1 = new User1(name,crops,Quantity,mnum);
                mFirebaseDatabase.child("Crops").setValue(user1);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://emitrapay.000webhostapp.com"));
                startActivity(intent);
                Toast.makeText(Main6Activity.this, "order placed", Toast.LENGTH_SHORT).show();

                finish();



            }
        });

    }
}
