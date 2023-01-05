package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.CoffeeBaristaListAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;

import java.util.ArrayList;


public class CoffeeListFragment extends Fragment {

    RecyclerView coffeeListInBaristaRecyclerView;
    private RecyclerView.Adapter coffeeListInBaristaAdapter;
    ArrayList<CoffeeModel> coffeesWithType = new ArrayList<>();
    ArrayList<BaristaModel> baristaWithCoffee = new ArrayList<>();
    TextView coffeeTypeText;
    ImageButton backBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_coffee_list,container,false);

        coffeeListInBaristaRecyclerView = view.findViewById(R.id.coffeeListInBaristaRecyclerView);
        backBtn = view.findViewById(R.id.backBtn);
        coffeeTypeText = view.findViewById(R.id.coffeeTypeText);

        coffeeTypeText.setText(Provider.getCurrentCoffeeType());
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomNavigationActivity)getActivity()).onBackPressed();
            }

        });


        getCoffeeTogetherWithBaristas();
        recyclerViewCoffee();


        // Inflate the layout for this fragment
        return view;
    }

    private void recyclerViewCoffee() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        coffeeListInBaristaRecyclerView.setLayoutManager(linearLayoutManager);

        coffeeListInBaristaAdapter = new CoffeeBaristaListAdapter(coffeesWithType, baristaWithCoffee, 'c', getActivity());
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