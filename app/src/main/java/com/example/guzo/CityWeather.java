package com.example.guzo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.guzo.Common.Common;
import com.example.guzo.Model.WeatherResult;
import com.example.guzo.Retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeather extends Fragment {

ImageView img_weather;
TextView txt_city_name,txt_humidity,txt_sunrise,txt_sunset,txt_pressure,txt_temp,txt_description,txt_wind,txt_geo_coord,txt_date_time;
LinearLayout weather_panel;
ProgressBar loading;
CompositeDisposable compositeDisposable;
IOenWeatherMap mService;

    public CityWeather() {
compositeDisposable= new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOenWeatherMap.class);
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_city_weather, container, false);
         img_weather = (ImageView)view.findViewById(R.id.img_weather);
         txt_city_name = (TextView)view.findViewById(R.id.txt_city_name);
         txt_date_time = (TextView)view.findViewById(R.id.txt_date_time);
         txt_description = (TextView)view.findViewById(R.id.txt_description);
         txt_humidity = (TextView)view.findViewById(R.id.txt_humiditty);
         txt_pressure = (TextView)view.findViewById(R.id.txt_pressure);
         txt_geo_coord = (TextView)view.findViewById(R.id.txt_geo_coord);
         txt_sunrise = (TextView)view.findViewById(R.id.txt_sunrise);
         txt_sunset = (TextView)view.findViewById(R.id.txt_sunset);
         txt_wind = (TextView)view.findViewById(R.id.txt_wind);
         txt_temp = (TextView)view.findViewById(R.id.txt_temperature);
     weather_panel =(LinearLayout)view.findViewById(R.id.weather_panel);
   loading =(ProgressBar)view.findViewById(R.id.loading);
    getWeatherInformation();
        return view;
    }

    private void getWeatherInformation() {
        compositeDisposable.add(mService.getWeatherByLatLng(String.valueOf(Common.class),
                String.valueOf(city.class)),
                Common.APP_ID,
                "meteric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {
                        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                                .append(WeatherResult.getWeather().get(0).getIcon())
                                .append(".png").toString().into(img_weather);
                    }
                },new io.reactivex.functions.Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) throws Exception{
                        Toast.makeText(getActivity(),""+throwable,Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
