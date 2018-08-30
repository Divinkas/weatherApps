package com.example.divinkas.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import com.example.divinkas.weatherapp.Model.PlacesModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private final static int LAYOUT_NAME = R.layout.activity_maps;
    private final static int TYPE_THEME = R.style.AppTheme;
    private final static int TITLE = R.string.map;

    SupportMapFragment mapFragment;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(TITLE);
        setTheme(TYPE_THEME);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_NAME);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void init(){
        map.setOnMapClickListener((LatLng latLng) -> {
            map.clear();
            map.addMarker(new MarkerOptions().position(latLng));
            new AlertDialog.Builder(this)
                    .setTitle("Add to places?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        PlacesModel placesModel = new PlacesModel(this);
                        placesModel.AddPlace(latLng.latitude, latLng.longitude);
                        Intent intent = new Intent(this, PlacesActivity.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> map.clear())
                    .show();
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (map!=null){ init(); }
    }

}
