package com.example.guzo.Museums;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.CitySelected;
import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.Model.City;
import com.example.guzo.Model.Museums;
import com.example.guzo.Museums.MuseumViewHolder.MuseumListsViewHolder;
import com.example.guzo.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuseumLists extends Fragment {
    private FirebaseRecyclerAdapter<Museums, MuseumListsViewHolder> adapter ;
    private RecyclerView recycler_museum;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    private FirebaseDatabase database;
    private DatabaseReference museumLists;
    String cityId="";
    static MuseumLists instance;



    public static MuseumLists getInstance(){

        if (instance == null)
            instance= new MuseumLists();

        return instance;

    }
    public MuseumLists() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_museum_lists, container, false);
        cityId=this.getArguments().getString("Id");
        Toast.makeText(v.getContext(), cityId, Toast.LENGTH_SHORT).show();

        return v;

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler_museum=view.findViewById (R.id.recycler_museum_lists);
        recycler_museum.setLayoutManager(new GridLayoutManager(getContext(),2));
//         LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(recycler_museum.getContext()
//         ,R.anim.layout_fall_down);
//         recycler_museum.setLayoutAnimation(controller);

        database = FirebaseDatabase.getInstance();
        museumLists = database.getReference("Museums");
        loadMuseum(cityId);
    }
    private void loadMuseum(String cityId ) {

        adapter= new FirebaseRecyclerAdapter<Museums, MuseumListsViewHolder>(Museums.class,
                R.layout.activity_museums_item,
                MuseumListsViewHolder.class,

                museumLists.orderByChild("CityId").equalTo(cityId)) {



            protected void populateViewHolder(MuseumListsViewHolder museumListsViewHolder, Museums model , int position) {

                museumListsViewHolder.museumName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(museumListsViewHolder.museumPic);
                museumListsViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent museumDetail = new Intent(view.getContext(), MuseumDetail.class);
                       museumDetail.putExtra("CityId",adapter.getRef(position).getKey());
                        startActivity(museumDetail);
                        // Toast.makeText(MainActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });}
        };

        recycler_museum.setAdapter(adapter);

        //animation
        recycler_museum.getAdapter().notifyDataSetChanged();
        recycler_museum.scheduleLayoutAnimation();
    }

}
