package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CartAdapter;
import com.example.coffeecom.model.CartCardModel;
import com.example.coffeecom.model.CartModel;

import java.util.ArrayList;

public class CoffeeCartFragment extends Fragment {
    private static final String TAG = "CoffeeCartFragment";
    private RecyclerView coffeeCartCardRecyclerView;
    private RecyclerView.Adapter cartAdapter;
    private ArrayList<CartModel> cartModelList = new ArrayList<>();
    private ArrayList<CartCardModel> tempCartCardModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_coffee_cart, container, false);

        coffeeCartCardRecyclerView = rootView.findViewById(R.id.coffeeCartRecyclerView);
        coffeeCartCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));



        for (String baristaId: Provider.getBaristaIdInCart()) {
            for (int i =0; i<Provider.getBaristas().size(); i++){
                if(baristaId.equals(Provider.getBaristas().get(i).getBaristaId())){
                    Log.i(TAG, "baristaId is equal to baristaId in list " + baristaId + " " + Provider.getBaristas().get(i).getBaristaId());
                    for (CartCardModel ca: Provider.getUser().getCartCard()) {
                        Log.i(TAG, "card card model found " + ca.getBaristaId() + " Current baristaId " + baristaId + Provider.getBaristas().get(i).getUserStreetNo());
                        if (ca.getBaristaId().equals(baristaId)){
                            tempCartCardModelList.add(ca);
                            Log.i(TAG, "Successfully Added CartCardModel " + tempCartCardModelList.get(0));
                        }
                    cartModelList.add(new CartModel(Provider.getBaristas().get(i), tempCartCardModelList));

                    }
                }
            }
        }
        cartAdapter = new CartAdapter(getContext(), cartModelList);
        coffeeCartCardRecyclerView.setAdapter(cartAdapter);
        cartModelList = createCartModelList();
        return rootView;
    }

    private ArrayList<CartModel> createCartModelList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        coffeeCartCardRecyclerView.setLayoutManager(linearLayoutManager);

        coffeeCartCardRecyclerView.setAdapter(cartAdapter);

        // Code to create a list of CartModel objects goes here
        return cartModelList;
    }

    private void recyclerViewCartModel(){


    }
}