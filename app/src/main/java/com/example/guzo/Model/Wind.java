package com.example.guzo.Model;

class Wind {
    private double speed;

    private int deg;
    public Wind() {
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }
    public double getSpeed(){
        return this.speed;
    }
    public void setDeg(int deg){
        this.deg = deg;
    }
    public int getDeg(){
        return this.deg;
    }

}
