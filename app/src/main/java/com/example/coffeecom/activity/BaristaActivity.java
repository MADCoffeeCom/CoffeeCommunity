package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.adapter.PendingOrderAdapter;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.OrderModel;

import java.util.ArrayList;

public class BaristaActivity extends AppCompatActivity {

    private TextView noCoffeeSoldErrorText;

    RecyclerView pendingOrderRecycleView, coffeeSellingRecycleView;
    RecyclerView.Adapter pendingOrderRecycleViewAdapter, coffeeSellingRecycleViewAdapter;

    ArrayList<String> coffeeType = new ArrayList<>();
    ArrayList<String> coffeePic = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barista);

        noCoffeeSoldErrorText = findViewById(R.id.noCoffeeSoldErrorText);
        noCoffeeSoldErrorText.setVisibility(View.GONE);


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


    }

    private void recyclerViewPendingOrder() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        pendingOrderRecycleView = findViewById(R.id.pendingOrderRecycleView);
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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            coffeeSellingRecycleView = findViewById(R.id.coffeeSellingRecycleView);
            coffeeSellingRecycleView.setLayoutManager(linearLayoutManager);

            coffeeSellingRecycleViewAdapter = new CoffeeTypeAdapter(coffeeType, coffeePic);
            coffeeSellingRecycleView.setAdapter(coffeeSellingRecycleViewAdapter);
        }
    }
}