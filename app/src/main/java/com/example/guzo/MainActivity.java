package com.example.guzo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.Model.City;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        {
            FirebaseRecyclerAdapter<City,CityViewAdapter> adapter ;
            RecyclerView recycler_city;
            RecyclerView.LayoutManager layoutManager;
            Context context;
            FirebaseDatabase database;
            DatabaseReference cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        database = FirebaseDatabase.getInstance();
        cityList = database.getReference("City");


        recycler_city = (RecyclerView)findViewById(R.id.city_lists);

        recycler_city.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_city.setLayoutManager(layoutManager);

        loadCity();

    }
            private void loadCity( ) {

                adapter= new FirebaseRecyclerAdapter<City, CityViewAdapter>(City.class, R.layout.activity_city_item, CityViewAdapter.class, cityList) {



                    protected void populateViewHolder(CityViewAdapter cityViewAdapter, City model , int position) {

                        cityViewAdapter.cityName.setText(model.getName());
                        Picasso.get().load(model.getImage()).into(cityViewAdapter.cityPic);
                        final City clickItem = model;
                        cityViewAdapter.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {
                             Intent cityDetail = new Intent(MainActivity.this,CitySelected.class);
                            cityDetail.putExtra("CityId",adapter.getRef(position).getKey());
                             startActivity(cityDetail);
                               // Toast.makeText(MainActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                            }
                        });}
                };

                recycler_city.setAdapter(adapter);
            }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.search_icon){
           startActivity(new Intent(MainActivity.this,SearchCity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}
