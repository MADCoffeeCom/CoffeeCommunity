package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.BaristaCardAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.IngredientsModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCoffeeTypeList, recyclerViewBaristaList;
    private RecyclerView.Adapter coffeeTypeAdapter, baristaAdapter;

    ArrayList<String> coffeeType = new ArrayList<>();
    ArrayList<String> coffeePic = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        initialiseData();

        //Run to get current coffee type
        ArrayList<CoffeeModel> coffees = Provider.getCoffees();
        for (int i = 0; i < coffees.size(); i++) {
            String currentCoffeeType = coffees.get(i).getCoffeeType();
            if(!coffeeType.contains(currentCoffeeType)){
                coffeeType.add(currentCoffeeType);
                coffeePic.add(coffees.get(i).getCoffeePic());
            }
        }

        recyclerViewCoffeeType();
        recyclerViewBarista();
    }

//    private void initialiseData() {
//        ArrayList<IngredientsModel> ings = new ArrayList<>();
//        ArrayList<IngredientsModel> ings2 = new ArrayList<>();
//
//        ings.add(new IngredientsModel("milk", "Come from female cow"));
//        ings.add(new IngredientsModel("water", "Purity with 100% oil"));
//        ings.add(new IngredientsModel("coffee bean", "Comes from Malaysia"));
//
//        ings2.add(new IngredientsModel("coffee bean", "Comes from Malaysia"));
//        ings2.add(new IngredientsModel("water", "Purity with 100% oil"));
//
//        ArrayList<CoffeeModel> coffee = new ArrayList<>();
//
//        CoffeeModel latte = new CoffeeModel("coffee1", "gorgeous latte", "this is a haha latte", "latte", 9.9, ings);
//        CoffeeModel americano = new CoffeeModel("coffee1", "gorgeous americano", "this is a haha americano", "americano", 9.9, ings2);
//
//        coffee.add(latte);
//        coffee.add(americano);
//
//        Provider.addBaristas(new BaristaModel("barista1", "Kwan Yang", "He is a award winning barista", "19, Lorong 2", "Taman Counnagth", 56200, "Kuala Lumpur", coffee));
////        Provider.updateCoffeeType();
//        Log.d(Provider.getBaristas().get(0).getBaristaPic(), "This is the pic");
//    }

    private void recyclerViewCoffeeType() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeTypeList = findViewById(R.id.coffeeListInBaristaRecyclerView);
        recyclerViewCoffeeTypeList.setLayoutManager(linearLayoutManager);

        coffeeTypeAdapter = new CoffeeTypeAdapter(coffeeType, coffeePic);
        recyclerViewCoffeeTypeList.setAdapter(coffeeTypeAdapter);
    }

    private void recyclerViewBarista() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBaristaList = findViewById(R.id.baristaRecyclerView);
        recyclerViewBaristaList.setLayoutManager(linearLayoutManager);

        baristaAdapter = new BaristaCardAdapter(Provider.getBaristas());
        recyclerViewBaristaList.setAdapter(baristaAdapter);
    }
}