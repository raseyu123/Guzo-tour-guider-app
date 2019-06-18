package com.example.guzo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityHome extends Fragment {
  private   SectionsPageAdapter sectionsPageAdapter;
  private TabLayout tabLayout;
   private ViewPager viewPager;

    public CityHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_home, container, false);

           tabLayout= (TabLayout) view.findViewById(R.id.city_tab_list);
        viewPager= (ViewPager) view.findViewById(R.id.fragment_container);
        viewPager.setAdapter(new SectionsPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}