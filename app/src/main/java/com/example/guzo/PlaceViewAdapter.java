package com.example.guzo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guzo.Interface.ItemClickListener;
import com.example.guzo.Model.places;

import java.util.List;

public class PlaceViewAdapter  extends RecyclerView.Adapter<PlaceViewAdapter.PlaceViewHolder> {
        private List<places> place;
    public PlaceViewAdapter(List<places> places) {
            this.place = places;
        }
        static class PlaceViewHolder extends RecyclerView.ViewHolder implements com.example.guzo.PlaceViewHolder {
            CardView cardView;
            TextView placeName,placeCatagory;

            ImageView placeImages;
            private ItemClickListener itemClickListener;


            public PlaceViewHolder(@NonNull final View itemView) {
                super(itemView);
                cardView = (CardView) itemView.findViewById(R.id.cardPlace);
                placeName = (TextView) itemView.findViewById(R.id.placeName);
                placeCatagory = (TextView) itemView.findViewById(R.id.placeCatagory);
                placeImages = (ImageView) itemView.findViewById(R.id.placeImage);
                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(itemView.getContext(), PlaceDetail.class);
                        //  intent.putExtra("City", (Parcelable) cityNames);
                        itemView.getContext().startActivity(intent);


                    }
                });
            }
        }
        @NonNull
        @Override
        public PlaceViewAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_place_items, parent, false);
            PlaceViewAdapter.PlaceViewHolder cvh = new PlaceViewAdapter.PlaceViewHolder(view);
            return cvh;
        }


    @Override
        public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {

            holder.placeName.setText(place.get(position).getPlaceName());
            holder.placeCatagory.setText(place.get(position).getPlaceCatagory());
            holder.placeImages.setImageResource(place.get(position).getPlaceImage());
        }

        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return place.size();
        }


    }