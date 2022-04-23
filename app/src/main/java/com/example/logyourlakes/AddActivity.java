package com.example.logyourlakes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener {

    Spinner activitySpinner;
    Button pickDateButton;
    EditText timeSpentEditText;
    Button confirmButton;

    String category;
    int year, month, day;
    int myYear, myMonth, myDay;
    String date;
    float timeSpent;
    String lakeName;
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        context = getApplicationContext();

        // Getting extras from the activity calling this one
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            lakeName = bundle.getString("lakeName");
        }

        // Spinner for picking activity type
        activitySpinner = findViewById(R.id.activitySpinner);
        activitySpinner.setOnItemSelectedListener(this);
        ArrayList<String> activityCategories = new ArrayList<>();
        activityCategories.add("Choose activity");
        activityCategories.add("Swimming");
        activityCategories.add("SUP paddling");
        activityCategories.add("Canoeing");
        activityCategories.add("Rowing");
        activityCategories.add("Ice skating");
        activityCategories.add("Other");
        ArrayAdapter<String> activitiesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, activityCategories);
        activitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(activitiesAdapter);

        // Date picker for setting date of activity
        date = "";
        pickDateButton = findViewById(R.id.pickDateButton);
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this,
                        AddActivity.this, year, month, day);
                datePickerDialog.show();
            }
        });

        // Edit text for setting time spent on activity
        timeSpentEditText = findViewById(R.id.timeSpentEditText);
        timeSpentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                timeSpent = Float.parseFloat(timeSpentEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        // Confirm button
        confirmButton = findViewById(R.id.addConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyJSONWriter jsonWriter = MyJSONWriter.getInstance();
                jsonWriter.writeJSON(context, lakeName, category, date, timeSpent);
                startYourLakesActivity();
            }
        });

    }

    // Date picker method
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        this.myYear = year;
        this.myMonth = month;
        this.myDay = day;
        date = day + "." + month + "." + year;
    }

    // Activity spinner methods
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            category = "";
        } else {
            this.category = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    // Start new activity method
    public void startYourLakesActivity() {
        Intent intent = new Intent(this, YourLakesActivity.class);
        startActivity(intent);
    }

}