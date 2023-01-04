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

    private TextView coffeeNameTextBox, coffeeDescTextBox, coffeePriceTextBox, coffeeTypeTextBox, coffeeIngredientsTextBox;
    private Button addCoffeeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coffee);

        coffeeNameTextBox = findViewById(R.id.coffeeNameTextBox);
        coffeeDescTextBox = findViewById(R.id.coffeeDescTextBox);
        coffeePriceTextBox = findViewById(R.id.coffeePriceTextBox);
        coffeeTypeTextBox = findViewById(R.id.coffeeTypeTextBox);
        coffeeIngredientsTextBox = findViewById(R.id.coffeeIngredientsTextBox);
        addCoffeeBtn = findViewById(R.id.addCoffeeBtn);

        addCoffeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCoffee();
            }
        });
    }

    private void addNewCoffee() {

        String coffeePicUrl = "";
        String coffeeId = "";
        String coffeeTitle = String.valueOf(coffeeNameTextBox.getText());
        String coffeeDesc = String.valueOf(coffeeDescTextBox.getText());
        String coffeeType = String.valueOf(coffeeTypeTextBox.getText());
        double coffeePrice = Double.parseDouble(String.valueOf(coffeePriceTextBox.getText()));
        String coffeeIngredients = String.valueOf(coffeeIngredientsTextBox.getText());
        String baristaId = Provider.getUser().getBaristaId();
        String baristaName = Provider.getUser().getUserName();
        String baristaTaman = Provider.getUser().getUserTaman();
        String baristaLocation = Provider.getUser().getUserLocation();

        //insert sql code to update and notify change



    }
}