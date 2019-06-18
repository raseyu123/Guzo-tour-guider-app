package com.example.guzo.Model;

public class places {
    private String placeName;
    private  int placeImage;
   private String  placeCatagory;

    public places(String name,String catagory, int image){
        placeName = name;
        placeCatagory = catagory;

        placeImage = image;
    }

    public places(String name,String catagory) {
        placeName = name;
        placeCatagory = catagory;
    }

    public String getPlaceName(){
        return placeName;
    }
    public  void  setPlaceName(String name){
        placeName = name;
    }
    public String getPlaceCatagory() {
        return placeCatagory;
    }

    public void setPlaceCatagory(String catagory) {
        placeCatagory = catagory;
    }
    public int getPlaceImage()

    {
        return placeImage;
    }
    public void setCityImage(int image){
        placeImage = image;
    }
}
