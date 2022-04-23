package com.example.logyourlakes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class LakeInfoActivity extends AppCompatActivity {

    JSONReader jsonReader = JSONReader.getInstance();
    TextView lakeName, municipality, centerCoordinates, averageDepth, deepestDepth, deepestCoordinates,
            waterSystem, shorelineLength;
    TextView totalOfActivities;
    Button addActivityButton;
    String lakeNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lake_info);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Retrieving position of chosen card/search result in lists created
        Bundle bundle = getIntent().getExtras();
        int i = 0;
        if (bundle != null) {
            i = bundle.getInt("position");
        }

        // Program gets required lists from JSONReader instance and gets needed attributes with position retrieved
        ArrayList<Lake> listLakes = jsonReader.getListLakeObjects();
        Lake lake = listLakes.get(i);
        lakeNameString = lake.getName();

        lakeName = findViewById(R.id.lakeNameInfo);
        lakeName.setText(lake.getName());

        municipality = findViewById(R.id.municipalityInfo);
        municipality.setText("In municipality: " + lake.getMunicipality());

        centerCoordinates = findViewById(R.id.centerCoordinatesInfo);
        centerCoordinates.setText("At coordinates: " + lake.getCenterCoordinates());

        averageDepth = findViewById(R.id.averageDepthInfo);
        averageDepth.setText("Average depth: " + lake.getAverageDepth());

        deepestDepth = findViewById(R.id.deepestDepthInfo);
        deepestDepth.setText("Deepest depth: " + lake.getDeepestDepth());

        deepestCoordinates = findViewById(R.id.deepestDepthCoordinates);
        deepestCoordinates.setText("At coordinates: " + lake.getDeepestCoordinates());

        waterSystem = findViewById(R.id.waterSystemInfo);
        waterSystem.setText("Part of: " + lake.getWaterSystem());

        shorelineLength = findViewById(R.id.shorelineInfo);
        shorelineLength.setText("Shoreline length: " + lake.getShorelineLength() + "km");

        //Activity box
        //Button for expanding and minimizing "Your activities" box
        ImageButton arrow = findViewById(R.id.imageButton);
        ConstraintLayout hiddenView = findViewById(R.id.hiddenView);
        CardView cardView = findViewById(R.id.expandableCardView);

        hiddenView.setVisibility(View.GONE);
        arrow.setImageResource(com.android.car.ui.R.drawable.car_ui_recyclerview_ic_down);

        arrow.setOnClickListener(view -> {
            if (hiddenView.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.GONE);
                arrow.setImageResource(com.android.car.ui.R.drawable.car_ui_recyclerview_ic_down);
            } else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenView.setVisibility(View.VISIBLE);
                arrow.setImageResource(com.android.car.ui.R.drawable.car_ui_recyclerview_ic_up);
            }
        });

        // Button for adding new activity for current lake taking user to AddActivity
        addActivityButton = findViewById(R.id.addActivityButton);
        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddActivity();
            }
        });

        // Creating a recycler view of user's activities
        MyJSONReader jsonReader = MyJSONReader.getInstance();
        ArrayList<Activity> listActivities = jsonReader.getListActivityObjects();
        ArrayList<Activity> listSpecificActivities = new ArrayList<>();
        for (int j=0; j<listActivities.size(); j++) {
            Activity activity = listActivities.get(j);
            if (activity.getLakeName().equals(lakeNameString)) {
                listSpecificActivities.add(activity);
            }
        }

        // Creating layout manager and adapter for recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerViewLakeInfo);
        ActivityAdapter activityAdapter = new ActivityAdapter(this, listSpecificActivities);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(activityAdapter);

        // Summary of user's activities
        if (listSpecificActivities.size() != 0) {
            totalOfActivities = findViewById(R.id.totalOfActivitiesInfo);
            totalOfActivities.setText("You have total of " + listSpecificActivities.size() +
                    " activities at this lake.");
            TextView previousActivities = findViewById(R.id.previousActivitiesText);
            previousActivities.setText("Previous activities:");
        }
    }

    public void startAddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("lakeName", lakeNameString);
        startActivity(intent);
    }
}