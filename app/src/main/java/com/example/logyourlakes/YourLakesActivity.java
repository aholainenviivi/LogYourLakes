package com.example.logyourlakes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class YourLakesActivity extends AppCompatActivity {

    private RecyclerView activitiesRecyclerView;
    FloatingActionButton backToTopButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_lakes);
        activitiesRecyclerView = findViewById(R.id.myActivitiesRecyclerView);
        MyJSONReader jsonReader = MyJSONReader.getInstance();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Creating a layout manager and adapter and using those to create a recycler view
        ArrayList<Activity> listActivities = jsonReader.getListActivityObjects();
        ActivityAdapter activityAdapter = new ActivityAdapter(this, listActivities);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        activitiesRecyclerView.setLayoutManager(linearLayoutManager);
        activitiesRecyclerView.setAdapter(activityAdapter);

        // Button taking user to top of the page
        backToTopButton = findViewById(R.id.backToTopButtonYourLakes);
        backToTopButton.setTooltipText("Back to top");
        backToTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitiesRecyclerView.smoothScrollToPosition(0);
            }
        });
    }
}