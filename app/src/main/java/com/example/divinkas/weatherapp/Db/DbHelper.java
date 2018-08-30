package com.example.divinkas.weatherapp.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.divinkas.weatherapp.Data.ItemTempPlaces;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public SQLiteDatabase database;

    private static final int VERSION = 1;
    private static final String DB_NAME = "contacts_like";

    public static final String TABLE_PLACES = "places_table";

    public static final String PLACES_ID = "_id";
    public static final String PLACES_NAME = "name_place";
    public static final String PLACES_C1 = "coords1";
    public static final String PLACES_C2 = "coords2";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PLACES);
        onCreate(db);
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PLACES + " ("+ PLACES_ID + " integer primary key, "
                + PLACES_NAME + " text, " + PLACES_C1 + " double, " + PLACES_C2 + " double)");
    }

    public void addItemTempPlaces(ItemTempPlaces item){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.PLACES_NAME, item.getName());
        contentValues.put(DbHelper.PLACES_C1, item.getLat());
        contentValues.put(DbHelper.PLACES_C2, item.getLon());
        database.insert(DbHelper.TABLE_PLACES, null, contentValues);
    }
    public void dellItemTempPlaces(double lat, double lon){
        database.delete(TABLE_PLACES, PLACES_C1 + "=? and " + PLACES_C2  + "=?",
                new String[]{String.valueOf(lat), String.valueOf(lon)});
    }
    public List<ItemTempPlaces> getListItemsPlaces(){
        List<ItemTempPlaces> placesList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + DbHelper.TABLE_PLACES, null, null);
        if(cursor.moveToNext()) {
            do {
                ItemTempPlaces itm = new ItemTempPlaces();
                itm.setName(cursor.getString(cursor.getColumnIndex(DbHelper.PLACES_NAME)));
                itm.setLat(cursor.getDouble(cursor.getColumnIndex(DbHelper.PLACES_C1)));
                itm.setLon(cursor.getDouble(cursor.getColumnIndex(DbHelper.PLACES_C2)));
                placesList.add(itm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return placesList;
    }
}