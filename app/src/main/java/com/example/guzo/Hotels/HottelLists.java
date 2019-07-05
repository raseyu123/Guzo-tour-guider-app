package com.example.guzo.Hotels;


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

import com.example.guzo.Hotels.HotelViewHolder.HotelListsViewHolder;
import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.Model.Hotels;
import com.example.guzo.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class HottelLists extends Fragment {
    private FirebaseRecyclerAdapter<Hotels, HotelListsViewHolder> adapter ;
    private RecyclerView recycler_hotel;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    private FirebaseDatabase database;
    private DatabaseReference hotelLists;
    String cityId="";
    static HottelLists instance;



    public static HottelLists getInstance(){

        if (instance == null)
            instance= new HottelLists();

        return instance;

    }
    public HottelLists() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_hottel_lists, container, false);
        cityId=this.getArguments().getString("Id");
        Toast.makeText(v.getContext(), cityId, Toast.LENGTH_SHORT).show();

        return v;

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler_hotel =view.findViewById (R.id.recycler_hotel_lists);
        recycler_hotel.setLayoutManager(new GridLayoutManager(getContext(),2));
//         LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(recycler_hotel.getContext()
//         ,R.anim.layout_fall_down);
//         recycler_hotel.setLayoutAnimation(controller);

        database = FirebaseDatabase.getInstance();
        hotelLists = database.getReference("Hotels");
        loadHotel(cityId);
    }
    private void loadHotel(String cityId ) {

        adapter= new FirebaseRecyclerAdapter<Hotels, HotelListsViewHolder>(Hotels.class,
                R.layout.activity_hotels_item,
                HotelListsViewHolder.class,

                hotelLists.orderByChild("CityID").equalTo(cityId)) {
            @Override
            protected void populateViewHolder(HotelListsViewHolder hotelListsViewHolder, Hotels model, int i) {
                hotelListsViewHolder.hotelName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(hotelListsViewHolder.hotelPic);
                hotelListsViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent hotelDetail = new Intent(view.getContext(), HotelDetail.class);
                        hotelDetail.putExtra("CityId", adapter.getRef(position).getKey());
                        startActivity(hotelDetail);
                    }    });}
        };




        recycler_hotel.setAdapter(adapter);

        //animation
        recycler_hotel.getAdapter().notifyDataSetChanged();
        recycler_hotel.scheduleLayoutAnimation();
    }


}



