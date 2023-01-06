package com.example.coffeecom.adapter;

import android.content.Context;
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
import com.example.coffeecom.model.OrderedCoffeeModel;

import java.util.ArrayList;

public class ProfileOrderHistoryAdapter extends RecyclerView.Adapter<ProfileOrderHistoryAdapter.ViewHolder> {

    ArrayList<OrderedCoffeeModel> orderedHistory;
    Context activity;

    public ProfileOrderHistoryAdapter(ArrayList<OrderedCoffeeModel> orderedHistory, Context activity){
        this.orderedHistory = orderedHistory;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView coffeeCardImage;
        public TextView coffeeCardTitle;
        public ConstraintLayout coffeeTypeCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.coffeeCardImage = itemView.findViewById(R.id.coffeeCardImage);
            this.coffeeCardTitle = itemView.findViewById(R.id.coffeeCardTitle);
            this.coffeeTypeCard = itemView.findViewById(R.id.coffeeTypeCard);
        }
    }

    // 3 - Implementing the methods

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.viewholder_coffee_type_small, parent, false);    //think inflate as display
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String picUrl = orderedHistory.get(position).getOrderedCoffee().get(0).getCoffeePic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeeCardImage);

        holder.coffeeCardTitle.setText("" + orderedHistory.get(position).getOrderedCoffee().get(0).getCoffeeTitle());

        holder.coffeeTypeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ((BottomNavigationActivity)activity).replaceFragment(new LearnDetailsFragment());
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderedHistory.size();
    }

    // 4 - On Click
}
