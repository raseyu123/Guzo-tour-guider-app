package com.example.guzo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPageAdapter extends FragmentPagerAdapter {
    private static final int FRAGMENT_COUNT = 2;



    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AllCityPlaces();
            case 1:
                return new MuseumLists();
        }
        return null;
    }

    public int getCount() {
        return FRAGMENT_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "All";
            case 1:
                return "Museum";

        }
        return null;
    }



}
