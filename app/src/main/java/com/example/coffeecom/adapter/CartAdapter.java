package com.example.coffeecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.model.CartCardModel;
import com.example.coffeecom.model.CartModel;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<CartModel> cartModelList;
    private LayoutInflater inflater;
    private Context context;
    private RecyclerView recyclerViewCartCardList;
    private RecyclerView.Adapter recyclerViewCartCardListAdapter;

    public CartAdapter(Context context, ArrayList<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewholder_coffee_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartModel cartModel = cartModelList.get(position);
        holder.bindData(cartModel);
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView baristaAddress;
        RecyclerView coffeeCartCardRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            baristaAddress = itemView.findViewById(R.id.baristaAddressText);
            coffeeCartCardRecyclerView = itemView.findViewById(R.id.coffeeCartCardRecyclerView);
        }

        public void bindData(CartModel cartModel) {
            baristaAddress.setText(cartModel.getBaristaAddress());

            CartCardAdapter cartCardAdapter = new CartCardAdapter(context, (ArrayList<CartCardModel>) cartModel.getCartCardModelsList());
            coffeeCartCardRecyclerView.setAdapter(cartCardAdapter);
            coffeeCartCardRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        }
    }

    private void recyclerViewCardCard() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCartCardList.setLayoutManager(linearLayoutManager);
        recyclerViewCartCardListAdapter = new CartCardAdapter(context, cartModelList.get(0).getCartCardModelsList());
        recyclerViewCartCardList.setAdapter(recyclerViewCartCardListAdapter);
    }
}

