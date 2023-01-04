package com.example.coffeecom.adapter;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.activity.CoffeeDetailsActivity;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;

import java.util.ArrayList;

public class CoffeeBaristaListAdapter extends RecyclerView.Adapter<CoffeeBaristaListAdapter.ViewHolder>{

    ArrayList<CoffeeModel> coffees;
    ArrayList<BaristaModel> baristaWithCoffee;
    BaristaModel currentBarista;
    char mode; //b for in barista or c for in coffee view


    public CoffeeBaristaListAdapter(ArrayList<CoffeeModel> coffeesWithType, ArrayList<BaristaModel> baristaWithCoffee, char mode) {
        this.coffees = coffeesWithType;
        this.baristaWithCoffee = baristaWithCoffee;
        this.mode = mode;
    }

    public CoffeeBaristaListAdapter(BaristaModel currentBarista, ArrayList<CoffeeModel> coffee, char mode) {
        this.coffees = coffee;
        this.currentBarista = currentBarista;
        this.mode = mode;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView coffeeListPic, addToCartBtn;
        TextView cardTitle, coffeeDesc, coffeePricePerItemText, baristaLocationText;
        ConstraintLayout coffeeCardListHorizontal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            coffeeListPic = itemView.findViewById(R.id.coffeeListPic);
            cardTitle = itemView.findViewById(R.id.titleTextHoriCard);
            coffeeDesc = itemView.findViewById(R.id.descTextHoriCard);
            coffeePricePerItemText = itemView.findViewById(R.id.coffeePricePerItemText);
            baristaLocationText = itemView.findViewById(R.id.baristaLocationText);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
            coffeeCardListHorizontal = itemView.findViewById(R.id.coffeeBaristaListHoriCard);
        }
    }

    @NonNull
    @Override
    public CoffeeBaristaListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_coffee_barista_list_horizontal, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeBaristaListAdapter.ViewHolder holder, int position) {

        if (mode == 'b'){
            onBindWhenBaristaView(holder, position);
        }else{
            onBindWhenCoffeeView(holder, position);
        }

        //coffee pic
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(coffees.get(position).getCoffeePic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeeListPic);
    }

    private void onBindWhenCoffeeView(ViewHolder holder, int position) {
        holder.cardTitle.setText(toTitleCase(baristaWithCoffee.get(position).getUserName()));
        holder.coffeeDesc.setText(coffees.get(position).getCoffeeDesc());
        holder.coffeePricePerItemText.setText(String.valueOf(coffees.get(position).getCoffeePrice()));
        holder.baristaLocationText.setText(baristaWithCoffee.get(position).getUserTaman());

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //straight add to cart & make toast
            }
        });

        holder.coffeeCardListHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to details coffee view
                Provider.setCurrentCoffeeId(coffees.get(position).getCoffeeId());
                Intent intent = new Intent(holder.itemView.getContext(), CoffeeDetailsActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        //coffee pic
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(coffees.get(position).getCoffeePic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeeListPic);
    }

    private void onBindWhenBaristaView(@NonNull CoffeeBaristaListAdapter.ViewHolder holder, int position) {
        holder.cardTitle.setText(coffees.get(position).getCoffeeTitle());
        holder.coffeeDesc.setText(coffees.get(position).getCoffeeDesc());
        holder.coffeePricePerItemText.setText(String.valueOf(coffees.get(position).getCoffeePrice()));
        holder.baristaLocationText.setText(currentBarista.getUserTaman());

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //straight add to cart & make toast
            }
        });

        holder.coffeeCardListHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to details coffee view
//                Provider.setCurrentCoffeeId(currentBarista.getSellingCoffee().get(position).getCoffeeId());
                Intent intent = new Intent(holder.itemView.getContext(), CoffeeDetailsActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        //coffee pic
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(currentBarista.getSellingCoffee().get(position).getCoffeePic(), "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.coffeeListPic);
    }

    @Override
    public int getItemCount() {
        return coffees.size();
    }
}


