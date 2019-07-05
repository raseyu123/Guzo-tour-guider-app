package com.example.guzo.Banks;



import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guzo.Banks.BankViewHolder.BankListsViewHolder;
import com.example.guzo.Hotels.HotelDetail;
import com.example.guzo.Hotels.HotelViewHolder.HotelListsViewHolder;
import com.example.guzo.Model.Banks;


import com.example.guzo.Model.Hotels;
import com.example.guzo.Model.Museums;
import com.example.guzo.Museums.MuseumDetail;
import com.example.guzo.Museums.MuseumViewHolder.MuseumListsViewHolder;
import com.example.guzo.R;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class BankLists extends Fragment {
    private FirebaseRecyclerAdapter<Banks, BankListsViewHolder> adapter ;
    private RecyclerView recycler_bank;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    private FirebaseDatabase database;
    private DatabaseReference bankLists;
    String cityId="";
    static BankLists instance;



    public static BankLists getInstance(){

        if (instance == null)
            instance= new BankLists();

        return instance;

    }
    public BankLists() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_bank_lists, container, false);
        cityId=this.getArguments().getString("Id");
        Toast.makeText(v.getContext(), cityId, Toast.LENGTH_SHORT).show();

        return v;

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler_bank =view.findViewById (R.id.recycler_bank_lists);
        recycler_bank.setLayoutManager(new GridLayoutManager(getContext(),2));
//         LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(recycler_bank.getContext()
//         ,R.anim.layout_fall_down);
//         recycler_bank.setLayoutAnimation(controller);

        database = FirebaseDatabase.getInstance();
        bankLists = database.getReference("Banks");
        loadBank(cityId);
    }
    private void loadBank(String cityId ) {
        adapter= new FirebaseRecyclerAdapter<Banks, BankListsViewHolder>(Banks.class,
                R.layout.activity_bank_item,
                BankListsViewHolder.class,
                bankLists.orderByChild("CityId").equalTo(cityId)) {



            protected void populateViewHolder(BankListsViewHolder bankListsViewHolder, Banks model , int position) {

                bankListsViewHolder.bankName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(bankListsViewHolder.bankPic);
                bankListsViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent bankDetail = new Intent(view.getContext(), BankDetail.class);
                        bankDetail.putExtra("CityId",adapter.getRef(position).getKey());
                        startActivity(bankDetail);
                        // Toast.makeText(MainActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });}
        };






        recycler_bank.setAdapter(adapter);

        //animation
        recycler_bank.getAdapter().notifyDataSetChanged();
        recycler_bank.scheduleLayoutAnimation();
    }


}





