package com.example.divinkas.weatherapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.divinkas.weatherapp.Adapters.PlacesAdapter;
import com.example.divinkas.weatherapp.Data.ItemTempPlaces;
import com.example.divinkas.weatherapp.Model.PlacesModel;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

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
        loadPlaces();
        super.onResume();
    }

    @SuppressLint("CheckResult")
    private void loadPlaces(){
        PlacesModel placesModel = new PlacesModel(this);
        List<ItemTempPlaces> lstt = placesModel.getListPlaces();

        PublishSubject<List<ItemTempPlaces>> subject;
        subject = PublishSubject.create();
        subject
                .map(lst ->new PlacesAdapter(this, lst))
                .subscribe(rvContainer::setAdapter);

        subject.onNext(lstt);
        //rvContainer.setAdapter(new PlacesAdapter(this, placesModel.getListPlaces()));

    }

}
