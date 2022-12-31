package com.example.coffeecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.model.ProfileOrderHistoryModel;
import com.example.coffeecom.R;

public class ProfileOrderHistoryAdapter extends RecyclerView.Adapter<ProfileOrderHistoryAdapter.ViewHolder> {

    // 1 - Data Source
    // Currently use dummy data

    private ProfileOrderHistoryModel[] myOrderList;

    public ProfileOrderHistoryAdapter(ProfileOrderHistoryModel[] myOrderList){
        this.myOrderList = myOrderList;
    }



    // 2 - View Holder Class
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView orderImage;
        public TextView orderDateTime, orderTotalPrice, orderAmount;
        public ConstraintLayout orderHistoryCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.orderImage = itemView.findViewById(R.id.imgOrderHistory);
            this.orderDateTime = itemView.findViewById(R.id.txtOrderHistoryDateTime);
            this.orderTotalPrice = itemView.findViewById(R.id.txtOrderHistoryPrice);
            this.orderAmount = itemView.findViewById(R.id.txtOrderHistoryAmount);
            this.orderHistoryCard = itemView.findViewById(R.id.coffeeOrderHistoryCard);

//            itemView.setOnClickListener((View.OnClickListener) this);
        }

    }

    // 3 - Implementing the methods

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.viewholder_profile_order_history, parent, false);    //think inflate as display
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ProfileOrderHistoryModel myOrderListData = myOrderList[position];
        holder.orderTotalPrice.setText(myOrderList[position].getOrderHistoryTotalPrice());
        holder.orderAmount.setText(myOrderList[position].getOrderHistoryAmount());
        holder.orderDateTime.setText(myOrderList[position].getOrderHistoryDateTime());
        holder.orderImage.setImageResource(myOrderList[position].getOrderHistoryImage());

//        holder.orderHistoryCard.setOnClickListener(new View.OnClickListener() {
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
        return myOrderList.length;
    }

    // 4 - On Click
}
