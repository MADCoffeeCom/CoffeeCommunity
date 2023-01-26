package com.example.coffeecom.adapter;

import static com.example.coffeecom.helper.FormatDateTime.convertDatetoStringDate;

import android.content.Context;
import android.os.Bundle;
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
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.fragment.LearnDetailsFragment;
import com.example.coffeecom.fragment.OrderHistoryDetailsFragment;
import com.example.coffeecom.model.BrewedOrderModel;
import com.example.coffeecom.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProfileBrewHistoryAdapter extends RecyclerView.Adapter<ProfileBrewHistoryAdapter.ViewHolder> {

    ArrayList<BrewedOrderModel> brewedHistory;
    Context activity;

    public ProfileBrewHistoryAdapter(ArrayList<BrewedOrderModel> brewedHistory, Context activity){
        this.brewedHistory = brewedHistory;
        this.activity = activity;
    }

    // 2 - View Holder Class
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
    @NonNull
    @Override
    public ProfileBrewHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.viewholder_coffee_type_small, parent, false);    //think inflate as display
        ProfileBrewHistoryAdapter.ViewHolder viewHolder = new ProfileBrewHistoryAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileBrewHistoryAdapter.ViewHolder holder, int position) {

        String picUrl = brewedHistory.get(position).getOrderedCoffee().get(0).getCoffeePic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeeCardImage);

        holder.coffeeCardTitle.setText("" + brewedHistory.get(position).getOrderedCoffee().get(0).getCoffeeTitle());

        holder.coffeeTypeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("orderId", brewedHistory.get(position).getOrderId());
                bundle.putString("type", "brew");
                ((BottomNavigationActivity)activity).replaceFragmentWithData(new OrderHistoryDetailsFragment(), bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brewedHistory.size();
    }

}
