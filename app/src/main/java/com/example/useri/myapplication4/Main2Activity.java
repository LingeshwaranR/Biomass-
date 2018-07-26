package com.example.useri.myapplication4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class Main2Activity extends AppCompatActivity {
    private Button btn;
    private FirebaseAuth auth;
    private EditText inputEmail, inputPassword,inputName;
    ProgressDialog progressDialog;

    private String userId;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        auth = FirebaseAuth.getInstance();
        inputEmail = (EditText) findViewById(R.id.editText_emailAddress);
        inputPassword = (EditText) findViewById(R.id.password);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance();


                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();





                progressDialog = new ProgressDialog(Main2Activity.this,R.style.AppCompatAlertDialogStyle);
                progressDialog.setMessage("Signing in..."); // Setting Message
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



                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Main2Activity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Main2Activity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    progressDialog.dismiss();
                                    startActivity(new Intent(Main2Activity.this, farmerdb.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
}
