package com.example.useri.myapplication4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {
    private Button btn,btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        btn = (Button) findViewById(R.id.button);
        btn1 = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main4Activity.this,Main5Activity.class);
                startActivity(i);
            }
        });
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
