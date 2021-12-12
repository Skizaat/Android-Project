package com.example.myghibli;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncGhibliJSONData extends AsyncTask<String, Void, JSONArray> {

    private AppCompatActivity resultActivity;

    public AsyncGhibliJSONData(AppCompatActivity activity) {
        resultActivity = activity;
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = readStream(in);
                Log.i("JFL", "connection ok");
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            Log.i("JFL", "connection nok");
        } catch (IOException e) {
            //e.printStackTrace();
            Log.i("JFL", "connection nok");
        }

        JSONArray json = null;
        try {
            json = new JSONArray(result);
            Log.i("JFL", "json ok");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("JFL", "json nok");
        }
        return json;
    }

    protected void onPostExecute(JSONArray result) {
        String name = "";
        Log.i("JFL", "in post ex");
        try {
            name = result.getJSONObject(0).getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView nameresult = (TextView) resultActivity.findViewById(R.id.textView5);
        nameresult.setText(name);
    }
}
