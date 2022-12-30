package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.TransactionAdapter;
import com.example.coffeecom.model.TransactionModel;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    TextView walletAmountText2;
    RecyclerView transactionRecyclerView;
    RecyclerView.Adapter transactionRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        walletAmountText2 = findViewById(R.id.walletAmountText2);
        recyclerViewTransactions();
    }

    private void recyclerViewTransactions() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        transactionRecyclerView = findViewById(R.id.transactionRecyclerView);
        transactionRecyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<TransactionModel> transactions = new ArrayList<>();
        for (int i = 0; i < Provider.getTransactions().size(); i++) {
            if(Provider.getUser().getUserId().equals(Provider.getTransactions().get(i).getReceiverId()) || Provider.getUser().getUserId().equals(Provider.getTransactions().get(i).getSenderId())){
                transactions.add(Provider.getTransactions().get(i));
            }
        }

        transactionRecyclerViewAdapter = new TransactionAdapter(transactions);
        transactionRecyclerView.setAdapter(transactionRecyclerViewAdapter);
    }
}