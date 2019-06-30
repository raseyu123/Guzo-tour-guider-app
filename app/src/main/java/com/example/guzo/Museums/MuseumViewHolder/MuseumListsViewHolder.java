
package com.example.guzo.Museums.MuseumViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.R;

public class MuseumListsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView museumName;

    public final ImageView museumPic;
    private ItemClickListener itemClickListener;


        public MuseumListsViewHolder(@NonNull View itemView) {
            super(itemView);
            museumName= itemView.findViewById(R.id.museumName);
            museumPic= itemView.findViewById(R.id.museumImage);
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