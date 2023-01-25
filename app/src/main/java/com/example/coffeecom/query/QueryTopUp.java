package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.constraintlayout.motion.utils.StopLogic;

import com.example.coffeecom.Provider;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryTopUp {

    private static final String TAG = "QueryTopUp";

    public static void topUp(double amount) {
        Log.i(TAG, "topUp: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "walletBalance";
                field[1] = "userId";

                String[] data = new String[2];
                data[0] = String.valueOf(Provider.getUser().getWalletBalance() + amount);
                data[1] = Provider.getUser().getUserId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updatewallet.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                    }
                }
            }
        });
    }

    public static void withdraw(double amount) {
        Log.i(TAG, "withdraw: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "walletBalance";
                field[1] = "userId";

                String[] data = new String[2];
                data[0] = String.valueOf(Provider.getUser().getWalletBalance() - amount);
                data[1] = Provider.getUser().getUserId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updatewallet.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "withdraw: " + result);
                    }
                }
            }
        });
    }
}
