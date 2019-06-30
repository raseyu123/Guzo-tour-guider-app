package com.example.guzo;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Model.City;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class SearchCity extends AppCompatActivity {
  //search
    FirebaseRecyclerAdapter<City,CityViewAdapter> searchAdapter;
    List<String> suggestList = new ArrayList<>();
    MaterialSearchBar materialSearchBar;
    RecyclerView recyclerView;
    Context context;

    FirebaseDatabase database;
    DatabaseReference cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        //Firebase
     //   database = FirebaseDatabase.getInstance("City");

        recyclerView=(RecyclerView)findViewById(R.id.recycler_search);
        recyclerView.setHasFixedSize(true);
      
       recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        materialSearchBar =(MaterialSearchBar)findViewById(R.id.searchBar);
        materialSearchBar.setHint("Enter City name");
        loadSuggest();
        
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        
     //   CityViewAdapter adapter=new CityViewAdapter(cities);
      //  initalizeData();
      //  initalizeAdapter();
    }

    private void loadSuggest() {

    }

}
