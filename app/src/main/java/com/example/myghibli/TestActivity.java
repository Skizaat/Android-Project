package com.example.myghibli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FragmentManager fm = getSupportFragmentManager();

        Button peopleBtn = (Button) findViewById(R.id.peoplebutton);
        peopleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment informFragment = fm.findFragmentById(R.id.informFragment);
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .show(informFragment)
                        .commit();
            }
        });

        Fragment informFragment = fm.findFragmentById(R.id.informFragment);
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .hide(informFragment)
                .commit();
    }


}