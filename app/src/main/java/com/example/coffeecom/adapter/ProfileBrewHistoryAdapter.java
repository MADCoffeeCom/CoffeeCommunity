package com.example.coffeecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.model.ProfileBrewHistoryModel;
import com.example.coffeecom.R;

public class ProfileBrewHistoryAdapter extends RecyclerView.Adapter<ProfileBrewHistoryAdapter.ViewHolder> {

    // 1 - Data Source
    // Currently use dummy data

    private ProfileBrewHistoryModel[] myBrewList;

    public ProfileBrewHistoryAdapter(ProfileBrewHistoryModel[] myBrewList){
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

        final ProfileBrewHistoryModel myOrderListData = myBrewList[position];
        holder.brewTotalPrice.setText(myBrewList[position].getBrewHistoryTotalPrice());
        holder.brewAmount.setText(myBrewList[position].getBrewHistoryAmount());
        holder.brewDateTime.setText((CharSequence) myBrewList[position].getBrewHistoryDateTime());
        holder.brewImage.setImageResource(myBrewList[position].getBrewHistoryImage());

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
