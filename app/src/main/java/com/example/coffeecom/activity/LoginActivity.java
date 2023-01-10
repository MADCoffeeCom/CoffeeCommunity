package com.example.coffeecom.activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.fragment.HomeActivityFragment;
import com.example.coffeecom.helper.QueryHomePage;
import com.example.coffeecom.model.ProfileModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private ImageButton backBtn;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private TextView username, passwordTextView, forgotPassword, signUpBtn;
    private String isLoggedOut = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.loginpage_loginButton);
        backBtn = findViewById(R.id.backBtn);
        forgotPassword = findViewById(R.id.forgotPassword);
        saveLoginCheckBox = findViewById(R.id.rememberMeCheckBox);
        signUpBtn = findViewById(R.id.signUpBtn);
        username = (TextView) findViewById(R.id.editText_username);
        passwordTextView = (TextView) findViewById(R.id.editText_password);
        backBtn.setOnClickListener(view -> finish());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            isLoggedOut = bundle.getString("isLoggedOut");
        }
            Log.i(TAG, "isLoggedOut: " + isLoggedOut);

        forgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true ) {
            username.setText(loginPreferences.getString("username", ""));
            passwordTextView.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
//        if(!isLoggedOut.equals("true")){
//            validateLogin();
//            overridePendingTransition(0, 0);
//        }

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
        ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.setInverseBackgroundForced(false);
        pd.create();
        pd.show();
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

                        if (saveLoginCheckBox.isChecked()) {
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("username", username.getText().toString());
                            loginPrefsEditor.putString("password", passwordTextView.getText().toString());
                            loginPrefsEditor.commit();
                        } else {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.commit();
                        }

                        PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/login.php", "POST", field, data);
                        if (putData.startPut()) {

                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                String[] splittedresult = result.split(" - ");

                                if (splittedresult[0].equals("Login Success")){
                                    Provider.setUser(new ProfileModel(splittedresult[1]));
                                    Intent intent = null;
                                    if(Provider.getUser().getUserId().equals("UID_admin")){
                                        intent = new Intent(getApplicationContext(), AdminBottomNavigationActivity.class);
                                    }else{
                                        CompletableFuture cf = null;

                                        Provider.setWaitingState(true);
                                        pd.show();
                                        try {

                                            Provider.getBaristas().clear();
                                            Provider.getCoffees().clear();
                                            cf.supplyAsync(() -> new HomeActivityFragment.QueryWaiterHomePage()).join().call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }



                                        intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
                                    }

                                    startActivity(intent);

                                    finish();
                                    pd.dismiss();
                                }else{
                                    Log.d("myTag", result);
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }
                                //End ProgressBar (Set visibility to GONE)
                            }
                        }
                        //End Write and Read data with URL
                    }
                });
            }catch (Exception e) {
                Log.d("myTag", "error");
                Toast.makeText(this, "Login FAILED", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Log.d("myTag", "errrrrror");
            Toast.makeText(this, "Please fill in all the details", Toast.LENGTH_SHORT).show();

        }
    }

    public URL convertToUrl(String str) throws MalformedURLException {
        URL url = new URL(str );
        return url;
    }

    public static class QueryWaiterHomePage implements Callable<Void> {
        @Override
        public Void call() throws Exception {
            QueryHomePage.queryHomepage();
            return null;
        }
    }

    public static class ChangeWaitingState implements Callable<Void>{
        @Override
        public Void call() throws Exception {
            Log.i("waiting","Waiting is done");
            Provider.setWaitingState(false);
            return null;
        }
    }
}