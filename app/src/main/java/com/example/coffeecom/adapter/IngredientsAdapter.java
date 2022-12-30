package com.example.coffeecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.model.IngredientsModel;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder>{

    ArrayList<IngredientsModel> ingredients;

    public IngredientsAdapter(ArrayList<IngredientsModel> ingredients) {
        this.ingredients = ingredients;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingText, ingDescText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingText = itemView.findViewById(R.id.ingText);
            ingDescText = itemView.findViewById(R.id.ingDescText);
        }
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ingredients, parent, false);
        return new IngredientsAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        holder.ingText.setText(ingredients.get(position).getIngredientTitle());
        holder.ingDescText.setText(ingredients.get(position).getIngredientDesc());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


}
