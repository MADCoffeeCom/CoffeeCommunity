package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.R;
import com.example.coffeecom.query.UpdatePassword;

public class EnterNewPasswordActivity extends AppCompatActivity {

    TextView confirmPasswordTextBox, passwordTextBox;
    Button submitNewPassword;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_password);

        String email = getIntent().getExtras().getString("email");

        confirmPasswordTextBox = findViewById(R.id.confirmPasswordTextBox);
        passwordTextBox = findViewById(R.id.passwordTextBox);
        submitNewPassword = findViewById(R.id.submitNewPassword);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> finish());

        submitNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = "" + passwordTextBox.getText();
                String cpassword = "" + confirmPasswordTextBox.getText();

                if(password.equals(cpassword)){
                    UpdatePassword.updatePassword(email, password);
                    Toast.makeText(EnterNewPasswordActivity.this, "Password update successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EnterNewPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }else{
                    Toast.makeText(EnterNewPasswordActivity.this, "Password not same", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}