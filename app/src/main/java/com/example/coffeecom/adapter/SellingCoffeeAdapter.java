package com.example.coffeecom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.fragment.CoffeeDetailsFragment;
import com.example.coffeecom.fragment.CoffeeListFragment;

import java.util.ArrayList;

public class SellingCoffeeAdapter extends RecyclerView.Adapter<SellingCoffeeAdapter.ViewHolder>{
    ArrayList<String> coffeeTitle;
    ArrayList<String> coffeePic;
    Context activity;

    public SellingCoffeeAdapter(ArrayList<String> coffeeTitle, ArrayList<String> coffeePic, Context activity) {
        this.coffeePic = coffeePic;
        this.coffeeTitle = coffeeTitle;
        this.activity = activity;
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
    public SellingCoffeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_coffee_type_small, parent, false);
        return new SellingCoffeeAdapter.ViewHolder(inflate);
    }

    //fill in the xml file with necessary information
    @Override
    public void onBindViewHolder(@NonNull SellingCoffeeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.coffeeTitle.setText(coffeeTitle.get(position));

        //code to insert picture
        String picUrl = coffeePic.get(position);
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeePic);

        holder.coffeeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input code here to open details coffee page
                Log.i("Coffeetype in adapter", coffeeTitle.get(position));
                Provider.setCurrentCoffeeType(coffeeTitle.get(position));

                ((BottomNavigationActivity)activity).replaceFragment(new CoffeeDetailsFragment());
            }
        });
    }

    //loop for how many times
    @Override
    public int getItemCount() {
        return coffeeTitle.size();
    }
}
