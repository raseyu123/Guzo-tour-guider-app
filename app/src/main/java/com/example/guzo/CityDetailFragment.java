package com.example.guzo;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityDetailFragment extends Fragment implements OnFragmentInteractionListener {
    private CityDetailPageAdapter cityDetailPageAdapter;
    private OnFragmentInteractionListener mListener;
    Toolbar toolbar;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    String cityId;

    public CityDetailFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_city_detail, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.city_detail_tab);
        viewPager = (ViewPager) v.findViewById(R.id.city_detail_container);
        viewPager.setAdapter(new CityDetailPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
      //  assert getArguments() != null;
      //  cityId = getArguments().getString("Id");
        return v;
    }

    public void onViewCreateView(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //mListener.onFragmentInteraction(cityId);
       // final Bundle data = new Bundle();
        //data.putString("Id",cityId);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(String id) {
        //mListener.onFragmentInteraction(this.cityId);
    }



}