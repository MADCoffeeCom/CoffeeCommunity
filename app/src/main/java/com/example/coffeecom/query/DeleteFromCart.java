package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;

import com.example.coffeecom.Provider;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class DeleteFromCart {

    public static void deleteFromCart(){
    Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
        @Override
        public void run() {
            String[] field = new String[1];
            field[0] = "userId";

            String[] data = new String[1];
            data[0] = Provider.getUser().getUserId();

            PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/deleteFromCart.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                }
            }
        }
    });
    }
}
