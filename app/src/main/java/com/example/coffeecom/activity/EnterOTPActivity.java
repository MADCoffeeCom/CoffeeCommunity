package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.R;

public class EnterOTPActivity extends AppCompatActivity {

    TextView OTPTextBox;
    Button enterOTPBtn;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otpactivity);

        String otp = getIntent().getExtras().getString("OTP");
        String email = getIntent().getExtras().getString("email");

        OTPTextBox = findViewById(R.id.OTPTextBox);
        enterOTPBtn = findViewById(R.id.enterOTPBtn);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> finish());

        enterOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otpEntered = "" + OTPTextBox.getText();
                if(otpEntered.equals(otp)){
                    Intent intent = new Intent(EnterOTPActivity.this, EnterNewPasswordActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            }
        });
    }
}