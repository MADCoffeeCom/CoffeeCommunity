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
import com.example.coffeecom.model.OrderModel;
import com.example.coffeecom.R;

import java.text.DecimalFormat;

public class ProfileBrewHistoryAdapter extends RecyclerView.Adapter<ProfileBrewHistoryAdapter.ViewHolder> {

    // 1 - Data Source
    // Currently use dummy data

    private OrderModel[] myBrewList;

    public ProfileBrewHistoryAdapter(OrderModel[] myBrewList){
        this.myBrewList = myBrewList;
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

//            itemView.setOnClickListener((View.OnClickListener) this);
        }

    }

    // 3 - Implementing the methods

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

        final OrderModel myBrewListData = myBrewList[position];

        //To format the double decimal, to prevent the 00 to be eaten
        DecimalFormat df = new DecimalFormat(".00");

        holder.brewTotalPrice.setText("RM"+ df.format(myBrewList[position].getOrderTotalPrice()));
        holder.brewAmount.setText(myBrewList[position].getOrderHistoryTotalItems());
        holder.brewDateTime.setText((CharSequence) myBrewList[position].getOrderHistoryDateTime());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(myBrewList[position].getOrderHistoryImage(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.brewImage);

//        holder.brewHistoryCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "HMMM STILL DOING", Toast.LENGTH_SHORT).show();
//                //input code to open details coffee order page
//                //havent complete
////                Intent intent = new Intent(holder.itemView.getContext(), BaristaListActivity.class);
////                holder.itemView.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return myBrewList.length;
    }

    // 4 - On Click
}
