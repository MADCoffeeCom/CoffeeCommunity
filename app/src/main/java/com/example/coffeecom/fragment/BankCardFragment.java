package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.BankCardAdapter;


public class BankCardFragment extends Fragment {

    private static final String TAG = "BankCardFragment";

    private RecyclerView recyclerViewBankCardCardList;
    private ImageButton backBtn, addBankCardBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_bank_card,container,false);

        initialiseID(view);
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
        addBankCardBtn.setOnClickListener(view12 -> ((BottomNavigationActivity)getActivity()).replaceFragment(new AddBankCardFragment()));
        recyclerViewBankCard();

        return view;
    }

    private void initialiseID(View view) {
        recyclerViewBankCardCardList = view.findViewById(R.id.bankCardCardRecycleView);
        addBankCardBtn = view.findViewById(R.id.addBankCardBtn);
        backBtn = view.findViewById(R.id.backBtn);
    }

    private void recyclerViewBankCard() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewBankCardCardList.setLayoutManager(linearLayoutManager);
        recyclerViewBankCardCardList.setAdapter(new BankCardAdapter(Provider.getUser().getBankCard(), getActivity()));
        Log.i(TAG, "recyclerViewBankCard: " + Provider.getUser().getBankCard().size());
    }

}