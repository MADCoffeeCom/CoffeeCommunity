package com.example.coffeecom.activity;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;

public class LoginOrSignupActivity extends AppCompatActivity {
    private Button loginButton;
    private Button registerButton;
    private SharedPreferences loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Provider.setIpAddress("192.168.100.11");
        Provider.setIpAddress(Provider.getLocalIpAddress());

        setContentView(R.layout.activity_loginorregister);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin) {
//            String username = loginPreferences.getString("username", "");
//            String password = loginPreferences.getString("password", "");
            openLoginActivity();
            overridePendingTransition(0, 0);
        }

        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.signupButton);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });
    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void openRegisterActivity(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}