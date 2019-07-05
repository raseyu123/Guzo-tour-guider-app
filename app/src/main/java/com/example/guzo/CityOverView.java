package com.example.guzo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityOverView extends Fragment  {
    private TextView city_name,text_description,over_view;
    private ImageView cityPic;
    String cityId;
    private FirebaseDatabase database;
    private DatabaseReference city;
    private static CityOverView instance;
   FloatingActionButton play ,stop;
   FloatingActionMenu audioMenu;

    private TTS tts;
    private String dist;

    public static CityOverView getInstance(){

        if (instance == null)
            instance= new CityOverView();

        return instance;

    }
    public CityOverView() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_city_over_view, container, false);






        city_name = v.findViewById(R.id.name_city);
        text_description = v.findViewById(R.id.below_text);
        over_view = v.findViewById(R.id.over_view);
        cityPic = v.findViewById(R.id.image_city);
          audioMenu=v.findViewById(R.id.menuAudio);
        play=v.findViewById(R.id.play);
        stop=v.findViewById(R.id.stop);
        cityId=this.getArguments().getString("Id");



        return v;
    }

    private void loadData(String cityId) {
        database=FirebaseDatabase.getInstance();

        city = database.getReference("City");
        city.child(cityId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                com.example.guzo.Model.City cities=dataSnapshot.getValue(com.example.guzo.Model.City.class);
                assert cities != null;

                Picasso.get().load(cities.getImage()).into(cityPic);
                city_name.setText(cities.getName());
                text_description.setText(cities.getName());
                over_view.setText(cities.getDescription());
                dist=cities.getDescription();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tts = new TTS(getActivity().getBaseContext(), Locale.US);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(dist);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.stop();
            }
        });
        loadData(cityId);
    }


    @Override
    public void onDestroy() {

        tts.stop();


        super.onDestroy();
    }
}
