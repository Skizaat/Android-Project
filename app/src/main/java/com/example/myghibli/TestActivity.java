package com.example.myghibli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    int clicknb =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FragmentManager fm = getSupportFragmentManager();

        Fragment informFragment = fm.findFragmentById(R.id.informFragment);
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .hide(informFragment)
                .commit();

        Fragment locateFragment = fm.findFragmentById(R.id.locateFragment);
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .hide(locateFragment)
                .commit();

        Fragment sideFragment = fm.findFragmentById(R.id.sideFragment);
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .hide(sideFragment)
                .commit();

        Fragment vehicleFragment = fm.findFragmentById(R.id.vehicleFragment);
        fm.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .hide(vehicleFragment)
                .commit();


        Button peopleBtn = (Button) findViewById(R.id.peoplebutton);
        peopleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(locateFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(sideFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(vehicleFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .show(informFragment)
                        .commit();

            }
        });

        Button locationBtn = (Button) findViewById(R.id.locationbutton);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(sideFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(informFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(vehicleFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .show(locateFragment)
                        .commit();
            }
        });

        Button SidekickBtn = (Button) findViewById(R.id.sidekickbutton);
        SidekickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(locateFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(informFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(vehicleFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .show(sideFragment)
                        .commit();
            }
        });

        Button VehicleBtn = (Button) findViewById(R.id.vehiclebutton);
        VehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(locateFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(informFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(sideFragment)
                        .commit();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .show(vehicleFragment)
                        .commit();
            }
        });

        Button resultsBtn = (Button) findViewById(R.id.seeresultsbutton);
        resultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicknb == 0){
                    Toast.makeText(getApplicationContext(), "Are you sure you answered to all questions ?",
                            Toast.LENGTH_LONG).show();
                    clicknb+=1;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Results are coming ...",
                            Toast.LENGTH_LONG).show();

                    //creating a list to stock all datas from the user
                    String[] userdata = new String[11];

                    //puting values in the array

                    //name edit view, if null do not send results
                    EditText nameedittext = (EditText) findViewById(R.id.nameeditView);
                    String name = String.valueOf(nameedittext.getText());
                    if (name != ""){
                        userdata[0] = name;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please enter your name",
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    //age edit view, if null do not send results
                    EditText ageeditext = (EditText) findViewById(R.id.ageeditView);
                    String age = String.valueOf(ageeditext.getText());
                    if (age != ""){
                        userdata[1] = age;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please enter your age",
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    //genre spinner
                    Spinner genrespn = (Spinner) findViewById(R.id.genderspinner);
                    String genre = genrespn.getSelectedItem().toString();
                    userdata[2] = genre;

                    //hair color spinner
                    Spinner hairspn = (Spinner) findViewById(R.id.haircolorspinner);
                    String haircolor = hairspn.getSelectedItem().toString();
                    userdata[3] = haircolor;

                    //eye color spinner
                    Spinner eyespn = (Spinner) findViewById(R.id.eyecolorspinner);
                    String eyecolor = eyespn.getSelectedItem().toString();
                    userdata[4] = eyecolor;

                    //rain information
                    Switch rainswt = (Switch) findViewById(R.id.switch1);
                    userdata[5] = String.valueOf(rainswt.isChecked());

                    //terrain information
                    Spinner terrspn = (Spinner) findViewById(R.id.terrainspinner);
                    String terrain = terrspn.getSelectedItem().toString();
                    userdata[6] = terrain;

                    //Magic information
                    CheckBox magicckb = (CheckBox) findViewById(R.id.checkBox);
                    userdata[7] = String.valueOf(magicckb.isChecked());

                    //fav color information
                    Spinner favspn = (Spinner) findViewById(R.id.colorspinner);
                    String color = favspn.getSelectedItem().toString();
                    userdata[8] = color;

                    //flying information
                    Switch flyswt = (Switch) findViewById(R.id.switch2);
                    userdata[9] = String.valueOf(flyswt.isChecked());

                    //bacon-speculos information
                    Switch bsswt = (Switch) findViewById(R.id.switch3);
                    userdata[10] = String.valueOf(bsswt.isChecked());


                    Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                    intent.putExtra("Datas", userdata);
                    startActivity(intent);
                }



            }
        });

    }
}