package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.BaristaRatingModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryRating {

    private static final String TAG = "QueryRating";

    public static void queryRating(String baristaId){

        Log.i(TAG, "queryRating: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                int baristaIndex = 0;
                Provider.getBaristas().get(baristaIndex).getRatings().clear();

                for (int i = 0; i < Provider.getBaristas().size(); i++) {
                    if(Provider.getBaristas().get(i).getBaristaId().equals(baristaId)){
                        baristaIndex=i;
                        break;
                    }
                }
                String[] field = new String[1];
                field[0] = "baristaId";

                String[] data = new String[1];
                data[0] = baristaId;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/rating.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] ratings = result.split("split");
                        for (String rating : ratings){
                            String[] ratingDetails = rating.split(" - ");
                            String username = ratingDetails[0];
                            int ratingNum = Integer.parseInt(ratingDetails[1]);
                            String ratingDesc = ratingDetails[2];

                            BaristaRatingModel ratingModel = new BaristaRatingModel(ratingNum, ratingDesc, username);
                            Log.i(TAG, "run: " + ratingModel.getRating());
                            Log.i(TAG, "run: " + ratingModel.getRatingDesc());
                            Provider.getBaristas().get(baristaIndex).addRatings(ratingModel);
                            Log.i(TAG, "Added rating: " + ratingModel.getRaterName());

                        }
                    }
                }
            }
        });
    }

    public static void addRating(String baristaId, String userId, String rating, String ratingDesc){

        Log.i(TAG, "addRating: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[1];
                field[0] = "baristaId";
                field[0] = "userId";
                field[0] = "rating";
                field[0] = "ratingDesc";

                String[] data = new String[1];
                data[0] = baristaId;
                data[0] = userId;
                data[0] = rating;
                data[0] = ratingDesc;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/ratebarista.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: "+ result);
                    }
                }
            }
        });
    }
}
