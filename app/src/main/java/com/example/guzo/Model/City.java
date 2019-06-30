package com.example.guzo.Model;

public class City {

    private String Name;
    private  String Image;
    private  String Description;

    private  Double Lat;
    private  Double Lon;



    public City() {

    }
    public City(String name, String image, String description, Double lat, Double lon){
        Name = name;
        Image = image;
        Description = description;
        Lat =lat;
        Lon= lon;
    }



    public String getName()
    {
        return Name;
    }
    public  void  setName(String name)
    {
        Name = name;
    }
    public String getImage()
    {
        return Image;
    }
    public void setImage(String image)
    {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public Double getLon() {
        return Lon;
    }

    public void setLon(Double lon) {
        Lon = lon;
    }
}
