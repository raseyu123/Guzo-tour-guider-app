package com.example.guzo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.guzo.Adapter.CityInfoViewAdapter;
import com.google.android.material.tabs.TabLayout;

public class CityNeedToKnow extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CoordinatorLayout coordinatorLayout;
    String cityId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_city_need_to_know);
        if (getIntent() != null)
            cityId = getIntent().getStringExtra("CityId");
        if (!cityId.isEmpty()) {


        }

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.root_view);
        toolbar = (Toolbar) findViewById(R.id.info_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        getSupportActionBar().setTitle("Need to know");
        viewPager = (ViewPager) findViewById(R.id.info_view_pager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.info_tab);
        tabLayout.setupWithViewPager(viewPager);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void setupViewPager(ViewPager viewPager) {
        final Bundle data = new Bundle();
        data.putString("Id", cityId);

        CityInfoViewAdapter adapter = new CityInfoViewAdapter(getSupportFragmentManager());

        adapter.addFragment(CityOverView.getInstance(), "Overview");

        CityOverView.getInstance().setArguments(data);


        adapter.addFragment(CityWeather.getInstance(), "Weather");

        CityWeather.getInstance().setArguments(data);
        viewPager.setAdapter(adapter);
    }
}
