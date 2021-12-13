package com.example.myghibli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        Button save = (Button)findViewById(R.id.savebutton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String people = ((TextView)findViewById(R.id.peopleResult)).getText().toString();
                String location = ((TextView)findViewById(R.id.locationResult)).getText().toString();
                String sidekick = ((TextView)findViewById(R.id.sideResult)).getText().toString();
                String vehicle = ((TextView)findViewById(R.id.vehicleResult)).getText().toString();

                FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(v.getContext());
                dbHelper.insertData(people, location, sidekick, vehicle);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "You already saved your results",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Button databasebutton = (Button) findViewById(R.id.seesavedbutton);
        databasebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, DataBaseActivity.class);
                startActivity(intent);
            }
        });

        Button restartbutton = (Button) findViewById(R.id.restartbutton);
        restartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}