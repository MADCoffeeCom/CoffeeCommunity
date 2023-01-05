package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CartAdapter;
import com.example.coffeecom.model.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CoffeeCartFragment extends Fragment {
    private RecyclerView coffeeCartCardRecyclerView;
    private CartAdapter cartAdapter;
    private ArrayList<CartModel> cartModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_coffee_cart, container, false);

        coffeeCartCardRecyclerView = rootView.findViewById(R.id.coffeeCartRecyclerView);
        coffeeCartCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        cartModelList = createCartModelList();
        cartAdapter = new CartAdapter(getContext(), cartModelList);
        coffeeCartCardRecyclerView.setAdapter(cartAdapter);

        return rootView;
    }

    private ArrayList<CartModel> createCartModelList() {
        // Code to create a list of CartModel objects goes here
        return cartModelList;
    }
}