package com.example.guzo.Museums;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import com.example.guzo.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MuseumDetail extends AppCompatActivity {
    ImageView museum_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnDirection,btnRating;
AppCompatRatingBar ratingBar;
String museumId="";
FirebaseDatabase database;
DatabaseReference museum;
DatabaseReference ratingTbl;

          @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);

         database=FirebaseDatabase.getInstance();
         museum=database.getReference("Museums");
         ratingTbl=database.getReference("MuseumRating");

        museum_image= findViewById(R.id.museum_image_collapsing);
        btnDirection= findViewById(R.id.museum_direction);
        ratingBar= findViewById(R.id.museum_rating_bar);
               collapsingToolbarLayout= findViewById(R.id.museum_collapsing);
       btnRating= findViewById(R.id.museum_rating);
//       btnRating.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               showRatingDialog();
//           }
//       });
          }

//    private void showRatingDialog() {
//
//        //Toast.makeText( this, "what", Toast.LENGTH_SHORT).show();
//        new AppRatingDialog.Builder()
//                .setPsitiveButtonText("Submit")
//                .setNegayiveButtonText("Cancel")
//                .setNoteDescriptions(Arrays.asList("Very Bad","Not Good","Quite Ok","Very Good","Excellent"))
//                .setDefaultRating(0)
//                .setTitle("Rate this Museum")
//                .setDescription("Please select stars and give your feedback")
//                .setTitleTextColor(R.color.text)
//                .setDescriptionTextColor(R.color.navTitle)
//                .setHint("Please write your comment here...")
//                .setHintTextColor(R.color.navTitle)
//                .setCommentBackgroundColor(R.color.mainBody)
//                .setWindowAnimation(R.style.RatingDialogFadeAnim)
//                .create(MuseumDetail.this)
//                .show();
//    }
//private void getDetailMuseum(String museumId){
//
//}
//@Override
//public void onPositiveButtonClicked(int i,String s){
//    Rating rating=new Rating(),
//    museumId,
//    String.valueOf(value),
//    comments);
//
//}

}
