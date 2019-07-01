package com.example.guzo.Museums;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.CitySelected;
import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.Model.City;
import com.example.guzo.Model.Museums;
import com.example.guzo.Museums.MuseumViewHolder.MuseumListsViewHolder;
import com.example.guzo.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuseumLists extends Fragment {

    Context context;

       static MuseumLists instance;



    public static MuseumLists getInstance(){

        if (instance == null)
            instance= new MuseumLists();

        return instance;

    }
    public MuseumLists() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_museum_lists, container, false);


        return v;

    }














}
