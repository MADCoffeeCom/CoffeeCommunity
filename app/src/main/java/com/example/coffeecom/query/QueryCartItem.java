package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.CartCardModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryCartItem {
    private static final String TAG = "QueryCartItem";
    public static void queryCartItem() {
        Provider.getUser().getCartCard().clear();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "userId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = Provider.getUser().getUserId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/cartItem.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String[] orderDetails = str.split(" - ");
                            String coffeePicUrl = orderDetails[0];
                            String coffeeTitle = orderDetails[1];
                            double coffeePrice = Double.parseDouble(orderDetails[2]);
                            int amount = Integer.parseInt(orderDetails[3]);
                            String coffeeType = orderDetails[4];
                            String userStreetNo = orderDetails[5];
                            String userTaman = orderDetails[6];
                            String userPostalCode = orderDetails[7];
                            String userState = orderDetails[8];
                            String baristaId = orderDetails[9];

                            CartCardModel cartCardModel = new CartCardModel(coffeePicUrl, coffeeTitle, coffeePrice, amount, baristaId);
                            Provider.getUser().addCartCard(cartCardModel);
                            if (Provider.getBaristaIdInCart().contains(baristaId)== false){
                                Provider.addBaristaIdInCart(baristaId);
                                Log.i(TAG, "Successfully Added BaristaIdInCart " + Provider.getBaristaIdInCart().contains(baristaId));
                            }
                            Log.i(TAG, "Successfully Added Cart Card " + cartCardModel.getCoffeeName());
                        }
                    }
                }
            }
        });
    }


}
