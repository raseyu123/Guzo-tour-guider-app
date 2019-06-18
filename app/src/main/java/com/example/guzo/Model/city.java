package com.example.guzo.Model;

public class city {

    private String Name;
    private  String Image;

    public city() {

    }
    public city(String name, String image){
        Name = name;
        Image = image;
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
}
