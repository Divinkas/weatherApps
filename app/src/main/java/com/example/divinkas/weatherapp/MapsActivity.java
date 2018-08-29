package com.example.divinkas.weatherapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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
    final String TAG = "myLogs";
    private static final double TARGET_LATITUDE = 17.893366;
    private static final double TARGET_LONGITUDE = 19.511868;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(TITLE);
        setTheme(TYPE_THEME);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_NAME);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init();
    }

    private void init(){

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

    }
}
