package com.example.guzo;

import com.example.guzo.Model.WeatherResult;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOenWeatherMap {
    @GET("weather")
    Observable<WeatherResult> getWeatherByCityName(
                                                 @Query("q") String cityName,
                                                 @Query("appid") String appid,
                                                 @Query("units") String unit);


}
