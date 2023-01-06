package com.example.coffeecom.query;

import static com.example.coffeecom.helper.FormatDateTime.convertStringtoDate;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.BrewedOrderModel;
import com.example.coffeecom.model.CoffeeModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.ParseException;
import java.util.Date;

public class QueryBrewedCoffee {

    private static final String TAG = "QueryOrderedCoffee";
    public static void queryOrder() {
        Provider.getUser().getBrewedOrder().clear();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "baristaId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = Provider.getUser().getBaristaId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/brewedorder.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String[] orderDetails = str.split(" - ");
                            String orderId = orderDetails[0];
                            String baristaId = orderDetails[1];
                            String baristaDesc = orderDetails[2];
                            String customerID = orderDetails[3];
                            String customerName = orderDetails[4];
                            String customerLocation = orderDetails[5];
                            Date orderStartTime = null;
                            Date orderEndTime = null;
                            Date orderDuration = null;
                            try {
                                orderStartTime = convertStringtoDate(orderDetails[6]);
                                orderEndTime = convertStringtoDate(orderDetails[7]);
                                orderDuration = convertStringtoDate(orderDetails[8]);
                            } catch (ParseException e) { e.printStackTrace(); }
                            double orderTotalPrice = Double.valueOf(orderDetails[9]);
                            String orderStatus = orderDetails[10];

                            BrewedOrderModel order = new BrewedOrderModel(orderId, baristaId, baristaDesc, customerID, customerName, customerLocation, orderStartTime, orderEndTime, orderDuration, orderTotalPrice, orderStatus);
                            Provider.getUser().addBrewedOrder(order);
                            Log.i(TAG, "Successfully Added Order " + order.getOrderId());
                        }
                    }
                    queryCoffeeInOrder();
                }
            }
        });
    }

    public static void queryCoffeeInOrder() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/coffeeinorder.php");
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        Log.i(TAG, "Result: " + result);
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String[] orderDetails = str.split(" - ");
                            String orderId = orderDetails[0];
                            String coffeeId = orderDetails[1];
                            int amount = Integer.parseInt(orderDetails[2]);

                            CoffeeModel coffee = null;
                            for (int i = 0; i < Provider.getCoffees().size(); i++) {
                                if(Provider.getCoffees().get(i).getCoffeeId().equals(coffeeId)){
                                    coffee = Provider.getCoffees().get(i);
                                    break;
                                }
                            }

                            for (int i = 0; i < Provider.getUser().getBrewedOrder().size(); i++) {
                                for (int j = 0; j < amount; j++) {
                                    if (Provider.getUser().getBrewedOrder().get(i).getOrderId().equals(orderId)) {
                                        Provider.getUser().getBrewedOrder().get(i).addOrderedCoffee(coffee);
                                        Log.i(TAG, "Successfully Coffee in Order " + coffee.getCoffeeId());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
