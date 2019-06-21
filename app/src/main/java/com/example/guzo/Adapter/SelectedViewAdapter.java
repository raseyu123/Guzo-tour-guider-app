package com.example.guzo.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class SelectedViewAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitle = new ArrayList<>();

        public SelectedViewAdapter(FragmentManager fm) {
            super(fm);
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
    }



