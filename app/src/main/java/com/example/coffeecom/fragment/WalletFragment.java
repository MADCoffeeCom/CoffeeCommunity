package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.BankCardAdapter;


public class WalletFragment extends Fragment {

    TextView walletAmountText;
    Button transactionBtn, topUpWalletBtn;
    Button topUp5Btn, topUp10Btn, topUp20Btn, topUp30Btn, topUp50Btn, topUp100Btn;
    TextView topUpAmountTextBox;
    TextView errorWalletText, noBankCardErrorText;
    ImageButton backBtn;

    RecyclerView bankCardRecyclerView;
    RecyclerView.Adapter bankCardRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_wallet,container,false);

        walletAmountText = view.findViewById(R.id.walletAmountText);
        errorWalletText = view.findViewById(R.id.errorWalletText);
        noBankCardErrorText = view.findViewById(R.id.noBankCardErrorText);
        backBtn = view.findViewById(R.id.backBtn);
        transactionBtn = view.findViewById(R.id.transactionBtn);
        topUpWalletBtn = view.findViewById(R.id.topUpWalletBtn);
        topUp5Btn = view.findViewById(R.id.topUp5Btn);
        topUp10Btn = view.findViewById(R.id.topUp10Btn);
        topUp20Btn = view.findViewById(R.id.topUp20Btn);
        topUp30Btn = view.findViewById(R.id.topUp30Btn);
        topUp50Btn = view.findViewById(R.id.topUp50Btn);
        topUp100Btn = view.findViewById(R.id.topUp100Btn);
        topUpAmountTextBox = view.findViewById(R.id.topUpAmountTextBox);
        topUpAmountTextBox.setText("");

        bankCardRecyclerView = view.findViewById(R.id.bankCardRecyclerView);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        try {
            walletAmountText.setText(String.format("%.2f", Provider.getUser().getWalletBalance()));
        }catch (NullPointerException e){
            walletAmountText.setText(String.valueOf(0.00));
        }

        topUpFillNumber();

        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                TransactionFragment transactionFragment = new TransactionFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,transactionFragment).addToBackStack(null).commit();
            }
        });
        recyclerViewBankCard();

        topUpWalletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d(String.valueOf(topUpAmountTextBox.getText()), "bruhbruh: ");
                if (!String.valueOf(topUpAmountTextBox.getText()).isEmpty()){
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    WalletPinFragment walletPinFragment = new WalletPinFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,walletPinFragment).addToBackStack(null).commit();

                    Bundle bundle = new Bundle();
                    bundle.putString("amount", String.valueOf(topUpAmountTextBox.getText()));
                    walletPinFragment.setArguments(bundle);
                    //please check if this is correct

//                    Intent intent = new Intent(getContext(), WalletPinActivity.class);
//                    intent.putExtra("amount", String.valueOf(topUpAmountTextBox.getText()));
//                    startActivity(intent);
                }else{
                    errorWalletText.setVisibility(View.VISIBLE);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void topUpFillNumber() {
        topUp5Btn.setOnClickListener(view -> topUpAmountTextBox.setText(String.valueOf(5)));
        topUp10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(10));
            }
        });
        topUp20Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(20));
            }
        });
        topUp30Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(30));
            }
        });
        topUp50Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(50));
            }
        });
        topUp100Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(100));
            }
        });
    }

    private void recyclerViewBankCard() {
        try{
            Provider.getUser().getBankCard();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            bankCardRecyclerView.setLayoutManager(linearLayoutManager);

            bankCardRecyclerViewAdapter = new BankCardAdapter();
            bankCardRecyclerView.setAdapter(bankCardRecyclerViewAdapter);

        }catch(NullPointerException e){
            noBankCardErrorText.setVisibility(View.VISIBLE);
        }

    }
}