package com.example.divinkas.weatherapp.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public SQLiteDatabase database;

    private static final int VERSION = 1;
    private static final String DB_NAME = "contacts_like";

    public static final String TABLE_PLACES = "places_table";
    public static final String TABLE_LAST_WEATHER = "last_weather";

    public static final String PLACES_ID = "_id";
    public static final String PLACES_NAME = "name_place";
    public static final String PLACES_C1 = "coords1";
    public static final String PLACES_C2 = "coords2";

    //public static final String LAST_NAME = "name_wt";
    //public static final String LAST_TEMP = "temp_wt";
    //public static final String LAST_STATUS = "status_wt";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PLACES);
        db.execSQL("drop table if exists " + TABLE_LAST_WEATHER);
        onCreate(db);
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PLACES + " ("+ PLACES_ID + " integer primary key, "
                + PLACES_NAME + " text, " + PLACES_C1 + " text, " + PLACES_C2 + " text)");
    }

    private boolean isNotice(SQLiteDatabase db, String tableName){
        return false;
    }

}