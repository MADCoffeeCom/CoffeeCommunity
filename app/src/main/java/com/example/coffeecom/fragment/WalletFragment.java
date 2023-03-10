package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.BankCardAdapter;


public class WalletFragment extends Fragment {

    TextView walletAmountText;
    Button transactionBtn, topUpWalletBtn, withdrawBtn;
    Button topUp5Btn, topUp10Btn, topUp20Btn, topUp30Btn, topUp50Btn, topUp100Btn;
    TextView topUpAmountTextBox;
    TextView errorWalletText, noBankCardErrorText;
    ImageButton backBtn, addBankCardBtn;

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
        addBankCardBtn = view.findViewById(R.id.addBankCardBtn);
        topUpAmountTextBox = view.findViewById(R.id.topUpAmountTextBox);
        withdrawBtn = view.findViewById(R.id.withdrawBtn);
        topUpAmountTextBox.setText("");

        bankCardRecyclerView = view.findViewById(R.id.bankCardRecyclerView);

        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
        addBankCardBtn.setOnClickListener(view13 -> ((BottomNavigationActivity)getActivity()).replaceFragment(new AddBankCardFragment()));

        try {
            walletAmountText.setText(String.format("%.2f", Provider.getUser().getWalletBalance()));

        }catch (NullPointerException e){
            walletAmountText.setText(String.valueOf(0.00));
        }

        topUpFillNumber();

        transactionBtn.setOnClickListener(view14 -> ((BottomNavigationActivity)getActivity()).replaceFragment(new TransactionFragment()));
        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("amount", topUpAmountTextBox.getText().toString());
                if (!topUpAmountTextBox.getText().toString().isEmpty()){
                    ((BottomNavigationActivity)getActivity()).replaceFragmentWithData(new WithdrawFragment(), bundle);
                }
                else{
                    Toast.makeText(getContext(), "Please enter an amount!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        recyclerViewBankCard();

        topUpWalletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d(String.valueOf(topUpAmountTextBox.getText()), "bruhbruh: ");
                Bundle bundle = new Bundle();
                bundle.putString("amount", String.valueOf(topUpAmountTextBox.getText()));
                if(Provider.getUser().getWalletPin().equals("empty")){
                    ((BottomNavigationActivity)getActivity()).replaceFragmentWithData(new WalletPinFragment(), bundle);
                }else if (!String.valueOf(topUpAmountTextBox.getText()).isEmpty()){
                    ((BottomNavigationActivity)getActivity()).replaceFragmentWithData(new WalletPinFragment(), bundle);
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
        topUp10Btn.setOnClickListener(view -> topUpAmountTextBox.setText(String.valueOf(10)));
        topUp20Btn.setOnClickListener(view -> topUpAmountTextBox.setText(String.valueOf(20)));
        topUp30Btn.setOnClickListener(view -> topUpAmountTextBox.setText(String.valueOf(30)));
        topUp50Btn.setOnClickListener(view -> topUpAmountTextBox.setText(String.valueOf(50)));
        topUp100Btn.setOnClickListener(view -> topUpAmountTextBox.setText(String.valueOf(100)));
    }

    private void recyclerViewBankCard() {
        try{
            Provider.getUser().getBankCard();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            bankCardRecyclerView.setLayoutManager(linearLayoutManager);

            bankCardRecyclerViewAdapter = new BankCardAdapter(Provider.getUser().getBankCard(), getActivity());
            bankCardRecyclerView.setAdapter(bankCardRecyclerViewAdapter);

        }catch(NullPointerException e){
            noBankCardErrorText.setVisibility(View.VISIBLE);
        }

    }
}