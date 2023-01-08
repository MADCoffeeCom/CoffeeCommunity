package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.TransactionModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

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
}
