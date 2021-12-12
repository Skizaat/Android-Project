package com.example.myghibli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Array;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //récupère les informations rentrées dans l'activité précédente
        Intent intent = getIntent();
        String[] datas = intent.getStringArrayExtra("Datas");


        AsyncGhibliJSONData peopletask = new AsyncGhibliJSONData(this);
        peopletask.execute("people", datas[3], datas[2], datas[4]);

        AsyncGhibliJSONData locationtask = new AsyncGhibliJSONData(this);
        locationtask.execute("locations", datas[6], datas[5]);

        AsyncGhibliJSONData sidetask = new AsyncGhibliJSONData(this);
        sidetask.execute("species", datas[7], datas[8], datas[8]);

        AsyncGhibliJSONData vehicletask = new AsyncGhibliJSONData(this);
        vehicletask.execute("vehicles", datas[9]);
    }
}