package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.query.QueryCoffeeType;


public class AddCoffeeFragment extends Fragment {

    private TextView coffeeNameTextBox, coffeeDescTextBox, coffeePriceTextBox, coffeeTypeTextBox, coffeeIngredientsTextBox;
    private Button addCoffeeBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_coffee,container,false);

        initialiseId(view);

        addCoffeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCoffee();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void initialiseId(View view) {
        coffeeNameTextBox = view.findViewById(R.id.coffeeNameTextBox);
        coffeeDescTextBox = view.findViewById(R.id.coffeeDescTextBox);
        coffeePriceTextBox = view.findViewById(R.id.coffeePriceTextBox);
        coffeeTypeTextBox = view.findViewById(R.id.coffeeTypeTextBox);
        coffeeIngredientsTextBox = view.findViewById(R.id.coffeeIngredientsTextBox);
        addCoffeeBtn = view.findViewById(R.id.addCoffeeBtn);
    }


    private void addNewCoffee() {

        String coffeePicUrl = "coffee" + 1;
        String coffeeTitle = String.valueOf(coffeeNameTextBox.getText());
        String coffeeDesc = String.valueOf(coffeeDescTextBox.getText());
        String coffeeType = String.valueOf(coffeeTypeTextBox.getText());
        double coffeePrice = Double.parseDouble(String.valueOf(coffeePriceTextBox.getText()));
        String coffeeIngredients = String.valueOf(coffeeIngredientsTextBox.getText());
        QueryCoffeeType.addCoffee(coffeeTitle, coffeePicUrl, coffeeDesc, coffeeType, String.valueOf(coffeePrice), coffeeIngredients);
        Toast.makeText(getContext(), "Add Coffee success!", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }

}