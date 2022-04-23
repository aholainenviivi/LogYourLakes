package com.example.logyourlakes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class JSONReader {

    private static final JSONReader jsonReader = new JSONReader();
    protected ArrayList<Lake> listLakeObjects;

    private JSONReader() {
        listLakeObjects = getListLakeObjects();
    }

    public static JSONReader getInstance() {
        return jsonReader;
    }

    protected String getJSON() {
        String json = null;
        try {
            URL url = new URL("https://rajapinnat.ymparisto.fi/api/jarvirajapinta/1.0/odata/Jarvi");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    protected JSONArray getJSONArray() {
        String json = getJSON();
        JSONArray jsonArray = null;
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                jsonArray = jsonObject.getJSONArray("value");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    protected void setListLakeObjects() {
        ArrayList<Lake> listLakes = new ArrayList<>();
        JSONArray jsonArray = getJSONArray();
        if (jsonArray != null) {
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    listLakes.add(new Lake(jsonObject.getString("Nimi"),
                            jsonObject.getString("KuntaNimi"),
                            jsonObject.getString("SyvyysSuurin"),
                            jsonObject.getString("SyvyysKeski"),
                            jsonObject.getString("Rantaviiva"),
                            jsonObject.getString("VesalNimi"),
                            jsonObject.getString("KoordErLat") + "N, " +
                                    jsonObject.getString("KoordErLong") + "E",
                            jsonObject.getString("SyvyysSuurinKoordErLat") + " N, "
                                    + jsonObject.getString("SyvyysSuurinKoordErLong") + " E"));
                }
                this.listLakeObjects = listLakes;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected ArrayList<Lake> getListLakeObjects() {
        if (listLakeObjects == null) {
            setListLakeObjects();
        }
        return listLakeObjects;
    }

}
