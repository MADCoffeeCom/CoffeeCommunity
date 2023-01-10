package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryWallet {
    private static final String TAG = "QueryWallet";

    public static void queryWallet() {
        Log.i(TAG, "queryWallet: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "userId";

                String[] data = new String[1];
                data[0] = Provider.getUser().getUserId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/wallet.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] str = result.split(" - ");
                        String walletId = str[0];
                        String walletBalance = str[1];
                        String walletpin = str[2];

                        Provider.getUser().setWalletId(walletId);
                        Provider.getUser().setWalletBalance(Double.parseDouble(walletBalance));
                        Provider.getUser().setWalletPin(walletpin);
                    }
                }
            }
        });
    }

    public static void updateWallet() {
        Log.i(TAG, "queryWallet: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "walletBalance";
                field[1] = "userId";

                String[] data = new String[2];
                data[0] = Double.toString(Provider.getUser().getWalletBalance());
                data[1] = Provider.getUser().getUserId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updatewallet.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Update Success")){
                            //if condition
                        }else{

                        };
                    }
                }
            }
        });
    }

}
