package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coffeecom.R;

public class StatusActivity extends AppCompatActivity {

    TextView statusText, statusHeading1Text, statusHeading2Text;
    Button statusBtn;

    String status, statusheading1, statusheading2;
    Intent intent;

    public StatusActivity(String status, String statusheading1, String statusheading2, Intent intent) {
        this.status = status;
        this.statusheading1 = statusheading1;
        this.statusheading2 = statusheading2;
        this.intent = intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        statusText = findViewById(R.id.statusText);
        statusHeading1Text = findViewById(R.id.statusHeading1Text);
        statusHeading2Text = findViewById(R.id.statusHeading2Text);
        statusBtn = findViewById(R.id.statusBtn);

        statusText.setText(status);
        statusHeading1Text.setText(statusheading1);
        statusHeading2Text.setText(statusheading2);
        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(intent));
            }
        });
    }
}