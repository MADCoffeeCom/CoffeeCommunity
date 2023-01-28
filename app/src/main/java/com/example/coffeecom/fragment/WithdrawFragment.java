package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.helper.QueryHomePage;
import com.example.coffeecom.query.QueryTopUp;
import com.example.coffeecom.query.QueryWallet;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class WithdrawFragment extends Fragment {

    Button withdrawBtn;
    ImageButton backBtn;
    EditText bankTextBox, bankNoTextBox, nameTextBox;
    TextView withdrawAmountText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_withdraw, container, false);

        withdrawBtn = view.findViewById(R.id.withdrawBtn);
        backBtn = view.findViewById(R.id.backBtn);
        bankTextBox = view.findViewById(R.id.bankTextBox);
        bankNoTextBox = view.findViewById(R.id.bankNoTextBox);
        nameTextBox = view.findViewById(R.id.nameTextBox);
        withdrawAmountText = view.findViewById(R.id.withdrawAmountText);
        backBtn.setOnClickListener((view1) -> getActivity().onBackPressed());

        Bundle bundle = this.getArguments();
        withdrawAmountText.setText(bundle.getString("amount"));

        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bankTextBox.getText() != null || bankNoTextBox.getText() != null || nameTextBox.getText() != null){
                    String bankName = bankTextBox.getText().toString();
                    String bankNo = bankNoTextBox.getText().toString();
                    String holderName = nameTextBox.getText().toString();
                    String amount = bundle.getString("amount");

                    QueryTopUp.withdraw(Integer.parseInt(amount));
                    Log.i("withdraw","amount"+amount);
                    Toast.makeText(getContext(), "Withdraw success!", Toast.LENGTH_SHORT).show();
                    try {
                        new HomeActivityFragment.QueryWaiterHomePage().call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ((BottomNavigationActivity)getActivity()).clearBackStack();
                    ((BottomNavigationActivity)getActivity()).replaceFragment(new WalletFragment());
                }
            }
        });

        return view;
    }
    public static class QueryWaiterHomePage implements Callable<Void> {
        @Override
        public Void call() throws Exception {
            QueryHomePage.queryHomepage();
            return null;
        }
    }
}