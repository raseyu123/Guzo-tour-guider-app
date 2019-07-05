package com.example.guzo.Banks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.guzo.Hotels.HotelMap;
import com.example.guzo.Model.Banks;
import com.example.guzo.R;

import com.example.guzo.Model.Hotels;

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

public class BankDetail extends AppCompatActivity {
    ImageView bank_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnDirection;
    TextView description, mtitles;
    String bankId = "";
    FirebaseDatabase database;
    DatabaseReference bank;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_detail);

        database = FirebaseDatabase.getInstance();
        bank = database.getReference("Banks");

        if (getIntent() != null)
            bankId = getIntent().getStringExtra("CityId");
        if (!bankId.isEmpty()) {
            getDetailBank(bankId);
            // cityIds.setCityId(cityId);
        }
        bank_image = findViewById(R.id.bank_image_collapsing);
        btnDirection = findViewById(R.id.bank_direction);
        description = findViewById(R.id.bankDescription);
        mtitles = findViewById(R.id.bankTitle);
        collapsingToolbarLayout = findViewById(R.id.bank_collapsing);
        btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsss = new Intent(BankDetail.this, BankMap.class);
                mapsss.putExtra("bankId", bankId);
                startActivity(mapsss);

            }
        });
    }

    private void getDetailBank(String bankId){
        bank.child(bankId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Banks museumies = dataSnapshot.getValue(Banks.class);
                assert museumies != null;

                collapsingToolbarLayout.setTitle(museumies.getName());
                Picasso.get().load(museumies.getImage()).into(bank_image);
                description.setText(museumies.getDescription());
                mtitles.setText(museumies.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}




