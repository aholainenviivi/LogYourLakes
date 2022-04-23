package com.example.logyourlakes;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyJSONReader {

    public static final MyJSONReader jsonReader = new MyJSONReader();
    protected JSONArray jsonArray;
    protected static Context context;

    private MyJSONReader() { }

    public static MyJSONReader getInstance() {
        return jsonReader;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected String getJSON() {
        String json = null;
        try {
            File file = new File(context.getFilesDir() + "/myJSONFile.json");
            FileReader inputStream = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();
            inputStream.close();
            bufferedReader.close();
            System.out.println(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("File read. Got json string:" + json);
        }
        return json;
    }

    protected JSONArray getJSONArray() {
        String json = getJSON();
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                jsonArray = jsonObject.getJSONArray("activities");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    protected ArrayList<Activity> getListActivityObjects() {
        JSONArray jsonArray = getJSONArray();
        ArrayList<Activity> listActivities = new ArrayList<>();
        if (jsonArray != null) {
            try {
                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    listActivities.add(new Activity(jsonObject.getString("activityType"),
                            jsonObject.getString("timeSpent"),
                            jsonObject.getString("date"),
                            jsonObject.getString("lakeName")));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return listActivities;
    }

}
