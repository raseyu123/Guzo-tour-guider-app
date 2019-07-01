package com.example.guzo;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Model.City;
import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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
        database = FirebaseDatabase.getInstance();
        cityList = database.getReference("City");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        materialSearchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        materialSearchBar.setHint("Enter City name");
        //  loadSuggest();

        materialSearchBar.setCardViewElevation(10);
//        materialSearchBar.addTextChangeListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                List<String> suggest= new ArrayList<>();
//                for (String search : suggestList){
//                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())){
//                        suggest.add(search);
//                    }
//                }
//materialSearchBar.setLastSuggestions(suggest);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
//            @Override
//            public void onSearchStateChanged(boolean enabled) {
//                if (!enabled)
//                    recyclerView.setAdapter(searchAdapter);
//            }
//
//            @Override
//            public void onSearchConfirmed(CharSequence text) {
//                startSearch(text);
//
//            }
//
//            @Override
//            public void onButtonClicked(int buttonCode) {
//
//            }
//        });
//
//     //   CityViewAdapter adapter=new CityViewAdapter(cities);
//      //  initalizeData();
//      //  initalizeAdapter();
//    }
//
//    private void startSearch(CharSequence text) {
//        Query searchByName = cityList.orderByChild("name").equalTo(text.toString());
//
//
//    }
//
//    private void loadSuggest() {
//                 cityList.addListenerForSingleValueEvent(new ValueEventListener() {
//                     @Override
//                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
//                            City item = postSnapshot.getValue(City.class);
//                            suggestList.add(item.getName());
//                        }
//                        materialSearchBar.setLastSuggestions(suggestList);
//                     }
//
//                     @Override
//                     public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                     }
//                 });
//
    }
}
