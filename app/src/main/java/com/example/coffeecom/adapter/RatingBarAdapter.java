package com.example.coffeecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.model.BaristaRatingModel;

import java.util.ArrayList;

public class RatingBarAdapter extends RecyclerView.Adapter<RatingBarAdapter.ViewHolder> {

    ArrayList<BaristaRatingModel> ratingModels;

    public RatingBarAdapter(ArrayList<BaristaRatingModel> ratingModels) {
        this.ratingModels = ratingModels;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ratingPeopleText, ratingDescText;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingPeopleText = itemView.findViewById(R.id.ratingPeopleText);
            ratingDescText = itemView.findViewById(R.id.ratingDescText);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

    @NonNull
    @Override
    public RatingBarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_rating, parent, false);
        return new RatingBarAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingBarAdapter.ViewHolder holder, int position) {
        holder.ratingPeopleText.setText(ratingModels.get(position).getRaterName());
        holder.ratingDescText.setText(ratingModels.get(position).getRatingDesc());
        holder.ratingBar.setRating(ratingModels.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return ratingModels.size();
    }

}
