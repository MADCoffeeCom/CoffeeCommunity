package com.example.coffeecom.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.OrderModel;

import java.util.ArrayList;

public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.ViewHolder>{

    ArrayList<OrderModel> pendingOrder;

    public PendingOrderAdapter(ArrayList<OrderModel> pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView orderCoffeePic;
        private TextView orderCoffeeName, orderCustomerName, orderCoffeeTime;
        private ImageButton acceptOrderBtn, declineOrderBtn;
        private ConstraintLayout coffeeOrderLayout;

        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            //no Order
            if(viewType == 0){
                coffeeOrderLayout= itemView.findViewById(R.id.coffeeOrderLayout);
            }
            //Pending order
            else if(viewType == 1){
                orderCoffeePic= itemView.findViewById(R.id.orderCoffeePic);
                orderCoffeeName= itemView.findViewById(R.id.orderCoffeeName);
                orderCustomerName= itemView.findViewById(R.id.orderCustomerName);
                orderCoffeeTime= itemView.findViewById(R.id.orderCoffeeTime);
                acceptOrderBtn= itemView.findViewById(R.id.acceptOrderBtn);
                declineOrderBtn= itemView.findViewById(R.id.declineOrderBtn);
                coffeeOrderLayout= itemView.findViewById(R.id.coffeeOrderLayout);
            }
            //Accepted Order
            else{
                orderCoffeePic= itemView.findViewById(R.id.orderCoffeePic);
                orderCoffeeName= itemView.findViewById(R.id.orderCoffeeName);
                orderCustomerName= itemView.findViewById(R.id.orderCustomerName);
                orderCoffeeTime= itemView.findViewById(R.id.orderCoffeeTime);
                coffeeOrderLayout= itemView.findViewById(R.id.coffeeOrderLayout);
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(pendingOrder.isEmpty()){
            return 0; // No order
        } else {
            if(pendingOrder.get(position).getOrderStatus().equals("pending")){
                return 1; // pending and await accept or decline
            }else {
                return 2; // accepted
            }
        }
    }

    @NonNull
    @Override
    public PendingOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_no_coffee_order, parent, false);
            return new PendingOrderAdapter.ViewHolder(inflate, viewType);
        }else if (viewType == 1){
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pending_coffee_order, parent, false);
            return new PendingOrderAdapter.ViewHolder(inflate, viewType);
        }else{
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_accepted_coffee_order, parent, false);
            return new PendingOrderAdapter.ViewHolder(inflate, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrderAdapter.ViewHolder holder, int position) {
        if(pendingOrder.isEmpty()){
            holder.coffeeOrderLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } else {
            //coffee title
            holder.orderCoffeeName.setText(pendingOrder.get(position).getOrderedCoffee().get(0).getCoffeeType());

            //customer name
            String userId = pendingOrder.get(position).getUserId();

            String userName = "";
            holder.orderCustomerName.setText(userName);

            //time
            holder.orderCoffeeName.setText(pendingOrder.get(position).getOrderStartTime().toString());

            //Insert pic
            String picUrl = pendingOrder.get(position).getOrderedCoffee().get(0).getCoffeePic();
            int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
            Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.orderCoffeePic);

            if(pendingOrder.get(position).getOrderStatus().equals("pending")){

                holder.acceptOrderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Provider.getUser().getPendingOrder().get(holder.getAdapterPosition()).setOrderStatus("accepted");
                        //Notify change listener
                    }
                });

                holder.declineOrderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Provider.getUser().getPendingOrder().get(holder.getAdapterPosition()).setOrderStatus("declined");
                        Provider.getUser().getPendingOrder().remove(holder.getAdapterPosition());
                        //Notify change listener
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return pendingOrder.size();
    }



}
