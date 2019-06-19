package com.example.guzo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public  class CityDetailPageAdapter  extends FragmentPagerAdapter implements OnFragmentInteractionListener {
    private static final int FRAGMENT_COUNT = 3;
OnFragmentInteractionListener onFragment;
String Id;
CityOverView overView;
    public CityDetailPageAdapter(FragmentManager fm) {
        super(fm);
    }


    public Fragment getItem(int position) {
        final Bundle data = new Bundle();
        data.putString("Ids",this.Id);

        switch (position){
            case 0:


           overView =  new CityOverView();
           overView.setArguments(data);
             return overView;



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


    @Override
    public void onFragmentInteraction(String id) {
        this.Id=id;
    }
}
