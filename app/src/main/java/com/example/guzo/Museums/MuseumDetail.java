package com.example.guzo.Museums;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.guzo.Model.Museums;
import com.example.guzo.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MuseumDetail extends AppCompatActivity {
    ImageView museum_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnDirection;
    TextView description,mtitles;
   String museumId="";
FirebaseDatabase database;
DatabaseReference museum;
    Toolbar toolbar;
          @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);

   database=FirebaseDatabase.getInstance();
     museum=database.getReference("Museums");

              if (getIntent() != null)
                  museumId = getIntent().getStringExtra("CityId");
              if (!museumId.isEmpty()){
                  getDetailMuseum(museumId);
                  // cityIds.setCityId(cityId);
              }
        museum_image= findViewById(R.id.museum_image_collapsing);
        btnDirection= findViewById(R.id.museum_direction);
        description= findViewById(R.id.museumDescription);
              mtitles= findViewById(R.id.musTitle);
               collapsingToolbarLayout= findViewById(R.id.museum_collapsing);
             btnDirection.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent mapsss = new Intent(MuseumDetail.this, HotelMap.class);
                      mapsss.putExtra("museumId", museumId );
                      startActivity(mapsss);

                  }
              });
//       btnRating.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               showRatingDialog();
//           }
//       });
          }

    private void getDetailMuseum(String museumId) {
        museum.child(museumId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Museums museumies = dataSnapshot.getValue(Museums.class);
                assert museumies != null;

                collapsingToolbarLayout.setTitle(museumies.getName());
                Picasso.get().load(museumies.getImage()).into(museum_image);
                description.setText(museumies.getDescription());
                mtitles.setText(museumies.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
