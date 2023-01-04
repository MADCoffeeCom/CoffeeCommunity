package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;


public class CoffeeWalletFragment extends Fragment {

    TextView walletBalanceTextFragment;
    Button topUpWalletBtnFragment;
    ConstraintLayout walletCard;


    public CoffeeWalletFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coffee_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        walletBalanceTextFragment = view.findViewById(R.id.walletBalanceTextFragment);
        topUpWalletBtnFragment = view.findViewById(R.id.topUpWalletBtnFragment);
        walletCard = view.findViewById(R.id.walletCard);

        try{
            walletBalanceTextFragment.setText(String.valueOf(Provider.getUser().getWalletBalance()));
        }catch (NullPointerException e){
            walletBalanceTextFragment.setText("0.00");
        }

        topUpWalletBtnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                WalletFragment walletFragment = new WalletFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,walletFragment).addToBackStack(null).commit();
            }
        });
    }

}