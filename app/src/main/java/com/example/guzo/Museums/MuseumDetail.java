package com.example.guzo.Museums;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import com.example.guzo.CityNeedToKnow;
import com.example.guzo.CitySelected;
import com.example.guzo.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MuseumDetail extends AppCompatActivity {
    ImageView museum_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnDirection;
    String museumId="";
FirebaseDatabase database;
DatabaseReference museum;


          @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);

         database=FirebaseDatabase.getInstance();
         museum=database.getReference("Museums");


        museum_image= findViewById(R.id.museum_image_collapsing);
        btnDirection= findViewById(R.id.museum_direction);

               collapsingToolbarLayout= findViewById(R.id.museum_collapsing);
//             btnDirection.setOnClickListener(new View.OnClickListener() {
//                  @Override
//                  public void onClick(View v) {
//                      Intent mapsss = new Intent(MuseumDetail.this, MuseumMap.class);
//                      startActivity(mapsss);
//
//                  }
//              });
//       btnRating.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               showRatingDialog();
//           }
//       });
          }


}
