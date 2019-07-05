
package com.example.guzo.Hotels.HotelViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.R;

public class HotelListsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView hotelName;

    public final ImageView hotelPic;
    private ItemClickListener itemClickListener;


        public HotelListsViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName= itemView.findViewById(R.id.hotelName);
            hotelPic= itemView.findViewById(R.id.hotelImage);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}