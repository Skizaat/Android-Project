package com.example.myghibli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Button clear = (Button)findViewById(R.id.clearbutton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedReaderDbHelper db = new FeedReaderDbHelper(v.getContext());
                db.deleteSaves();
                list.setAdapter(new ArrayAdapter<String>(list.getContext(), R.layout.support_simple_spinner_dropdown_item));
            }
        });
    }
}