package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.query.QueryWallet;

public class ActivateWalletFragment extends Fragment {
    private TextView activateWalletPinText;
    private TextView activateWalletPinConfirmText;
    private Button activateWalletDoneBtn;
    private ImageButton activateWalletBckBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activate_wallet,container,false);

        activateWalletPinText = view.findViewById(R.id.activateWalletPinText);
        activateWalletPinConfirmText = view.findViewById(R.id.activateWalletPinConfirmText);
        activateWalletDoneBtn = view.findViewById(R.id.activateWalletDoneBtn);
        activateWalletBckBtn = view.findViewById(R.id.activateWalletBckBtn);

        activateWalletDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activateWalletPinText.getText().equals(activateWalletPinConfirmText.getText())){
                    QueryWallet.insertWalletPin(activateWalletPinText.getText().toString());
                    Provider.getUser().setWalletPin(activateWalletPinText.getText().toString());
                    ((BottomNavigationActivity)getActivity()).replaceFragment(new WalletActivatedFragment());
                }
                else{
                    Toast.makeText(getActivity(),"Please make sure both of the field are identical", Toast.LENGTH_SHORT);
                }
            }
        });

        activateWalletBckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomNavigationActivity)getActivity()).onBackPressed();
            }
        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activate_wallet, container, false);
    }
}