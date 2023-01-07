package com.example.coffeecom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.adapter.BankCardAdapter;
import com.example.coffeecom.adapter.LearnArticleAdapter;


public class BankCardFragment extends Fragment {
    private RecyclerView recyclerViewBankCardCardList;
    private RecyclerView.Adapter bankCardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_bank_card,container,false);

        initialiseID(view);
    //    recyclerViewBankCardCard();

        // Inflate the layout for this fragment
        return view;
    }

    private void initialiseID(View view) {
        recyclerViewBankCardCardList = view.findViewById(R.id.bankCardCardRecycleView);
    }

    private void recyclerViewBankCardCard() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewBankCardCardList.setLayoutManager(linearLayoutManager);

    //    bankCardAdapter = new BankCardAdapter();
        recyclerViewBankCardCardList.setAdapter(bankCardAdapter);
    }

}