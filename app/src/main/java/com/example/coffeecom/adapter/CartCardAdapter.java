package com.example.coffeecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.model.CartCardModel;

import java.util.ArrayList;
import java.util.List;

public class CartCardAdapter extends RecyclerView.Adapter<CartCardAdapter.ViewHolder> {
    private ArrayList<CartCardModel> cartCardModelList;
    private LayoutInflater inflater;
    private Context context;

    public CartCardAdapter(Context context, ArrayList<CartCardModel> cartCardModelList) {
        this.context = context;
        this.cartCardModelList = cartCardModelList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewholder_coffee_cart_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartCardModel cartCardModel = cartCardModelList.get(position);
        holder.bindData(cartCardModel);
    }

    @Override
    public int getItemCount() {
        return cartCardModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView coffeePic;
        TextView coffeeName;
        TextView coffeePrice;
        TextView coffeeQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            coffeePic = itemView.findViewById(R.id.coffeeCartPic);
            coffeeName = itemView.findViewById(R.id.coffeeNameCardText);
            coffeePrice = itemView.findViewById(R.id.coffeePriceCardText);
            coffeeQuantity = itemView.findViewById(R.id.coffeeQuantityCardText);
        }

        public void bindData(CartCardModel cartCardModel) {
            coffeePic.setText(cartCardModel.getCoffeePic());
            coffeeName.setText(cartCardModel.getCoffeeName());
            coffeePrice.setText((int) cartCardModel.getCoffeePrice());
            coffeeQuantity.setText(cartCardModel.getCoffeeQuantity());
        }
    }
}
