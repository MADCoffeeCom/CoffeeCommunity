package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.IngredientsModel;

import java.util.ArrayList;

import pub.devrel.easypermissions.EasyPermissions;
//import com.droidninja.filepicker.*;

public class AddCoffeeActivity extends AppCompatActivity {

    private TextView coffeeNameTextBox, coffeeDescTextBox, coffeePriceTextBox, coffeeTypeTextBox;
    private ImageButton addIngredientsBtn, addCoffeePicBtn;
    private Button addCoffeeBtn;
    private LinearLayout addIngredientsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coffee);



        coffeeNameTextBox = findViewById(R.id.coffeeNameTextBox);
        coffeeDescTextBox = findViewById(R.id.coffeeDescTextBox);
        coffeePriceTextBox = findViewById(R.id.coffeePriceTextBox);
        coffeeTypeTextBox = findViewById(R.id.coffeeTypeTextBox);
        addIngredientsBtn = findViewById(R.id.addIngredientsBtn);
        addCoffeePicBtn = findViewById(R.id.addCoffeePicBtn);
        addCoffeeBtn = findViewById(R.id.addCoffeeBtn);
        addIngredientsLayout = findViewById(R.id.addIngredientsLayout);

//        addCoffeePicBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String[] strings = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
//                if(EasyPermissions.hasPermissions(AddCoffeeActivity.this, strings)){
//                    imagePicker();
//                }
//            }
//        });

        addIngredientsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = LayoutInflater.from(AddCoffeeActivity.this).inflate(R.layout.viewholder_bank_card, addIngredientsLayout, false);
                addIngredientsLayout.addView(inflate);
            }
        });


        addCoffeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCoffee();
            }
        });
    }

    private void imagePicker() {
//        FilePickerBuilder.getInstance()
    }

    private void addNewCoffee() {
        ArrayList<IngredientsModel> ingredients = new ArrayList<>();

        for (int i = 0; i < addIngredientsLayout.getChildCount(); i++) {
            TextView ingredientTitleText = addIngredientsLayout.getChildAt(0).findViewById(R.id.ingredientTitleText);
            TextView ingredientDescText = addIngredientsLayout.getChildAt(0).findViewById(R.id.ingredientDescText);
            String ingredientTitle = String.valueOf(ingredientTitleText.getText());
            String ingredientDesc = String.valueOf(ingredientDescText.getText());
            ingredients.add(new IngredientsModel(ingredientTitle, ingredientDesc));
        }

        String coffeePic = "";
        String coffeeTitle = String.valueOf(coffeeNameTextBox.getText());
        String coffeeDesc = String.valueOf(coffeeDescTextBox.getText());
        String coffeeType = String.valueOf(coffeeTypeTextBox.getText());
        double coffeePrice = Double.valueOf(String.valueOf(coffeePriceTextBox.getText()));

        CoffeeModel newCoffee = new CoffeeModel(Provider.getUser().getUserId(), coffeePic, coffeeTitle, coffeeDesc, coffeeType, coffeePrice, ingredients);




    }


}