package com.example.guzo.Hotels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.guzo.Model.Hotels;
import com.example.guzo.R;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HotelDetail extends AppCompatActivity {
    ImageView hotel_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnDirection;
    TextView description, mtitles;
    String hotelId = "";
    FirebaseDatabase database;
    DatabaseReference hotel;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        database = FirebaseDatabase.getInstance();
        hotel = database.getReference("Hotels");

        if (getIntent() != null)
            hotelId = getIntent().getStringExtra("CityId");
        if (!hotelId.isEmpty()) {
            getDetailHotel(hotelId);
            // cityIds.setCityId(cityId);
        }
        hotel_image = findViewById(R.id.hotel_image_collapsing);
        btnDirection = findViewById(R.id.hotel_direction);
        description = findViewById(R.id.hotelDescription);
        mtitles = findViewById(R.id.hoTitle);
        collapsingToolbarLayout = findViewById(R.id.hotel_collapsing);
        btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsss = new Intent(HotelDetail.this, HotelMap.class);
                mapsss.putExtra("hotelId", hotelId);
                startActivity(mapsss);

            }
        });
    }

        private void getDetailHotel(String hotelId){
            hotel.child(hotelId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Hotels museumies = dataSnapshot.getValue(Hotels.class);
                    assert museumies != null;

                    collapsingToolbarLayout.setTitle(museumies.getName());
                    Picasso.get().load(museumies.getImage()).into(hotel_image);
                    description.setText(museumies.getDescription());
                    mtitles.setText(museumies.getName());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }




