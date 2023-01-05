package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.ProfileModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.loginpage_loginButton);


        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });
    }

    //username: abang
    //password: abang
    public void validateLogin(){
        TextView username = (TextView) findViewById(R.id.editText_username);
        TextView passwordTextView = (TextView) findViewById(R.id.editText_password);

        if (!passwordTextView.getText().toString().equals("") && !username.getText().toString().equals("")) {
            try {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[2];
                        field[0] = "username";
                        field[1] = "password";

                        //Creating array for data
                        String[] data = new String[2];
                        data[0] = username.getText().toString();
                        data[1] = passwordTextView.getText().toString();

                        PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/login.php", "POST", field, data);
                        if (putData.startPut()) {

                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                String[] splittedresult = result.split(" - ");

                                if (splittedresult[0].equals("Login Success")){
                                    Provider.setUser(new ProfileModel(splittedresult[1]));
                                    Intent intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Log.d("myTag", result + data[1]);
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
            Toast.makeText(this, "Register Bruh", Toast.LENGTH_SHORT).show();

        }




//        if (username.getText().toString().equals("abang") && passwordTextView.getText().toString().equals("abang")){
//            Toast.makeText(this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
//        }else Toast.makeText(this,"LOGIN FAILED", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
    }

    public URL convertToUrl(String str) throws MalformedURLException {
        URL url = new URL(str );
        return url;
    }
}