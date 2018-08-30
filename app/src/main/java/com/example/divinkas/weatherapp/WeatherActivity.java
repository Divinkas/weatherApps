package com.example.divinkas.weatherapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.divinkas.weatherapp.Data.ItemTempPlaces;
import com.example.divinkas.weatherapp.Model.PlacesModel;

import java.util.Objects;

public class WeatherActivity extends AppCompatActivity {
    private final static int LAYOUT_NAME = R.layout.activity_weather;
    private final static int TYPE_THEME = R.style.AppTheme;
    private final static int TITLE = R.string.weather;

    TextView tvName, tvTemp, tvLat, tvLon, tvWt;
    ImageView imgWt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(TITLE);
        setTheme(TYPE_THEME);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_NAME);

        initialComponents();

        double c1 = Double.valueOf(getIntent().getStringExtra("lat"));
        double c2 = Double.valueOf(getIntent().getStringExtra("lon"));

        PlacesModel placesModel = new PlacesModel(this);
        setTemp(Objects.requireNonNull(placesModel.loadInfo(c1, c2)));
    }
    private void initialComponents(){
        tvName = findViewById(R.id.tvNamePlace);
        tvTemp = findViewById(R.id.valueTemp);
        tvLat = findViewById(R.id.valueLAT);
        tvLon = findViewById(R.id.valueLon);
        tvWt = findViewById(R.id.valueWT);
        imgWt = findViewById(R.id.imgWeather);
    }
    @SuppressLint("SetTextI18n")
    private void setTemp(ItemTempPlaces item){
        tvName.setText(item.getName());
        tvWt.setText(item.getWt());
        tvLon.setText(String.valueOf(item.getLon()));
        tvLat.setText(String.valueOf(item.getLat()));
        tvTemp.setText(item.getTemp() + "C");
        Glide.with(this).load(item.getLinkImg()).into(imgWt);
    }
}
