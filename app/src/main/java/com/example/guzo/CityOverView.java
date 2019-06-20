package com.example.guzo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityOverView extends Fragment  {
    private TextView city_name,text_description,over_view;
    private ImageView cityPic;
 String cityId;
    private FirebaseDatabase database;
    private DatabaseReference city;


    public CityOverView() {
        // Required empty public constructor
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = FirebaseDatabase.getInstance();
        city = database.getReference("City");
        this.cityId = getArguments().getString("Ids");
        loadData("01");
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View v=inflater.inflate(R.layout.fragment_city_over_view, container, false);





        city_name = (TextView)v.findViewById(R.id.name_city);
        text_description = (TextView)v.findViewById(R.id.below_text);
        over_view =(TextView)v.findViewById(R.id.over_view);
       cityPic = (ImageView)v.findViewById(R.id.image_city);




        return v;
    }

    private void loadData(String cityId) {

        city.child(cityId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                com.example.guzo.Model.city cities=dataSnapshot.getValue(com.example.guzo.Model.city.class);
                assert cities != null;

                Picasso.get().load(cities.getImage()).into(cityPic);
                city_name.setText(cities.getName());
                text_description.setText(cities.getName());
                over_view.setText(cities.getDescription());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
