package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryProfile {

    private static final String TAG = "QueryProfile";

    public static void updatePassword(String email, String password) {
        Log.i(TAG, "updatePassword: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "email";
                field[1] = "password";

                String[] data = new String[2];
                data[0] = email;
                data[1] = password;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updatepassword.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                    }
                }
            }
        });
    }
}
