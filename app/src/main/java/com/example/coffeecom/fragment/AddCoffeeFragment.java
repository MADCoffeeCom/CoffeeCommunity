package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.query.QueryCoffee;


public class AddCoffeeFragment extends Fragment {

    private TextView coffeeNameTextBox, coffeeDescTextBox, coffeePriceTextBox, coffeeTypeTextBox, coffeeIngredientsTextBox;
    private TextView titleCoffeeText;
    private Button addCoffeeBtn;
    private ImageButton backBtn;
    boolean isEdit = false;
    CoffeeModel currentCoffee;
    int currentCoffeeIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_coffee,container,false);
        initialiseId(view);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            isEdit = true;
            for (int i = 0; i < Provider.getCoffees().size(); i++) {
                if(Provider.getCoffees().get(i).getCoffeeId().equals(bundle.getString("coffeeId"))){
                    currentCoffeeIndex = i;
                    currentCoffee = Provider.getCoffees().get(i);
                }
            }
        }

        if(isEdit){
            titleCoffeeText.setText("Edit Coffee");
            addCoffeeBtn.setText("Edit Coffee");
            coffeeNameTextBox.setText(currentCoffee.getCoffeeTitle());
            coffeeDescTextBox.setText(currentCoffee.getCoffeeDesc());
            coffeePriceTextBox.setText(currentCoffee.getCoffeePrice() + "");
            coffeeTypeTextBox.setText(currentCoffee.getCoffeeType());
            coffeeIngredientsTextBox.setText(currentCoffee.getIngredients());
        }

        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        addCoffeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) updateNewCoffee();
                else addNewCoffee();
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
        titleCoffeeText = view.findViewById(R.id.titleCoffeeText);
        addCoffeeBtn = view.findViewById(R.id.addCoffeeBtn);
        backBtn = view.findViewById(R.id.backBtn);
    }

    private void addNewCoffee() {
        int min = 1; // Minimum value of range
        int max = 12; // Maximum value of range
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);

        String coffeePicUrl = "coffee" + random_int;
        String coffeeTitle = String.valueOf(coffeeNameTextBox.getText());
        String coffeeDesc = String.valueOf(coffeeDescTextBox.getText());
        String coffeeType = String.valueOf(coffeeTypeTextBox.getText());
        double coffeePrice = Double.parseDouble(String.valueOf(coffeePriceTextBox.getText()));
        String coffeeIngredients = String.valueOf(coffeeIngredientsTextBox.getText());
        QueryCoffee.addCoffee(coffeeTitle, coffeePicUrl, coffeeDesc, coffeeType, String.valueOf(coffeePrice), coffeeIngredients);
        Toast.makeText(getContext(), "Add Coffee success!", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }

    private void updateNewCoffee() {
        String coffeePicUrl = currentCoffee.getCoffeePic();
        String coffeeTitle = String.valueOf(coffeeNameTextBox.getText());
        String coffeeDesc = String.valueOf(coffeeDescTextBox.getText());
        String coffeeType = String.valueOf(coffeeTypeTextBox.getText());
        double coffeePrice = Double.parseDouble(String.valueOf(coffeePriceTextBox.getText()));
        String coffeeIngredients = String.valueOf(coffeeIngredientsTextBox.getText());
        QueryCoffee.updateCoffee(coffeeTitle, coffeePicUrl, coffeeDesc, coffeeType, String.valueOf(coffeePrice), coffeeIngredients, currentCoffee.getCoffeeId());
        Toast.makeText(getContext(), "Edit Coffee success!", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }

}