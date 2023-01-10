package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.BaristaModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

public class QueryBarista {
    public static void queryBarista() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/barista.php");
//                FetchData fetchData = new FetchData("https://coffeecommunityapp.000webhostapp.com/barista.php");

                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {

                            String[] baristaDetails = str.split(" - ");
                            String baristaId = baristaDetails[0];
                            String baristaPic = baristaDetails[1];
                            String username = baristaDetails[2];
                            String baristaDesc = baristaDetails[3];
                            String userStreetNo =  baristaDetails[4];
                            String userPostalCode = baristaDetails[5];
                            String userState = baristaDetails[6];
                            String userTaman = baristaDetails[7];
                            String userLocation = baristaDetails[8];

                            BaristaModel barista = new BaristaModel(baristaId, baristaPic, username, baristaDesc, userStreetNo, userPostalCode, userState, userTaman, userLocation);

                            if (!Provider.getBaristas().contains(barista)){
                                Provider.addBarista(barista);
                                //                            Log.i("HomeActivity - Add Barista", "");
                            }
                        }
                    }

                }
            }
        });
    }
}
