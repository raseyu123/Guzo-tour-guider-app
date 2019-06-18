package com.example.guzo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CityDetail extends AppCompatActivity {
      TextView description;
      CollapsingToolbarLayout collapsingToolbarLayout;
      FloatingActionButton btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       //Init View
        description = (TextView)findViewById(R.id.description);
        btnMap = (FloatingActionButton)findViewById(R.id.fav);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
    }

}
