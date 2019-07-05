package com.example.guzo;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;

public class EmergencyListsViewHolder extends  RecyclerView.ViewHolder  {
    public final TextView Name,detail;


    private ItemClickListener itemClickListener;


    public EmergencyListsViewHolder(@NonNull View itemView) {
        super(itemView);
        Name= itemView.findViewById(R.id.emergName);
        detail= itemView.findViewById(R.id.detail);

    }}


