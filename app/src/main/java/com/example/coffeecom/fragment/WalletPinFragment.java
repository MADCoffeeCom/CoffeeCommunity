package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.query.QueryTopUp;


public class WalletPinFragment extends Fragment {

    private static final String TAG = "WalletPinFragment";


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
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        Bundle bundle = this.getArguments();


        nextBtnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Provider.getUser().getWalletPin().equals(String.valueOf(pinNumberTextBox.getText()))){

                    Provider.getUser().setWalletBalance(Provider.getUser().getWalletBalance() + Integer.parseInt(bundle.getString("amount")));
                    QueryTopUp.topUp(Integer.parseInt(bundle.getString("amount")));

                    Bundle bundle2 = new Bundle();
                    bundle2.putString("Title", "Top Up Success");
                    bundle2.putString("Heading1", "Top Up Successfully");
                    bundle2.putString("Heading2", "");
                    bundle2.putString("BtnText", "Back");
                    bundle2.putString("Relocate", "wallet");

                    ((BottomNavigationActivity)getActivity()).replaceFragmentWithData(new StatusFragment(), bundle2);

                }else{
                    errorPinText.setVisibility(View.VISIBLE);
                    pinNumberTextBox.setText("");
                }
            }
        });

        return view;
    }
}