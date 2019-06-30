package com.example.guzo;



import android.graphics.Typeface;
import android.os.Bundle;

import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.guzo.Model.City;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeather extends Fragment  implements PtrHandler {
    private PtrClassicFrameLayout pullToRefreshLayout;
    TextView  cityField, detailsField, currentTemperatureField,  weatherIcon, updatedField;
    ProgressBar loader;
    Typeface weatherFont;
    Handler handler;
    FirebaseDatabase database;
    DatabaseReference city;
    String cityId="";
    private static CityWeather instance;


    public static CityWeather getInstance(){

        if (instance == null)
            instance= new CityWeather();

        return instance;

    }
    public CityWeather() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        city = database.getReference("City");


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_city_weather, container, false);
        loader = (ProgressBar)view. findViewById(R.id.loader);
        pullToRefreshLayout = (PtrClassicFrameLayout) view.findViewById(R.id.refersh_weather);

        //set handler for pull to refresh layout
        pullToRefreshLayout.setPtrHandler(this);
        //set last update time in header
        pullToRefreshLayout.setLastUpdateTimeRelateObject(this);


        cityField = (TextView)view. findViewById(R.id.city_field);
        updatedField = (TextView)view. findViewById(R.id.updated_field);
        detailsField = (TextView)view. findViewById(R.id.details_field);
        currentTemperatureField = (TextView)view. findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)view.findViewById(R.id.weather_icon);
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        weatherIcon.setTypeface(weatherFont);
        cityId = this.getArguments().getString("Id");
        getDetailCity(cityId);
        return view;
    }
    private void getDetailCity(String cityId) {
        city.child(cityId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                City cityies = dataSnapshot.getValue(City.class);
                assert cityies != null;


                updateWeatherData(cityies.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

    private void updateWeatherData(final String city){
        handler =new Handler();
        new Thread(){
            public void run(){
                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(getContext(),
                                  getActivity().getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }
    private void renderWeather(JSONObject json){
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            detailsField.setText(
                    details.getString("description").toUpperCase(Locale.US) +
                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");

            currentTemperatureField.setText(
                    String.format("%.2f", main.getDouble("temp"))+ " ℃");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            updatedField.setText("Last update: " + updatedOn);
            weatherIcon.setText(Html.fromHtml(RemoteFetch.setWeatherIcon(details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000)));

        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
        loader.setVisibility(View.GONE);
        pullToRefreshLayout.refreshComplete();
    }


    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return true;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        getDetailCity(cityId);
    }



}