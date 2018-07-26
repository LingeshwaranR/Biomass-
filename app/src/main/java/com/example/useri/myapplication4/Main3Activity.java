package com.example.useri.myapplication4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentManager fragmetManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmetManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content,new homefragment()).commit();
                    return true;

                case R.id.profile:
                    transaction.replace(R.id.content,new profileFragment()).commit();
                    return true;
                case R.id.marketplace:

                    transaction.replace(R.id.content,new marketplaceFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        android.support.v4.app.FragmentManager fragmetManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmetManager.beginTransaction();
        transaction.replace(R.id.content,new homefragment()).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
