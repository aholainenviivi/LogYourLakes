package com.example.logyourlakes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BrowseLakesActivity extends AppCompatActivity {

    private RecyclerView lakeRecyclerView;
    FloatingActionButton backToTopButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_lakes);
        lakeRecyclerView = findViewById(R.id.lakeRecyclerView);

        // Getting list of Lake objects, creating a layout manager and adapter and setting
        // those up for the RecyclerView
        JSONReader jsonReader = JSONReader.getInstance();
        ArrayList<Lake> listLakes =  jsonReader.getListLakeObjects();
        LakeAdapter lakeAdapter = new LakeAdapter(this, listLakes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        lakeRecyclerView.setLayoutManager(linearLayoutManager);
        lakeRecyclerView.setAdapter(lakeAdapter);

        // FloatingActionButton that scrolls the view back to top
        backToTopButton = findViewById(R.id.backToTopButton);
        backToTopButton.setTooltipText("Back to top");
        backToTopButton.setOnClickListener(view -> {
            lakeRecyclerView.smoothScrollToPosition(0);
            //lakeRecyclerView.scrollToPosition(0);
        });
    }

}