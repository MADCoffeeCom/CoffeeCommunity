package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;

public class WalletActivatedFragment extends Fragment {
    private Button activateWalletBackBtn;
    private ImageButton activateWalletBckBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activate_wallet,container,false);

        activateWalletBackBtn = view.findViewById(R.id.activateWalletBackBtn);
        activateWalletBckBtn = view.findViewById(R.id.activateWalletBckBtn);

        activateWalletBckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomNavigationActivity)getActivity()).onBackPressed();
            }
        });

        activateWalletBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomNavigationActivity)getActivity()).replaceFragment(new WalletFragment());
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet_activated, container, false);
    }
}