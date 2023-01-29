package com.example.coffeecom.adapter;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.example.coffeecom.fragment.StatusFragment;
import com.example.coffeecom.helper.DownloadImageHelper;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.OrderedCoffeeModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class CoffeeOrderAdapter extends RecyclerView.Adapter<CoffeeOrderAdapter.ViewHolder>{
    private static final String TAG = "CoffeeOrderAdapter";

    ArrayList<OrderedCoffeeModel> orders;
    Context activity;

    public CoffeeOrderAdapter(ArrayList<OrderedCoffeeModel> orders, Context activity) {
        this.orders = orders;
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

    public void filterList(ArrayList<OrderedCoffeeModel> filterlist) {
        orders = filterlist;
        notifyDataSetChanged();
    }

    //Create viewholder or card based on the xml file
    @Override
    public CoffeeOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_coffee_type_small, parent, false);
        return new CoffeeOrderAdapter.ViewHolder(inflate);
    }

    //fill in the xml file with necessary information
    @Override
    public void onBindViewHolder(@NonNull CoffeeOrderAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.coffeeTitle.setText(toTitleCase(orders.get(position).getBaristaName()));
        Log.i(TAG, "onBindViewHolder: " + orders.get(position).getBaristaName());
        Log.i(TAG, "onBindViewHolder: " + orders.get(position).getOrderId());
        Log.i(TAG, "onBindViewHolder: " + orders.get(position).getOrderStatus());
        Log.i(TAG, "onBindViewHolder: " + orders.get(position).getBaristaTaman());


        Log.i(TAG, "size: " + orders.get(position).getOrderedCoffee().size());
//      code to insert picture with URL
        String picUrl = "http://" + Provider.getIpAddress() + "/images/" +  orders.get(position).getOrderedCoffee().get(0).getCoffeePic()+".jpg";
        CompletableFuture cf = null;
        try {
            DownloadImageHelper dit = new DownloadImageHelper(holder.coffeePic);
            Bitmap bitmap = cf.supplyAsync(() -> dit.execute(picUrl)).join().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

//       Code to insert picture with drawable
//        String picUrl = orders.get(position).getOrderedCoffee().get(0).getCoffeePic();
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeePic);

        holder.coffeeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle2 = new Bundle();

                String h1 = "";
                String h2 = "";
                String btnText = "Back";
                String relocate = "";
                if(orders.get(position).getOrderStatus().equals("A")){
                    h1 = "Your order is currently brewing... ";
                    h2 = "Please wait patiently.";
                }else if (orders.get(position).getOrderStatus().equals("P")){
                    h1 = "Your order is awaiting being accepted... ";
                    h2 = "Please wait patiently.";
                }else if (orders.get(position).getOrderStatus().equals("B")){
                    h1 = "Your order is ready to be taken!";
                    h2 = "Please proceed to " + orders.get(position).getBaristaTaman() + " to take your coffee.";
                    btnText = "Taken";
                    relocate = "home";
                }else if (orders.get(position).getOrderStatus().equals("D")){
                    h1 = "Your order has been declined...";
                    h2 = "WE are so sorry, please try to order from other barista...";
                }

                bundle2.putString("Title", toTitleCase(orders.get(position).getBaristaName()));
                bundle2.putString("Heading1", h1);
                bundle2.putString("Heading2", h2);
                bundle2.putString("BtnText", btnText);
                bundle2.putString("Relocate", relocate);
                bundle2.putString("orderId", orders.get(position).getOrderId());
                bundle2.putString("baristaId", orders.get(position).getBaristaId());
                Log.i(TAG, "onClick: " + orders.get(position).getOrderId());
                Log.i("CoffeeOrderAdapter", "Opening status");
                ((BottomNavigationActivity)activity).replaceFragmentWithData(new StatusFragment(), bundle2);

            }
        });
    }

    //loop for how many times
    @Override
    public int getItemCount() {
        return orders.size();
    }
}
