package com.example.guzo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CityDetailPageAdapter  extends FragmentPagerAdapter {
    private static final int FRAGMENT_COUNT = 3;
    public CityDetailPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CityOverView();
            case 1:
                return new CityWeather();
            case 2:
                return new CityEmergency();
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
                return "Overview";
            case 1:
                return "Weather";
            case 2:
                return  "Emergency Contact";
        }
        return null;
    }


}
