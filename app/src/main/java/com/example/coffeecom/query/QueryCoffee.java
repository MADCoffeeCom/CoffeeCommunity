package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.CoffeeModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryCoffee {

    private static final String TAG = "QueryCoffeeType";

    public static void queryCoffee() {
        Provider.getCoffees().clear();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/coffee.php");

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

                            Log.i("coffeeTitle", coffeeTitle);
                            Log.i("coffeePicUrl", coffeePicUrl);


                            CoffeeModel coffee = new CoffeeModel(coffeeId, coffeeTitle, coffeePicUrl, coffeeDesc, coffeeType, coffeePrice, ingredients, baristaId);
                            Log.i("in model coffeeTitle", coffee.getCoffeeTitle());
                            Log.i("in model coffeePicUrl", coffee.getCoffeePic());

                            Provider.addCoffee(coffee);
                            Log.i("Home Add new coffee", coffee.getCoffeeId() + " added!");

//                            if(!coffeeTypeA.contains(coffeeType)){
//                                coffeeTypeA.add(toTitleCase(coffeeType));
//                                coffeePicA.add(coffeePicUrl);
//                            }
                        }
                    }

                }
            }
        });
    }

    public static void addCoffee(String title, String pic, String desc, String type, String price, String ing){
        Log.i(TAG, "addCoffee: Run here once");

        String lastId = Provider.getCoffees().get(0).getCoffeeId();
        int id = Integer.parseInt(lastId.substring(1)) + 1;
        String newId = "c" + id;
        Log.i(TAG, "addCoffee: " + newId);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[8];
                field[0] = "coffeeId";
                field[1] = "baristaId";
                field[2] = "coffeeTitle";
                field[3] = "coffeePicUrl";
                field[4] = "coffeeDesc";
                field[5] = "coffeeType";
                field[6] = "coffeePrice";
                field[7] = "ingredients";

                String[] data = new String[8];
                data[0] = newId;
                data[1] = Provider.getUser().getBaristaId();
                data[2] = title;
                data[3] = pic;
                data[4] = desc;
                data[5] = type;
                data[6] = price;
                data[7] = ing;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addcoffee.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                        CoffeeModel newCoffee = new CoffeeModel(newId, title, pic, desc, type, Double.parseDouble(price), ing, Provider.getUser().getBaristaId());
                        Provider.getCoffees().add(newCoffee);
                    }
                }
            }
        });
    }

    public static void updateCoffee(String title, String pic, String desc, String type, String price, String ing, String coffeeId){
        Log.i(TAG, "updateCoffee: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[7];
                field[0] = "coffeeTitle";
                field[1] = "coffeePicUrl";
                field[2] = "coffeeDesc";
                field[3] = "coffeeType";
                field[4] = "coffeePrice";
                field[5] = "ingredients";
                field[6] = "coffeeId";

                String[] data = new String[7];
                data[0] = title;
                data[1] = pic;
                data[2] = desc;
                data[3] = type;
                data[4] = price;
                data[5] = ing;
                data[6] = coffeeId;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updatecoffee.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "updateCoffee: " + result);
                        for (int i = 0; i < Provider.getCoffees().size(); i++) {
                            if(Provider.getCoffees().get(i).getCoffeeId().equals(coffeeId)){
                                Provider.getCoffees().get(i).setCoffeeTitle(title);
                                Provider.getCoffees().get(i).setCoffeePic(pic);
                                Provider.getCoffees().get(i).setCoffeeDesc(desc);
                                Provider.getCoffees().get(i).setCoffeeType(type);
                                Provider.getCoffees().get(i).setCoffeePrice(Double.parseDouble(price));
                                Provider.getCoffees().get(i).setIngredients(ing);
                            }
                        }
                    }
                }
            }
        });
    }

    public static void deleteCoffee(String coffeeId){
        Log.i(TAG, "deleteCoffee: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "coffeeId";

                String[] data = new String[1];
                data[0] = coffeeId;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/deletecoffee.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "deleteCoffee: " + result);
                        for (int i = 0; i < Provider.getCoffees().size(); i++) {
                            if(Provider.getCoffees().get(i).getCoffeeId().equals(coffeeId)){
                                Provider.getCoffees().remove(i);
                            }
                        }
                    }
                }
            }
        });
    }
}
