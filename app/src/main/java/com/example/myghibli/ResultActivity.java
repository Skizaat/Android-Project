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

        Intent intent = getIntent();
        String[] datas = intent.getStringArrayExtra("Datas");

        TextView nameuser = (TextView) findViewById(R.id.nameView);
        nameuser.setText(datas[0]);

        TextView ageuser = (TextView) findViewById(R.id.ageView);
        ageuser.setText(datas[1]);
    }
}