package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Random;

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
                        if (result.equals("No results")){
                            Random ran = new Random();
                            String ranWalletId = "w"+ran.nextInt(999);
                            Provider.getUser().setWalletId(ranWalletId);
                            Provider.getUser().setWalletBalance(0);
                            insertWallet(ranWalletId);
                        }else if (result.equals("Error: Database connection")){

                        }
                        else{
                        String[] str = result.split(" - ");
                        String walletId = str[0];
                        String walletBalance = str[1];
                        String walletpin = str[2];
                        if (str[2].equals("empty")){
                            walletpin = null;
                        }
                        else{
                            walletpin = str[2];
                        }

                        Provider.getUser().setWalletId(walletId);
                        Provider.getUser().setWalletBalance(Double.parseDouble(walletBalance));
                        Provider.getUser().setWalletPin(null);
                    }
                    }
                }
            }
        });
    }
    public static void insertWallet(String ranWalletId) {
        Log.i(TAG, "queryWallet: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[3];
                field[0] = "walletBalance";
                field[1] = "userId";
                field[2] = "walletId";


                String[] data = new String[3];
                data[0] = Double.toString(Provider.getUser().getWalletBalance());
                data[1] = Provider.getUser().getUserId();
                data[2] = ranWalletId;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/insertwallet.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i("QueryWallet Insert Wallet", result);
                        if (result.equals("Insert Wallet success")){
                            //if condition
                        }else{

                        };
                    }
                }
            }
        });
    }

    public static void insertWalletPin(String walletPin) {
        Log.i(TAG, "queryWallet: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[3];
                field[0] = "walletPin";
                field[1] = "userId";
                field[2] = "walletId";


                String[] data = new String[3];
                data[0] = walletPin;
                data[1] = Provider.getUser().getUserId();
                data[2] = Provider.getUser().getWalletPin();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/insertwallet.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i("QueryWallet Insert Wallet Pin", result);
                        if (result.equals("Insert Wallet Pin success")){
                            //if condition
                        }else{

                        };
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
