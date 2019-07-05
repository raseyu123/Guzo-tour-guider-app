
package com.example.guzo.Banks.BankViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.R;

public class BankListsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView bankName;

    public final ImageView bankPic;
    private ItemClickListener itemClickListener;


        public BankListsViewHolder(@NonNull View itemView) {
            super(itemView);
            bankName= itemView.findViewById(R.id.bankName);
            bankPic= itemView.findViewById(R.id.bankImage);
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