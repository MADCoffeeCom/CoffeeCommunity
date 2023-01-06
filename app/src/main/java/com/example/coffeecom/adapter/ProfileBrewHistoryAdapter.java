package com.example.coffeecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.model.BrewedOrderModel;
import com.example.coffeecom.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProfileBrewHistoryAdapter extends RecyclerView.Adapter<ProfileBrewHistoryAdapter.ViewHolder> {

    ArrayList<BrewedOrderModel> brewedHistory;
    public ProfileBrewHistoryAdapter(ArrayList<BrewedOrderModel> brewedHistory){
        this.brewedHistory = brewedHistory;
    }

    // 2 - View Holder Class
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView brewImage;
        public TextView brewDateTime, brewTotalPrice, brewAmount;
        public ConstraintLayout brewHistoryCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.brewImage = itemView.findViewById(R.id.imgBrewHistory);
            this.brewDateTime = itemView.findViewById(R.id.txtBrewHistoryDateTime);
            this.brewTotalPrice = itemView.findViewById(R.id.txtBrewHistoryPrice);
            this.brewAmount = itemView.findViewById(R.id.txtBrewHistoryAmount);
            this.brewHistoryCard = itemView.findViewById(R.id.coffeeBrewHistoryCard);
        }
    }
    @NonNull
    @Override
    public ProfileBrewHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.viewholder_profile_brew_history, parent, false);    //think inflate as display
        ProfileBrewHistoryAdapter.ViewHolder viewHolder = new ProfileBrewHistoryAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileBrewHistoryAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return brewedHistory.size();
    }

}
