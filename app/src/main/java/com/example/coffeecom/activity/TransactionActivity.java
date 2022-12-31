package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.TransactionAdapter;
import com.example.coffeecom.model.TransactionModel;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    TextView walletAmountText2, noTransactionErrorText;
    RecyclerView transactionRecyclerView;
    RecyclerView.Adapter transactionRecyclerViewAdapter;
    ImageButton backBtn;

    ArrayList<TransactionModel> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        walletAmountText2 = findViewById(R.id.walletAmountText2);
        noTransactionErrorText = findViewById(R.id.noTransactionErrorText);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> finish());

        for (int i = 0; i < Provider.getTransactions().size(); i++) {
            if(Provider.getUser().getUserId().equals(Provider.getTransactions().get(i).getReceiverId()) || Provider.getUser().getUserId().equals(Provider.getTransactions().get(i).getSenderId())){
                transactions.add(Provider.getTransactions().get(i));
            }
        }

        recyclerViewTransactions();
    }

    private void recyclerViewTransactions() {

        if(transactions.isEmpty()){
            noTransactionErrorText.setVisibility(View.VISIBLE);

        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            transactionRecyclerView = findViewById(R.id.transactionRecyclerView);
            transactionRecyclerView.setLayoutManager(linearLayoutManager);

            transactionRecyclerViewAdapter = new TransactionAdapter(transactions);
            transactionRecyclerView.setAdapter(transactionRecyclerViewAdapter);
        }
    }
}