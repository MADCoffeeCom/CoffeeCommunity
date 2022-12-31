package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;

public class StatusActivity extends AppCompatActivity {

    TextView statusText, statusHeading1Text, statusHeading2Text;
    Button statusBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        statusText = findViewById(R.id.statusText);
        statusHeading1Text = findViewById(R.id.statusHeading1Text);
        statusHeading2Text = findViewById(R.id.statusHeading2Text);
        statusBtn = findViewById(R.id.statusBtn);

        statusText.setText(Provider.getStatusTitle());
        statusHeading1Text.setText(Provider.getStatusHeading1());
        statusHeading2Text.setText(Provider.getStatusHeading2());
        statusBtn.setText(Provider.getStatusBtnText());
        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                startActivity(new Intent(StatusActivity.this, Provider.getRedirectedCls().getClass()));
            }
        });
    }
}