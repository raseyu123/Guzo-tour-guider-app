package com.example.guzo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.Model.City;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchCity extends AppCompatActivity {

    FirebaseRecyclerAdapter<City , CityViewAdapter> adapter;
  //search
    FirebaseRecyclerAdapter<City,CityViewAdapter> searchAdapter;
    List<String> suggestList = new ArrayList<>();
    MaterialSearchBar materialSearchBar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference cityList;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);

        database = FirebaseDatabase.getInstance();
        cityList = database.getReference("City");
        //Firebase

        recyclerView = findViewById(R.id.recycler_searche);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager  );
        recyclerView.setHasFixedSize(true);




        materialSearchBar = findViewById(R.id.searchBar);
        materialSearchBar.setHint("Enter City name");

        loadSuggest();
            materialSearchBar.setLastSuggestions(suggestList);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest= new ArrayList<>();
                for (String search : suggestList){
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())){
                        suggest.add(search);
                    }
                }
materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled)
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

     //   CityViewAdapter adapter=new CityViewAdapter(cities);
      //  initalizeData();
      //  initalizeAdapter();
        loadCity();
    }

    private void startSearch(CharSequence text) {
     Query searchByName = cityList.orderByChild("Name").equalTo(text.toString());
      searchAdapter = new FirebaseRecyclerAdapter<City, CityViewAdapter>(City.class,
              R.layout.activity_city_item,
              CityViewAdapter.class,
              searchByName) {
          @Override
          protected void populateViewHolder(CityViewAdapter cityViewAdapter, City model, int i) {
              cityViewAdapter.cityName.setText(model.getName());
              Picasso.get().load(model.getImage()).into(cityViewAdapter.cityPic);
              final City local= model;
              cityViewAdapter.setItemClickListener(new ItemClickListener() {
                  @Override
                  public void onClick(View view, int position, boolean isLongClick) {
                      Intent cityDetail = new Intent(SearchCity.this,CitySelected.class);
                      cityDetail.putExtra("CityId",searchAdapter.getRef(position).getKey());
                      startActivity(cityDetail);
                      // Toast.makeText(MainActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                  }
              });
          }
      };
      recyclerView.setAdapter(searchAdapter);

    }

    private void loadSuggest() {
                 cityList.addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                            City item = postSnapshot.getValue(City.class);
                            suggestList.add(item.getName());
                        }
                        materialSearchBar.setLastSuggestions(suggestList);
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });

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
                        Intent cityDetail = new Intent(SearchCity.this,CitySelected.class);
                        cityDetail.putExtra("CityId",adapter.getRef(position).getKey());
                        startActivity(cityDetail);
                        // Toast.makeText(MainActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });}
        };

        recyclerView.setAdapter(adapter);
    }

}
