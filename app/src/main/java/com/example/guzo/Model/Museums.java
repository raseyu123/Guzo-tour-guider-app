package com.example.guzo.Model;

public class Museums {
    private String Name;
    private String Image;
    private String Description;
    private String Address;
    private String LocationName;
    private String Phone;
    private String CityId;
    private Double Lat;
    private Double Lon;

    public Museums(){

    }
    public Museums(String name, String image, String description, String address, String locationName, String phone, String cityId, Double lat, Double lon) {
        Name = name;
        Image = image;
        Description = description;
        Address = address;
        LocationName = locationName;
        Phone = phone;
        CityId = cityId;
        Lat = lat;
        Lon = lon;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
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
