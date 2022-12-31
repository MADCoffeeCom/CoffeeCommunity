package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;

public class WalletPinActivity extends AppCompatActivity {

    TextView pinNumberTextBox, errorPinText;
    Button nextBtnPin;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_pin);

        nextBtnPin=findViewById(R.id.nextBtnPin);
        pinNumberTextBox=findViewById(R.id.pinNumberTextBox);
        backBtn=findViewById(R.id.backBtn);
        errorPinText=findViewById(R.id.errorPinText);
        errorPinText.setVisibility(View.GONE);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextBtnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Provider.getUser().getWalletPin() == Double.parseDouble((String) pinNumberTextBox.getText())){

                    Provider.getUser().setWalletBalance(Provider.getUser().getWalletBalance() + Double.parseDouble((String) pinNumberTextBox.getText()));

                    Provider.setStatusTitle("Top Up Successful");
                    Provider.setStatusHeading1("Top Up Successfully!");
                    Provider.setStatusHeading2("");
                    Provider.setStatusBtnText("Back to wallet");
                    Provider.setRedirectedCls(WalletActivity.class);

                    startActivity(new Intent(WalletPinActivity.this, StatusActivity.class));
                }else{
                    errorPinText.setVisibility(View.VISIBLE);
                    pinNumberTextBox.setText("");
                }
            }
        });

    }
}