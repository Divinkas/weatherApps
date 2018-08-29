package com.example.divinkas.weatherapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.divinkas.weatherapp.Adapters.PlacesAdapter;
import com.example.divinkas.weatherapp.Data.ItemPlaces;

import java.util.ArrayList;
import java.util.List;

public class PlacesActivity extends AppCompatActivity {

    private final static int LAYOUT_NAME = R.layout.activity_places;
    private final static int TYPE_THEME = R.style.AppTheme;
    private final static int TITLE = R.string.place;
    FloatingActionButton fab;
    RecyclerView rvContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(TYPE_THEME);
        setTitle(TITLE);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_NAME);
        initComponents();
    }
    private void initComponents(){
        fab = findViewById(R.id.fab);
        fab.setOnClickListener((v) -> {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        });

        rvContainer = findViewById(R.id.rvPlaces);
        rvContainer.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
    }

    @Override
    protected void onResume() {
        initDefaultPlaces();
        super.onResume();
    }

    //load all added places
    private void initDefaultPlaces(){
        Toast.makeText(this, "load data", Toast.LENGTH_SHORT).show();

        List<ItemPlaces> lst = new ArrayList<>();
        for(int  i = 0; i<5; i++){
            ItemPlaces itemPlaces = new ItemPlaces();
            itemPlaces.setName("name " + i);
            lst.add(itemPlaces);
        }

        rvContainer.setAdapter(new PlacesAdapter(this, lst));

    }

}
