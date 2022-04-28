package com.example.logyourlakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchLakesActivity extends AppCompatActivity {

    JSONReader jsonReader = JSONReader.getInstance();
    ArrayList<String> listSearches = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lakes);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SearchView searchView = findViewById(R.id.searchView);
        ListView listView = findViewById(R.id.listView);

        // Creating an ArrayList to shown as search results
        if (listSearches.size() == 0) {
            ArrayList<Lake> listLakes = jsonReader.getListLakeObjects();
            for (int i=0; i<listLakes.size(); i++) {
                Lake lake = listLakes.get(i);
                listSearches.add(lake.getName() + "   (" + lake.getMunicipality() + ")");
            }
        }
        //Creating the SearchView and ListView and using the created ArrayList
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listSearches);
        listView.setAdapter(arrayAdapter);
        ArrayList<String> finalListSearches = listSearches;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (finalListSearches.contains(s)) {
                    arrayAdapter.getFilter().filter(s);
                } else {
                    //Toast.makeText(SearchLakesActivity.this, "No results for your search",
                    //        Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            int actualPosition = finalListSearches.indexOf(arrayAdapter.getItem(i));
            openLakeInfoActivity(actualPosition);
        });

    }

    // Method starts LakeInfoActivity and sends position of clicked search result
    public void openLakeInfoActivity(int position) {
        Intent intent = new Intent(this, LakeInfoActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}