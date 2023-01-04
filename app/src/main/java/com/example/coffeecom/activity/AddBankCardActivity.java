package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.BankCardModel;
import com.google.android.material.badge.BadgeUtils;

import java.util.Scanner;

public class AddBankCardActivity extends AppCompatActivity {

    TextView cardNumberTextBox, nameOnCardTextBox, ccvTextBox, expiryDateTextBox, bankNameText;
    Button addCardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);

        addCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BankCardModel card = new BankCardModel(
                        Integer.valueOf((String) cardNumberTextBox.getText()),
                        String.valueOf(nameOnCardTextBox.getText()),
                        Integer.valueOf((String)ccvTextBox.getText()),
                        Integer.valueOf((String)expiryDateTextBox.getText()),
                        String.valueOf(bankNameText.getText()));

                Provider.getUser().addBankCard(card);
                //notify to update to sql server
            }
        });
    }


}