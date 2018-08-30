package com.example.divinkas.weatherapp.Data;

public class ItemTempPlaces {
    private String Name;
    private double lat;
    private double lon;
    private String temp;
    private String wt;
    private String linkImg;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }
}
