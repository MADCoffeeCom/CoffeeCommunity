package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.TransactionModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Random;

public class QueryTransaction {

    private static final String TAG = "QueryTransaction";

    public static void queryTransaction() {
        Log.i(TAG, "queryTransaction: Run here once");
        Provider.getTransactions().clear();

        FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/transaction.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                String result = fetchData.getResult();
                Log.i(TAG, "queryTransaction: " + result);
                String[] transactions = result.split("split");
                for (String transaction:transactions) {
                    String[] details = transaction.split(" - ");
                    String id = details[0];
                    String senderId = details[1];
                    String receiverId = details[2];
                    String orderId = details[3];
                    double payment = Double.parseDouble(details[4]);
                    String paymentType = details[5];

                    TransactionModel trans = new TransactionModel(id, senderId, receiverId, orderId, payment, paymentType);
                    Provider.addTransactions(trans);

                }
            }
        }
    }

    public static void insertTransaction(String receiverid, String orderId, double totalPayment, String paymentType){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[6];
                field[0] = "transactionId";
                field[1] = "senderId";
                field[2] = "receiverId";
                field[3] = "orderId";
                field[4] = "totalPayment";
                field[5] = "paymentType";

                String[] data = new String[6];
                Random ran = new Random();
                data[0] = "t" + Integer.toString(ran.nextInt(999));
                data[1] = Provider.getUser().getUserId();
                data[2] = receiverid;
                data[3] = orderId;
                data[4] = Double.toString(totalPayment);
                data[5] = paymentType;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addtransaction.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Insert Success")){
                            //if condition
                        }else{

                        };
                    }
                }
            }
        });

    }
}
