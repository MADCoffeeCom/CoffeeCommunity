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
import com.example.coffeecom.adapter.TransactionAdapter;
import com.example.coffeecom.model.TransactionModel;

import java.util.ArrayList;


public class TransactionFragment extends Fragment {

    TextView walletAmountText2, noTransactionErrorText;
    RecyclerView transactionRecyclerView;
    RecyclerView.Adapter transactionRecyclerViewAdapter;
    ImageButton backBtn;

    ArrayList<TransactionModel> transactions = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_transaction,container,false);

        transactionRecyclerView = view.findViewById(R.id.transactionRecyclerView);

        walletAmountText2 = view.findViewById(R.id.walletAmountText2);
        noTransactionErrorText = view.findViewById(R.id.noTransactionErrorText);

        backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().popBackStack();
            }
        });

        for (int i = 0; i < Provider.getTransactions().size(); i++) {
            if(Provider.getUser().getUserId().equals(Provider.getTransactions().get(i).getReceiverId()) || Provider.getUser().getUserId().equals(Provider.getTransactions().get(i).getSenderId())){
                transactions.add(Provider.getTransactions().get(i));
            }
        }

        recyclerViewTransactions();


        // Inflate the layout for this fragment
        return view;
    }

    private void recyclerViewTransactions() {

        if(transactions.isEmpty()){
            noTransactionErrorText.setVisibility(View.VISIBLE);

        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            transactionRecyclerView.setLayoutManager(linearLayoutManager);

            transactionRecyclerViewAdapter = new TransactionAdapter(transactions);
            transactionRecyclerView.setAdapter(transactionRecyclerViewAdapter);
        }
    }
}