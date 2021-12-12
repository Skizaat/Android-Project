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
import java.util.Random;

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



    //methode pour créer l'url de la requete
    private URL buildURL(String... strings) throws MalformedURLException {
        String urlString = "https://ghibliapi.herokuapp.com/" + strings[0] + "?";

        //construction d'url pour la recherche de people
        if (strings[0].equals("people")){
            urlString += "hair_color=" + strings[1];
            if (strings.length>2) {
                if (!strings[2].equals("Other")) {
                    urlString += "&gender=" + strings[2];
                }
                if (strings.length>3) {
                    urlString += "&eye_color=" + strings[3];
                }
            }
        }

        //construction d'url pour la recherche de location
        if (strings[0].equals("locations")){
            urlString += "terrain=" + strings[1];
            if (strings.length>1) {
                if (strings[2].equals("false")) {
                    urlString += "&climate=Continental";
                }
            }
        }

        //construction d'url pour la recherche de sidekick
        if (strings[0].equals("species")){
            if (strings[1].equals(false)){
                urlString += "classification=Mammal";
            }
            if (strings.length>2) {
                if (strings[2].equals("White")){
                    urlString += "&hair_colors=" + strings[2];
                }
            }
            if (strings.length>3) {
                if (strings[2].equals("Red")){
                    urlString += "&eye_colors=" + strings[2];
                }
            }
        }

        //construction d'url pour la recherche de vehicle
        if (strings[0].equals("vehicles")){
            if (strings[1].equals("false")){
                urlString += "vehicle_class=Boat";
            }
        }
        Log.i("JFL", urlString);
        URL url = new URL(urlString);
        return url;
    }



    private JSONArray getJSON(URL url) {

        //connection
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = readStream(in);
                //Log.i("JFL", "connection ok");
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //Log.i("JFL", "connection nok");
        } catch (IOException e) {
            e.printStackTrace();
            //Log.i("JFL", "connection nok");
        }

        //récupération du résultat
        JSONArray json = null;
        try {
            json = new JSONArray(result);
            //Log.i("JFL", "json ok");
        } catch (JSONException e) {
            e.printStackTrace();
            //Log.i("JFL", "json nok");
        }
        return json;


    }

    @Override
    protected JSONArray doInBackground(String... strings) {

        //déclaration des variables
        int stringslengh = strings.length;
        int lenghtresult = 0;
        URL url = null;
        JSONArray resultjson = null;

        //on obtient une prelière fois le résultat
        try {
            url = buildURL(strings);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        resultjson = getJSON(url);

        //si il n'y a pas de résultat, on enlève un argument et on recommence jusqu'à ce qu'il y ait au moins un résultat
        lenghtresult = resultjson.length();
        while (lenghtresult<=0) {
            String[] newargs = new String[stringslengh - 1];
            for (int i=0; i<stringslengh-1; i++){
                newargs[i] = strings[i];
            }
            stringslengh -= 1;

            try {
                url = buildURL(strings);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            resultjson = getJSON(url);
            lenghtresult = resultjson.length();
        }

        return resultjson;
    }

    protected void onPostExecute(JSONArray result) {

        int rand = 0;
        //si il y a plus d'un résultat, on en choisit un au hasard
        if (result.length() != 1){
            Random r = new Random();
            rand = r.nextInt(result.length());
        }

        //on affiche le résultat voulu au bon endroit
        String peoplename = "";
        String locationname = "";
        String sidename = "";
        String vehiclename = "";
        String test ="";

        //pour people
        try {
            test = result.getJSONObject(rand).getString("age");
            peoplename = result.getJSONObject(rand).getString("name");

            TextView peopleresult = (TextView) resultActivity.findViewById(R.id.peopleResult);
            peopleresult.setText(peoplename);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //pour location
        try {
            test = result.getJSONObject(rand).getString("climate");
            locationname = result.getJSONObject(rand).getString("name");

            TextView locationresult = (TextView) resultActivity.findViewById(R.id.locationResult);
            locationresult.setText(locationname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //pour sidekick
        try {
            test = result.getJSONObject(rand).getString("classification");
            sidename = result.getJSONObject(rand).getString("name");

            TextView sideresult = (TextView) resultActivity.findViewById(R.id.sideResult);
            sideresult.setText(sidename);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //pour vehicle
        try {
            test = result.getJSONObject(rand).getString("vehicle_class");
            vehiclename = result.getJSONObject(rand).getString("name");

            TextView vehicleresult = (TextView) resultActivity.findViewById(R.id.vehicleResult);
            vehicleresult.setText(vehiclename);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
