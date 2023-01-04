package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.BaristaCardAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.adapter.LearnArticleAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.BaristaRatingModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.IngredientsModel;

import java.util.ArrayList;


public class HomeActivityFragment extends Fragment {

    private RecyclerView recyclerViewCoffeeTypeList, recyclerViewBaristaList;
    private RecyclerView.Adapter coffeeTypeAdapter, baristaAdapter;

    ArrayList<String> coffeeType = new ArrayList<>();
    ArrayList<String> coffeePic = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initialiseData();

        View view = inflater.inflate(R.layout.activity_home,container,false);

        recyclerViewCoffeeTypeList = view.findViewById(R.id.coffeeListInBaristaRecyclerView);
        recyclerViewBaristaList = view.findViewById(R.id.baristaRecyclerView);


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

        return view;
    }

        private void initialiseData() {
        ArrayList<IngredientsModel> ings = new ArrayList<>();
        ArrayList<IngredientsModel> ings2 = new ArrayList<>();

        ings.add(new IngredientsModel("milk", "Come from female cow"));
        ings.add(new IngredientsModel("water", "Purity with 100% oil"));
        ings.add(new IngredientsModel("coffee bean", "Comes from Malaysia"));

        ings2.add(new IngredientsModel("coffee bean", "Comes from Malaysia"));
        ings2.add(new IngredientsModel("water", "Purity with 100% oil"));

        ArrayList<CoffeeModel> coffee = new ArrayList<>();

//        CoffeeModel latte = new CoffeeModel("coffee1", "gorgeous latte", "this is a haha latte", "latte", 9.9, ings);
//        CoffeeModel americano = new CoffeeModel("coffee1", "gorgeous americano", "this is a haha americano", "americano", 9.9, ings2);

//        coffee.add(latte);
//        coffee.add(americano);

//        Provider.addBaristas(new BaristaModel("barista1", "Kwan Yang", "He is a award winning barista", "19, Lorong 2", "Taman Counnagth", "blablabla");
//        Provider.updateCoffeeType();
    }

    private void recyclerViewCoffeeType() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeTypeList.setLayoutManager(linearLayoutManager);

        coffeeTypeAdapter = new CoffeeTypeAdapter(coffeeType, coffeePic);
        recyclerViewCoffeeTypeList.setAdapter(coffeeTypeAdapter);
    }

    private void recyclerViewBarista() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBaristaList.setLayoutManager(linearLayoutManager);

        baristaAdapter = new BaristaCardAdapter(Provider.getBaristas());
        recyclerViewBaristaList.setAdapter(baristaAdapter);
    }


}