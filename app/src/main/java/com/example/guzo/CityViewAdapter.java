package com.example.guzo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;

public class CityViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView cityName;
    public ImageView cityPic;
    private ItemClickListener itemClickListener;

    public CityViewAdapter(@NonNull View itemView) {
        super(itemView);
        cityName=(TextView)itemView.findViewById(R.id.cityName);
        cityPic=(ImageView)itemView.findViewById(R.id.cityImage);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }}

