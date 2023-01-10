package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.ProfileModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignupActivity extends AppCompatActivity {

    TextView logInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button registerButton = (Button) findViewById(R.id.signup_register);
        ImageButton backBtn = findViewById(R.id.backBtn);
        logInBtn = findViewById(R.id.logInBtn);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(view -> finish());


        registerButton.setOnClickListener(view -> validateRegister());

    }

    public void validateRegister(){
        TextView username = (TextView) findViewById(R.id.signup_username);
        TextView email = (TextView) findViewById(R.id.signup_email);
        TextView passwordTextView = (TextView) findViewById(R.id.signup_password);
        TextView confirmTextView = (TextView) findViewById(R.id.signup_confirmpassword);
        CheckBox tc_agreement = (CheckBox) findViewById(R.id.tc_agreement);

        if (tc_agreement.isChecked() && passwordTextView.getText().toString().equals(confirmTextView.getText().toString()) && !email.getText().toString().equals("") && !username.getText().toString().equals("")) {
            try {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[4];
                field[0] = "username";
                field[1] = "userId";
                field[2] = "password";
                field[3] = "email";

                //Creating array for data
                String[] data = new String[4];
                data[0] = username.getText().toString();
                data[1] = "UID_"+username.getText().toString();
                data[2] = passwordTextView.getText().toString();
                data[3] = email.getText().toString();

                PutData putData = new PutData("http://" + Provider.getIpAddress()+ "/CoffeeCommunityPHP/signup.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Sign Up Success")){
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Log.d("myTag", result);
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                        }
                        //End ProgressBar (Set visibility to GONE)
                    }
                }
                //End Write and Read data with URL
            }
        });
            }catch (Exception e) {
                Log.d("myTag", "error");
                Toast.makeText(this, "Register FAILED", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Log.d("myTag", "errrrrror");
            Toast.makeText(this, "Please fill in all the details", Toast.LENGTH_SHORT).show();

        }
//
//        if (tc_agreement.isChecked() && passwordTextView.getText().toString().equals(confirmTextView.getText().toString()) && !email.getText().toString().equals("") && !username.getText().toString().equals("")) {
//            try {
//                ProfileModel profileModel = new ProfileModel(username.getText().toString(), email.getText().toString(), passwordTextView.getText().toString(), "User");
//                Toast.makeText(this, "Register SUCCESFUL", Toast.LENGTH_SHORT).show();
//            } catch (Exception e) {
//                Toast.makeText(this, "Register FAILED", Toast.LENGTH_SHORT).show();
//            }
//        }
//        else{
//            Toast.makeText(this, "Register Bruh", Toast.LENGTH_SHORT).show();
//
//        }
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
    }

}