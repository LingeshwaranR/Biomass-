package com.example.useri.myapplication4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        android.support.v4.app.FragmentManager fragmetManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmetManager.beginTransaction();
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.profile:
                    transaction.replace(R.id.content,new custprofilefrag()).commit();

                    return true;
                case R.id.marketplace:
                    transaction.replace(R.id.content,new custshopfrag()).commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        android.support.v4.app.FragmentManager fragmetManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmetManager.beginTransaction();
        transaction.replace(R.id.content,new custshopfrag()).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
