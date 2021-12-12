package com.example.myghibli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DataBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);


        ListView list = (ListView)findViewById(R.id.displayedresults);
        ArrayAdapter<String> array = new ArrayAdapter<String>(list.getContext(), R.layout.support_simple_spinner_dropdown_item);

        FeedReaderDbHelper mydb = new FeedReaderDbHelper(this);
        mydb.printData(array);
        list.setAdapter(array);
    }
}