package com.example.coffeecom.query;

import static com.example.coffeecom.helper.FormatDateTime.convertStringtoDate;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.OrderedCoffeeModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryOrderedAndPendingCoffee {
    private static final String TAG = "QueryOrderedCoffee";

    public static void queryOrderedAndPendingCoffee(){
        Provider.getUser().getOrderedHistory().clear();
        Provider.getUser().getPendingOrder().clear();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "userId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = Provider.getUser().getUserId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/orderedcoffee.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("No results")){
                            Log.i(TAG, "queryOrderedCoffee No results ");
                        }else if(result.equals("Error: Database connection")){
                            Log.i(TAG, "queryOrderedCoffee Database connection problem");
                        }
                        else{
                            Log.i("QueryOrderedCoffee", "result : " + result);
                            String[] resultSplitted = result.split("split");
                            for (String str: resultSplitted) {

                                String[] orderDetails = str.split(" - ");
                                for (String str1 : orderDetails){
                                    Log.i("QueryOAPCoffee",""+str1+ " ");
                                }
                                String orderId = orderDetails[0];
                                Log.i(TAG, "Debugging " + orderId);
                                String baristaId = orderDetails[1];
                                String baristaName = orderDetails[2];
                                String baristaTaman = orderDetails[3];
                                String baristaDesc = orderDetails[4];
                                String userId = orderDetails[5];
                                Date orderStartTime = null;
                                Date orderEndTime = null;
                                Date orderDuration = null;
                                try {
                                    orderStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderDetails[6]);
                                    orderEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderDetails[7]);
                                    orderDuration = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderDetails[8]);
                                } catch (ParseException e) { e.printStackTrace(); }
                                double orderTotalPrice = Double.valueOf(orderDetails[9]);
                                String orderStatus = orderDetails[10];
                                Log.i("orderStatus", orderStatus);
                                OrderedCoffeeModel order = new OrderedCoffeeModel(orderId, baristaId, baristaName, baristaTaman, baristaDesc, userId, orderStartTime, orderEndTime, orderDuration, orderTotalPrice, orderStatus);

                                if(order.getOrderStatus().equals("P") || order.getOrderStatus().equals("A") || order.getOrderStatus().equals("B")){
                                    Provider.getUser().addPendingOrder(order);
                                    Log.i(TAG, "Successfully Added Pending Order " + order.getOrderId());
                                }else if(order.getOrderStatus().equals("T")){
                                    Provider.getUser().addOrderedHistory(order);
                                    Log.i(TAG, "Successfully Added Taken Order " + order.getOrderId());
                                }

                            }
                        }
                        queryCoffeeInOrder();
                    }
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
                        if (!result.equals("No results") && !result.equals("Error: Database connection")){
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

                                for (int i = 0; i < Provider.getUser().getOrderedHistory().size(); i++) {
                                    for (int j = 0; j < amount; j++) {
                                        if (Provider.getUser().getOrderedHistory().get(i).getOrderId().equals(orderId)) {
                                            Provider.getUser().getOrderedHistory().get(i).addOrderedCoffee(coffee);
                                            Log.i(TAG, "Successfully added OrderHistory in Order " + coffee.getCoffeeId());
                                        }
                                    }
                                }

                                for (int i = 0; i < Provider.getUser().getPendingOrder().size(); i++) {
                                    for (int j = 0; j < amount; j++) {
                                        if (Provider.getUser().getPendingOrder().get(i).getOrderId().equals(orderId)) {
                                            Provider.getUser().getPendingOrder().get(i).addOrderedCoffee(coffee);
                                            Log.i(TAG, "Successfully added PendingOrder in Order " + coffee.getCoffeeId());
                                        }
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
