package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class NewQueryProfile {

    public static void queryProfile() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "userId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = Provider.getUser().getUserId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/profile.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i("NewQueryProfile", "" + result);
                        String[] profileDetails = result.split(" - ");
                        String userId = profileDetails[0];
                        String picUrl = profileDetails[1];
                        String baristaId = profileDetails[2];
                        String adminId = profileDetails[3];
                        String userName = profileDetails[4];
                        String email = profileDetails[5];
                        String streetNo = profileDetails[6];
                        String taman = profileDetails[7];
                        String postCode = (profileDetails[8]);
                        String state = profileDetails[9];

                        Provider.getUser().setUserDetails(picUrl, userId, baristaId, adminId, userName, email, streetNo, taman, postCode, state);

                        Log.i(Provider.getUser().getUserName(), "User Updated in Provider" + Provider.getUser().getUserId());

                    }
                }
            }
        });
    }
}
