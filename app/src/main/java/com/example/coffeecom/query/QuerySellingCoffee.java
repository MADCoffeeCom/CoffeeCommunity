package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QuerySellingCoffee {
    private static final String TAG = "QuerySellingCoffee";
    public static void querySellingCoffee() {
        Log.i(TAG, "querySellingCoffee: Run here once");
        Provider.getUser().getSellingCoffeeId().clear();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "baristaId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = Provider.getUser().getBaristaId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/coffeesoldbybarista.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String coffeeIdSold:resultSplitted) {
                            Provider.getUser().getSellingCoffeeId().add(coffeeIdSold);
                        }
                    }
                }
            }
        });
    }
}
