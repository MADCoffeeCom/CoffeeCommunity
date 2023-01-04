package com.example.coffeecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.R;
import com.example.coffeecom.model.CoffeeModel;

import java.util.ArrayList;

public class CoffeeTypeAdapter extends RecyclerView.Adapter<CoffeeTypeAdapter.ViewHolder> {

    ArrayList<String> coffeeType;
    ArrayList<String> coffeePic;

    public CoffeeTypeAdapter(ArrayList<String> coffeeType, ArrayList<String> coffeePic) {
        this.coffeePic = coffeePic;
        this.coffeeType = coffeeType;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView coffeeTitle;
        ImageView coffeePic;
        ConstraintLayout coffeeCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coffeeTitle = itemView.findViewById(R.id.coffeeCardTitle);
            coffeePic = itemView.findViewById(R.id.coffeeCardImage);
            coffeeCard = itemView.findViewById(R.id.coffeeTypeCard);
        }
    }

    //Create viewholder or card based on the xml file
    @Override
    public CoffeeTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_coffee_type_small, parent, false);
        return new ViewHolder(inflate);
    }

    //fill in the xml file with necessary information
    @Override
    public void onBindViewHolder(@NonNull CoffeeTypeAdapter.ViewHolder holder, int position) {
        holder.coffeeTitle.setText(coffeeType.get(position));

        //code to insert picture
        String picUrl = coffeePic.get(position);
        //dummy code below
        picUrl = "coffee1";
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeePic);

        holder.coffeeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input code here to open details coffee page

            }
        });
    }

    //loop for how many times
    @Override
    public int getItemCount() {
        return coffeeType.size();
    }



}
