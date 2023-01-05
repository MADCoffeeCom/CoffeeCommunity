package com.example.coffeecom.Providers;

import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.BaristaModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Baristas {
    ArrayList<BaristaModel> _baristas;

public BaristaModel findBarista(String baristaId){
    for (BaristaModel b:_baristas
         ) {
        if (b.getBaristaId().equals(baristaId)){
            return b;
        }
    }
    return null;
}

public void fetchAndSetBaristas(){
    ArrayList<BaristaModel> loadedBarista = new ArrayList<>();
    String[] field = new String[6];
    field[0] = "baristaId";
    field[1] = "userPicUrl";
    field[2] = "username";
    field[3] = "baristaDesc";
    field[4] = "userTaman";
    field[5] = "userLocation";

    String[] data = new String[0];
//    data[0] = username.getText().toString();
//    data[1] = passwordTextView.getText().toString();

//    PutData putData = new PutData("http://192.168.56.1/CoffeeCommunity/barista.php", "POST", field, data);

    FetchData fetchData = new FetchData("http://192.168.56.1/CoffeeCommunityPHP/barista.php");
    if (fetchData.startFetch()) {
        if (fetchData.onComplete()) {
            String result = fetchData.getResult();
            //End ProgressBar (Set visibility to GONE)
            String[] resultSplitted = result.split("split");
            for (String str: resultSplitted) {
//                            Log.i(str, "Printing all user...");

                String[] baristaDetails = str.split(" - ");
                BaristaModel barista = new BaristaModel(baristaDetails[0],baristaDetails[1],baristaDetails[2],baristaDetails[3],baristaDetails[4],baristaDetails[5],baristaDetails[6],baristaDetails[7],baristaDetails[8],baristaDetails[9],baristaDetails[10]);
                String userId = profileDetails[0];
                if(userId.equals(Provider.getUser().getUserId())){
                    String picUrl = profileDetails[1];
                    String userName = profileDetails[2];
                    String email = profileDetails[3];
                    String streetNo = profileDetails[4];
                    String taman = profileDetails[5];
                    int postCode = Integer.parseInt(profileDetails[6]);
                    String state = profileDetails[7];

                    String walletId = profileDetails[8];
                    double walletBalance = Double.valueOf(profileDetails[9]);
                    String walletPin = profileDetails[10];

                    Provider.getUser().setUserDetails(picUrl, userId, userName, email, "user", streetNo, taman, postCode, state, walletId, walletPin, walletBalance);
                    Log.i(Provider.getUser().getUserName(), "User Updated in Provider");

                }
            }
        }
    }



}


}
