package com.example.logyourlakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button browseLakesButton, searchLakesButton, yourLakesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /* Calling the JSONReader class as the app launches optimizes the speed at which the app
         functions when in use. When app launches, the JSONReader will get info from the interface
         on the web and create ArrayList of Lake objects with the information retrieved. */
        JSONReader jsonReader = JSONReader.getInstance();
        MyJSONReader myJSONReader = MyJSONReader.getInstance();
        myJSONReader.setContext(getApplicationContext());

        browseLakesButton = findViewById(R.id.browseLakesButton);
        browseLakesButton.setOnClickListener(view -> openBrowseLakesActivity());

        searchLakesButton = findViewById(R.id.searchLakesButton);
        searchLakesButton.setOnClickListener(view -> openSearchLakesActivity());

        yourLakesButton = findViewById(R.id.yourLakesButton);
        yourLakesButton.setOnClickListener(view -> openYourLakesActivity());

    }

    public void openBrowseLakesActivity() {
        Intent intent = new Intent(this, BrowseLakesActivity.class);
        startActivity(intent);
    }

    public void openSearchLakesActivity() {
        Intent intent = new Intent(this, SearchLakesActivity.class);
        startActivity(intent);
    }

    public void openYourLakesActivity() {
        Intent intent = new Intent(this, YourLakesActivity.class);
        startActivity(intent);
    }

}