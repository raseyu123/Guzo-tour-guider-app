package com.example.guzo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuseumLists extends Fragment {

    private static MuseumLists   instance;


    public static MuseumLists   getInstance(){

        if (instance == null)
            instance= new MuseumLists  ();

        return instance;

    }
    public MuseumLists() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_museum_lists, container, false);
    }

}
