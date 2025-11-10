package com.example.currencyparserxml;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class DataLoader extends AsyncTask<String, Void, String> {

    public interface DataCallback { void onDataLoaded(String jsonData); }
    private final DataCallback callback;

    public DataLoader(DataCallback callback) { this.callback = callback; }

    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) result.append(line);
            br.close();
            return result.toString();
        } catch (Exception e) { return null; }
    }

    @Override
    protected void onPostExecute(String result) {
        if (callback != null && result != null) callback.onDataLoaded(result);
    }
}
