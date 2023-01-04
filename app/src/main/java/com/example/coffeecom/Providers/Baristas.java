package com.example.coffeecom.Providers;

import android.util.Log;

import com.example.coffeecom.model.BaristaModel;
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

    PutData putData = new PutData("http://192.168.56.1/CoffeeCommunity/barista.php", "POST", field, data);

    if (putData.startPut()){
        if (putData.onComplete()){
            String result = putData.getResult();
            Log.d("bruhb",result);

        }

    }



}


}
