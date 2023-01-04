package com.example.coffeecom.activity;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.BaristaCardAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.IngredientsModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCoffeeTypeList, recyclerViewBaristaList;
    private RecyclerView.Adapter coffeeTypeAdapter, baristaAdapter;

    ArrayList<String> coffeeTypeA = new ArrayList<>();
    ArrayList<String> coffeePicA = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialiseProfile();
        initialiseCoffeeType();
        initialiseBarista();



    }

    private void initialiseProfile() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://192.168.56.1/CoffeeCommunityPHP/profile.php");
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        //End ProgressBar (Set visibility to GONE)
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
//                            Log.i(str, "Printing all user...");

                            String[] profileDetails = str.split(" - ");
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
        });
    }

    private void initialiseCoffeeType() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://192.168.56.1/CoffeeCommunityPHP/coffee.php");
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        String[] resultSplitted = new String[11];
                        resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String[] strDetails = str.split(" - ");

                            String coffeeId = strDetails[0];
                            String coffeeTitle = strDetails[1];
                            String coffeePicUrl = strDetails[2];
                            String coffeeDesc = strDetails[3];
                            String coffeeType = strDetails[4];
                            double coffeePrice = Double.parseDouble(strDetails[5]);
                            String ingredients = strDetails[6];
                            String baristaId = strDetails[7];
//                            String baristaName = strDetails[8];
//                            String baristaTaman = strDetails[9];
//                            String baristaLocation = strDetails[10];

                            CoffeeModel coffee = new CoffeeModel(coffeeId, coffeeTitle, coffeePicUrl, coffeeDesc, coffeeType, coffeePrice, ingredients, baristaId);
                            Log.i(String.valueOf(coffee.getCoffeeDesc()), "Coffee model");

                            //Run to filter coffee type
                            if(!coffeeTypeA.contains(coffeeType)){
                                coffeeTypeA.add(toTitleCase(coffeeType));
                                coffeePicA.add(coffeePicUrl);
                            }
                            //add coffees to provider
                            Provider.addCoffee(coffee);
                        }
                    }
                    //Put here so that it pop the coffee after complete query
                    recyclerViewCoffeeType();
                }
            }
        });
    }

    private void initialiseBarista() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://192.168.56.1/CoffeeCommunityPHP/barista.php");
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
                            String userTaman = baristaDetails[4];
                            String userLocation = baristaDetails[5];


                            BaristaModel barista = new BaristaModel(baristaId, baristaPic, username, baristaDesc, userTaman, userLocation);
                            Provider.addBarista(barista);
//                            Log.i("HomeActivity - Add Barista", "");
                        }
                    }
                    recyclerViewBarista();
                }
            }
        });
    }

    private void recyclerViewCoffeeType() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeTypeList = findViewById(R.id.coffeeListInBaristaRecyclerView);
        recyclerViewCoffeeTypeList.setLayoutManager(linearLayoutManager);

        coffeeTypeAdapter = new CoffeeTypeAdapter(coffeeTypeA, coffeePicA);
        recyclerViewCoffeeTypeList.setAdapter(coffeeTypeAdapter);
    }

    private void recyclerViewBarista() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBaristaList = findViewById(R.id.baristaRecyclerView);
        recyclerViewBaristaList.setLayoutManager(linearLayoutManager);

        baristaAdapter = new BaristaCardAdapter(Provider.getBaristas());
        recyclerViewBaristaList.setAdapter(baristaAdapter);
    }
}