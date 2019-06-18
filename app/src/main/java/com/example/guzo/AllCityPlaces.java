package com.example.guzo;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Model.places;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllCityPlaces extends Fragment {
    RecyclerView recyclerView;
    Context context;
    private List<places> place;

    public AllCityPlaces() {
        // Required empty public constructor
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.why);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        //   recyclerView.setLayoutManager(new LinearLayoutManager(context));
        PlaceViewAdapter adapter=new PlaceViewAdapter(place);
        initalizeData();
        initalizeAdapter();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_city_places, container, false);


    }

    private void initalizeData() {
        place = new ArrayList<>();

        place.add(new places("vhddis","hotel",R.drawable.guzo));
        place.add(new places("Addnvs","Museum",R.drawable.ic_launcher_background));

    }

    private  void initalizeAdapter() {
        PlaceViewAdapter adapter=new PlaceViewAdapter(place);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
