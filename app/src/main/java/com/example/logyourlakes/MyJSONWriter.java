package com.example.logyourlakes;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MyJSONWriter {

    private static final MyJSONWriter jsonWriter = new MyJSONWriter();
    JSONArray jsonArray = new JSONArray();

    private MyJSONWriter() { }

    public static MyJSONWriter getInstance() {
        return jsonWriter;
    }

    protected void writeJSON(Context context, String lakeName, String activityType, String date,
                             float timeSpent) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("lakeName", lakeName);
            jsonObject.put("activityType", activityType);
            jsonObject.put("date", date);
            jsonObject.put("timeSpent", timeSpent + " h");
            jsonArray.put(jsonObject);
            JSONObject jsonObjectOfArray = new JSONObject();
            jsonObjectOfArray.put("activities", jsonArray);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("myJSONFile.json",
                    Context.MODE_PRIVATE));
            outputStreamWriter.write(jsonObjectOfArray.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();
            System.out.println(jsonObject.toString());
            System.out.println(jsonObjectOfArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("File written");
        }
    }

}
