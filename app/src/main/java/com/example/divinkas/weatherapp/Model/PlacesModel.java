package com.example.divinkas.weatherapp.Model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.divinkas.weatherapp.DTO.Connecter;
import com.example.divinkas.weatherapp.Data.ItemTempPlaces;
import com.example.divinkas.weatherapp.Db.DbHelper;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PlacesModel {
    private Context context;
    private DbHelper dbHelper;

    public PlacesModel(Context context){
        this.context = context;
        dbHelper= new DbHelper(context);
    }

    public void AddPlace(double lat, double lon){
        addToDb(loadInfo(lat, lon));
    }
    public void dellPlaces(double lat, double lon){
        dbHelper.dellItemTempPlaces(lat, lon);
    }
    public List<ItemTempPlaces> getListPlaces(){ return dbHelper.getListItemsPlaces(); }

    @Nullable
    public ItemTempPlaces loadInfo(double lat, double lon){
        try {

            Connecter connecter = new Connecter();
            connecter.execute(lat, lon);
            ItemTempPlaces itemTempPlaces = connecter.get();
            return itemTempPlaces;

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addToDb(ItemTempPlaces item){
        dbHelper.addItemTempPlaces(item);
    }

}
