package com.example.divinkas.weatherapp.DTO;

import android.os.AsyncTask;
import android.util.Log;

import com.example.divinkas.weatherapp.Data.ItemTempPlaces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class Connecter extends AsyncTask<Double, Void, ItemTempPlaces> {
    private ItemTempPlaces item;

    @Override
    protected ItemTempPlaces doInBackground(Double... doubles) {
        String link = "http://api.openweathermap.org/data/2.5/weather?";
        String query = "lat="+doubles[0]+"&lon="+doubles[1]+"&units=metric&appid=9d8ba174a36709c36aa8a6c784731c28";
        item = new ItemTempPlaces();

        try {
            Document document = Jsoup.connect(link+query).ignoreContentType(true).get();
            String result = document.text();

            JSONObject jsonObject = new JSONObject(result);

            JSONObject coords = jsonObject.getJSONObject("coord");
            item.setLat(coords.getDouble("lat"));
            item.setLon(coords.getDouble("lon"));

            JSONArray wt = jsonObject.getJSONArray("weather");
            for (int i = 0; i<wt.length(); i++){
                JSONObject wtItem = wt.getJSONObject(i);
                item.setWt(wtItem.getString("description"));
                item.setLinkImg("http://openweathermap.org/img/w/" + wtItem.getString("icon")+ ".png");
            }

            JSONObject main = jsonObject.getJSONObject("main");
            item.setTemp(main.getString("temp"));
            item.setName(jsonObject.getString("name"));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    protected void onPostExecute(ItemTempPlaces itemPlaces) {
        super.onPostExecute(itemPlaces);
    }
}

