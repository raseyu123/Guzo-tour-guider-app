package com.example.guzo.Common;


import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Common {
    public static final String APP_ID ="9943dae0568ad3db55abcee5dd9a4a37";
    public static Location location;
public static String convertUnixToDate(int dt){
    Date date = new Date(dt*1000L);
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm EEE MM yyyy");
    String formatted = sdf.format(date);
    return formatted;
}
public  static String convertUnixToHour(int sunrise){
    Date date = new Date(sunrise*1000L);
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    String formatted = sdf.format(date);
    return formatted;
}

}
