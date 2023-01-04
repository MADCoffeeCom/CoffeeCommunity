package com.example.coffeecom.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.StatusActivity;
import com.example.coffeecom.activity.WalletActivity;
import com.example.coffeecom.activity.WalletPinActivity;


public class WalletPinFragment extends Fragment {

    TextView pinNumberTextBox, errorPinText;
    Button nextBtnPin;
    ImageButton backBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_wallet_pin,container,false);

        nextBtnPin = view.findViewById(R.id.nextBtnPin);
        pinNumberTextBox = view.findViewById(R.id.pinNumberTextBox);
        backBtn = view.findViewById(R.id.backBtn);
        errorPinText = view.findViewById(R.id.errorPinText);
        errorPinText.setVisibility(View.GONE);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        nextBtnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Integer.parseInt(Provider.getUser().getWalletPin()) == Integer.parseInt((String) pinNumberTextBox.getText())){
                    Bundle bundle = getActivity().getIntent().getExtras();
                    Provider.getUser().setWalletBalance(Provider.getUser().getWalletBalance() + Integer.parseInt(bundle.getString("amount")));

                    Provider.setStatusTitle("Top Up Successful");
                    Provider.setStatusHeading1("Top Up Successfully!");
                    Provider.setStatusHeading2("");
                    Provider.setStatusBtnText("Back to wallet");
                    Provider.setRedirectedCls(WalletActivity.class);

                    startActivity(new Intent(getContext(), StatusActivity.class));
                }else{
                    errorPinText.setVisibility(View.VISIBLE);
                    pinNumberTextBox.setText("");
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}