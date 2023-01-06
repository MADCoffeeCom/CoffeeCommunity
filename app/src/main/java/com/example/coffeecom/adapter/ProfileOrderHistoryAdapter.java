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

public class ProfileOrderHistoryAdapter extends RecyclerView.Adapter<ProfileOrderHistoryAdapter.ViewHolder> {


//    private BrewedOrderModel[] myOrderList;
//
//    public ProfileOrderHistoryAdapter(BrewedOrderModel[] myOrderList){
//        this.myOrderList = myOrderList;
//    }



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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // 4 - On Click
}
