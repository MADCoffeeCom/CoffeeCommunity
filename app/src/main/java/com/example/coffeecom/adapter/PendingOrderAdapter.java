package com.example.coffeecom.adapter;

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
import com.example.coffeecom.R;
import com.example.coffeecom.model.OrderModel;

import java.util.ArrayList;

public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.ViewHolder>{

    ArrayList<OrderModel> brewedOrder;

    public PendingOrderAdapter(ArrayList<OrderModel> brewedOrder) {
        this.brewedOrder = brewedOrder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView orderCoffeePic;
        private TextView orderCoffeeName, orderCustomerName, orderCoffeeTime;
        private ImageButton acceptOrderBtn, declineOrderBtn;
        private ConstraintLayout coffeeOrderLayout;

        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            coffeeOrderLayout= itemView.findViewById(R.id.coffeeOrderLayout);
            orderCoffeePic= itemView.findViewById(R.id.orderCoffeePic);
            orderCoffeeName= itemView.findViewById(R.id.orderCoffeeName);
            orderCustomerName= itemView.findViewById(R.id.orderCustomerName);
            orderCoffeeTime= itemView.findViewById(R.id.orderCoffeeTime);
            acceptOrderBtn= itemView.findViewById(R.id.acceptOrderBtn);
            declineOrderBtn= itemView.findViewById(R.id.declineOrderBtn);
//            //no Order
//            if(viewType == 0){
//            }
//            //Pending order
//            else if(viewType == 1){
//            }
//            //Accepted Order
//            else{
//                orderCoffeePic= itemView.findViewById(R.id.orderCoffeePic);
//                orderCoffeeName= itemView.findViewById(R.id.orderCoffeeName);
//                orderCustomerName= itemView.findViewById(R.id.orderCustomerName);
//                orderCoffeeTime= itemView.findViewById(R.id.orderCoffeeTime);
//            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(brewedOrder.isEmpty()){
            return 0; // No order
        } else {
            if(brewedOrder.get(position).getOrderStatus().equals("P")){
                return 1; // pending and await accept or decline
            }else{
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
        if(brewedOrder.isEmpty()){
            holder.coffeeOrderLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } else {
            //coffee title
            holder.orderCoffeeName.setText(brewedOrder.get(position).getOrderedCoffee().get(0).getCoffeeTitle());
            holder.orderCustomerName.setText(brewedOrder.get(position).getUserId());

            //time
            holder.orderCoffeeName.setText(brewedOrder.get(position).getOrderStartTime().toString());

            //Insert pic
            String picUrl = brewedOrder.get(position).getOrderedCoffee().get(0).getCoffeePic();
            int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
            Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.orderCoffeePic);

            if(brewedOrder.get(position).getOrderStatus().equals("pending")){

                holder.acceptOrderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Provider.getUser().getPendingOrder().get(holder.getAdapterPosition()).setOrderStatus("accepted");
                        //Notify change listener
                    }
                });

                holder.declineOrderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Provider.getUser().getPendingOrder().get(holder.getAdapterPosition()).setOrderStatus("declined");
//                        Provider.getUser().getPendingOrder().remove(holder.getAdapterPosition());
                        //Notify change listener
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return brewedOrder.size();
    }



}
