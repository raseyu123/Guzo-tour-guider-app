package com.example.guzo;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.Model.Emergency;
import com.example.guzo.Model.Museums;
import com.example.guzo.Museums.MuseumDetail;
import com.example.guzo.Museums.MuseumLists;
import com.example.guzo.Museums.MuseumViewHolder.MuseumListsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityEmergency extends Fragment {
    private FirebaseRecyclerAdapter<Emergency, EmergencyListsViewHolder> adapter ;
    private RecyclerView recycler_emergency;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    private FirebaseDatabase database;
    private DatabaseReference emergencyLists;
    String cityId="";
    static CityEmergency instance;

    public CityEmergency() {
        // Required empty public constructor
    }
    public static CityEmergency getInstance(){

        if (instance == null)
            instance= new CityEmergency();

        return instance;

    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View v= inflater.inflate(R.layout.fragment_city_emergency, container, false);
        cityId=this.getArguments().getString("Id");
        Toast.makeText(v.getContext(), cityId, Toast.LENGTH_SHORT).show();

        return v;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler_emergency=view.findViewById (R.id.recycler_emerg_lists);
        recycler_emergency.setLayoutManager(new LinearLayoutManager(getContext()));
//         LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(recycler_museum.getContext()
//         ,R.anim.layout_fall_down);
//         recycler_museum.setLayoutAnimation(controller);


        loadMuseum(cityId);
    }
    private void loadMuseum(String cityId ) {
        database = FirebaseDatabase.getInstance();
        emergencyLists = database.getReference("Emergency");
        adapter= new FirebaseRecyclerAdapter<Emergency, EmergencyListsViewHolder>(Emergency.class,
                R.layout.activity_emergency_item,
                EmergencyListsViewHolder.class,

                emergencyLists.orderByChild("CityId").equalTo(cityId)) {


            protected void populateViewHolder(EmergencyListsViewHolder emergencyListsViewHolder, Emergency model , int position) {

                emergencyListsViewHolder.Name.setText(model.getCatagory());
                emergencyListsViewHolder.detail.setText(model.getPhone());


            }
        };

        recycler_emergency.setAdapter(adapter);

        //animation
        recycler_emergency.getAdapter().notifyDataSetChanged();
        recycler_emergency.scheduleLayoutAnimation();
    }

}
