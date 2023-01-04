package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.adapter.PendingOrderAdapter;
import com.example.coffeecom.model.OrderModel;

import java.util.ArrayList;


public class BaristaFragment extends Fragment {

    private TextView noCoffeeSoldErrorText;

    RecyclerView pendingOrderRecycleView, coffeeSellingRecycleView;
    RecyclerView.Adapter pendingOrderRecycleViewAdapter, coffeeSellingRecycleViewAdapter;

    ArrayList<String> coffeeType = new ArrayList<>();
    ArrayList<String> coffeePic = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_barista,container,false);

        noCoffeeSoldErrorText = view.findViewById(R.id.noCoffeeSoldErrorText);
        noCoffeeSoldErrorText.setVisibility(View.GONE);

        pendingOrderRecycleView = view.findViewById(R.id.pendingOrderRecycleView);
        coffeeSellingRecycleView = view.findViewById(R.id.coffeeSellingRecycleView);



//        ArrayList<CoffeeModel> coffees = Provider.getUser().getSellingCoffee();
//        for (int i = 0; i < coffees.size(); i++) {
//            String currentCoffeeType = coffees.get(i).getCoffeeType();
//            if(!coffeeType.contains(currentCoffeeType)){
//                coffeeType.add(currentCoffeeType);
//                coffeePic.add(coffees.get(i).getCoffeePic());
//            }
//        }

        recyclerViewCoffeeType();
        recyclerViewPendingOrder();


        // Inflate the layout for this fragment
        return view;
    }

    private void recyclerViewPendingOrder() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        pendingOrderRecycleView.setLayoutManager(linearLayoutManager);
//        try {
//            Provider.getUser().getPendingOrder();
//            pendingOrderRecycleViewAdapter = new PendingOrderAdapter(Provider.getUser().getPendingOrder());
        pendingOrderRecycleViewAdapter = new PendingOrderAdapter(new ArrayList<OrderModel>());
        pendingOrderRecycleView.setAdapter(pendingOrderRecycleViewAdapter);
//        }catch(NullPointerException e){
//            pendingOrderRecycleView.setAdapter(new PendingOrderAdapter(new ArrayList<OrderModel>()));
//        }
    }

    private void recyclerViewCoffeeType() {
        if(coffeeType.isEmpty()) {
            noCoffeeSoldErrorText.setVisibility(View.VISIBLE);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            coffeeSellingRecycleView.setLayoutManager(linearLayoutManager);

            coffeeSellingRecycleViewAdapter = new CoffeeTypeAdapter(coffeeType, coffeePic);
            coffeeSellingRecycleView.setAdapter(coffeeSellingRecycleViewAdapter);
        }
    }
}