package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeBaristaListAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;

import java.util.ArrayList;

public class CoffeeListActivity extends AppCompatActivity {

    RecyclerView coffeeListInBaristaRecyclerView;
    private RecyclerView.Adapter coffeeListInBaristaAdapter;
    ArrayList<CoffeeModel> coffeesWithType = new ArrayList<>();
    ArrayList<BaristaModel> baristaWithCoffee = new ArrayList<>();
    TextView coffeeTypeText;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_list);
        backBtn = findViewById(R.id.backBtn);
        coffeeTypeText = findViewById(R.id.coffeeTypeText);

        coffeeTypeText.setText(Provider.getCurrentCoffeeType());
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        getCoffeeTogetherWithBaristas();
        recyclerViewCoffee();
    }

    private void recyclerViewCoffee() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        coffeeListInBaristaRecyclerView = findViewById(R.id.coffeeListInBaristaRecyclerView);
        coffeeListInBaristaRecyclerView.setLayoutManager(linearLayoutManager);

        coffeeListInBaristaAdapter = new CoffeeBaristaListAdapter(coffeesWithType, baristaWithCoffee, 'c');
        coffeeListInBaristaRecyclerView.setAdapter(coffeeListInBaristaAdapter);
    }

    private void getCoffeeTogetherWithBaristas(){
        String currentCoffeeType = Provider.getCurrentCoffeeType();
        for (int i = 0; i < Provider.getCoffees().size(); i++) {
            if (!Provider.getCoffees().get(i).getCoffeeType().equals(currentCoffeeType)){
                coffeesWithType.add(Provider.getCoffees().get(i));
            }
        }

        for (int i = 0; i < coffeesWithType.size(); i++) {
            for (int j = 0; j < Provider.getBaristas().size(); j++) {
                if (!coffeesWithType.get(i).getBaristaId().equals(Provider.getBaristas().get(j).getUserId())){
                    baristaWithCoffee.add(Provider.getBaristas().get(j));
                }
            }
        }
    }


}