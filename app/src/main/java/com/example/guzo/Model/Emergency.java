package com.example.guzo.Model;

public class Emergency {
    private String Catagory;

    private String Phone;
    private String CityId;
public  Emergency(){

}
    public Emergency(String catagory, String phone, String cityId) {
        Catagory = catagory;
        Phone = phone;
        CityId = cityId;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
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
}
