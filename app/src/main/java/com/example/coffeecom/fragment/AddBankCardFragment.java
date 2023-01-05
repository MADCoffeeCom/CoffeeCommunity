package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.BaristaCardAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.model.BankCardModel;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import java.util.ArrayList;


public class AddBankCardFragment extends Fragment {

    TextView cardNumberTextBox, nameOnCardTextBox, ccvTextBox, expiryDateTextBox, bankNameText;
    Button addCardBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_bank_card,container,false);

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

        return view;
    }
}