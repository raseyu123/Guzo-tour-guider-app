package com.example.guzo.Model;

public class Coord {
    private double lon;

    private double lat;

    public Coord() {
    }

    public void setLon(double lon){
        this.lon = lon;
    }
    public double getLon(){
        return this.lon;
    }
    public void setLat(double lat){
        this.lat = lat;
    }
    public double getLat(){
        return this.lat;
    }

    public  String toString(){
        return new StringBuilder("[").append(this.lat).append(',').append(this.lon).append(']').toString();
    }
}
