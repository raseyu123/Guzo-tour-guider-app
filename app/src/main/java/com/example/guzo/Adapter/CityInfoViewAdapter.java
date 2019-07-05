package com.example.guzo.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class CityInfoViewAdapter extends FragmentPagerAdapter {
     private final List<Fragment> fragmentList = new ArrayList<>();
     private final List<String> fragmentTitle = new ArrayList<>();

    public CityInfoViewAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public CityInfoViewAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }




    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public  void  addFragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        fragmentTitle.add(title);

    }
    @NonNull
    @Override
    public CharSequence getPageTitle(int i){
        return fragmentTitle.get(i);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        super.destroyItem(container, position, object);
    }}


}
