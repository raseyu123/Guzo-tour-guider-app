package com.example.guzo;



import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeather extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    TextView selectCity, cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    ProgressBar loader;
    Typeface weatherFont;
    String city = "Lalibela";
    /* Please Put your API KEY here */
    String OPEN_WEATHER_MAP_API = "9943dae0568ad3db55abcee5dd9a4a37";
    public CityWeather() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_city_weather, container, false);
        loader = (ProgressBar)view. findViewById(R.id.loader);
       swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refersh_weather);
       swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
              android.R.color.holo_green_dark,
              android.R.color.holo_orange_dark,
              android.R.color.holo_blue_dark
       );

        cityField = (TextView)view. findViewById(R.id.city_field);
        updatedField = (TextView)view. findViewById(R.id.updated_field);
        detailsField = (TextView)view. findViewById(R.id.details_field);
        currentTemperatureField = (TextView)view. findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)view. findViewById(R.id.humidity_field);
        pressure_field = (TextView)view. findViewById(R.id.pressure_field);
        weatherIcon = (TextView)view.findViewById(R.id.weather_icon);
        weatherFont = Typeface.createFromAsset( getActivity().getAssets(),"fonts/weathericons-regular-webfont.ttf") ;
        weatherIcon.setTypeface(weatherFont);

        taskLoadUp(city);




        return view;
    }

    public void taskLoadUp(final String query) {
          swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
              @Override
              public void onRefresh() {
                  if (Function.isNetworkAvailable(getActivity().getBaseContext())) {
                      DownloadWeather task = new DownloadWeather();
                      task.execute(query);
                  } else {
                      Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                  }
              }

          });
          swipeRefreshLayout.post(new Runnable() {
              @Override
              public void run() {
                  if (Function.isNetworkAvailable(getContext())) {
                      DownloadWeather task = new DownloadWeather();
                      task.execute(query);
                  } else {
                      Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                  }
              }

          });


}

    class DownloadWeather extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);

        }
        protected String doInBackground(String...args) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();

                    cityField.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    detailsField.setText(details.getString("description").toUpperCase(Locale.US));
                    currentTemperatureField.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    humidity_field.setText("Humidity: " + main.getString("humidity") + "%");
                    pressure_field.setText("Pressure: " + main.getString("pressure") + " hPa");
                    updatedField.setText(df.format(new Date(json.getLong("dt") * 1000)));
                    weatherIcon.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));

                    loader.setVisibility(View.GONE);

                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }


        }



    }



}
