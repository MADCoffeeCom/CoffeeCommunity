package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryFeedback {
    private static final String TAG = "UpdateFeedback";
    static int size = 0;

    public static void queryFeedback() {
        Log.i(TAG, "queryFeedback: Run here once");
        FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/feedback.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                String result = fetchData.getResult();
                Log.i(TAG, "queryFeedback:" + result);

                size = Integer.parseInt(result);
            }
        }
    }

    public void addFeedback(int rating, String feedback){
        queryFeedback();
        Log.i(TAG, "updateFeedback: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[4];
                field[0] = "feedbackId";
                field[1] = "ratings";
                field[2] = "feedbackDesc";
                field[3] = "userId";

                String[] data = new String[4];
                data[0] = "fb" + (size + 1);
                data[1] = "" + rating;
                data[2] = feedback;
                data[3] = Provider.getUser().getUserId();

                Log.i(TAG, "feedbackId:" + data[0]);
                Log.i(TAG, "ratings"+ data[1]);
                Log.i(TAG, "feedbackDesc:"+ data[2]);
                Log.i(TAG, "userId:"+ data[3]);


                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addfeedback.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if(result.equals("Update success")){
                            Log.i(TAG, "run: " + result);
                        }
                    }
                }
            }
        });
    }
}
