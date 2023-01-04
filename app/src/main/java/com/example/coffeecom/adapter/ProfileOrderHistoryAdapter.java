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
import com.example.coffeecom.model.OrderModel;
import com.example.coffeecom.R;

import java.text.DecimalFormat;

public class ProfileOrderHistoryAdapter extends RecyclerView.Adapter<ProfileOrderHistoryAdapter.ViewHolder> {

    // 1 - Data Source
    // Currently use dummy data

    private OrderModel[] myOrderList;

    public ProfileOrderHistoryAdapter(OrderModel[] myOrderList){
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

        final OrderModel myOrderListData = myOrderList[position];

        //To format the double decimal, to prevent the 00 to be eaten
        DecimalFormat df = new DecimalFormat(".00");

        holder.orderTotalPrice.setText("RM"+ df.format(myOrderList[position].getOrderTotalPrice()));
        holder.orderAmount.setText(myOrderList[position].getOrderHistoryTotalItems());
        holder.orderDateTime.setText(myOrderList[position].getOrderHistoryDateTime());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(myOrderList[position].getOrderHistoryImage(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.orderImage);

        holder.orderHistoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "HMMM STILL DOING", Toast.LENGTH_SHORT).show();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                //the page havent done yet
//                AddBankCardFragment addBankCard = new AddBankCardFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,addBankCard).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return myOrderList.length;
    }

    // 4 - On Click
}
