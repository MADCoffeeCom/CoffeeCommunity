package com.example.coffeecom.query;

import static com.example.coffeecom.helper.FormatDateTime.convertStringtoDate;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.ApplicationModel;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.model.TransactionModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.ParseException;
import java.util.Date;

public class QueryApplication {

    private static final String TAG = "QueryApplication";

    public static void queryApplication() {
        Log.i(TAG, "queryApplication: Run here once");
        Provider.getApplication().clear();

        FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/application.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                String result = fetchData.getResult();
                if(!result.equals("No results")){
                    Log.i(TAG, "queryApplication: " + result);
                    String[] applications = result.split("split");
                    for (String apps:applications) {
                        String[] details = apps.split(" - ");
                        String applicationId = details[0];
                        String userId = details[1];
                        String userPicUrl = details[2];
                        String username = details[3];
                        String userBackground = details[4];
                        int yearsOfExperience = Integer.parseInt(details[5]);
                        String applicationStatus = details[6];

                        ApplicationModel app = new ApplicationModel(applicationId, userId, userBackground, userPicUrl, username, yearsOfExperience);
                        Provider.addApplication(app);
                    }
                }
            }
        }
    }

    public static void acceptApplication(String applicationId, String userId, String baristaDesc, String years){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "applicationId";
                field[1] = "status";

                //Creating array for data
                String[] data = new String[2];
                data[0] = applicationId;
                data[1] = "A";

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updateapplication.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "acceptApplication: " + result);
                    }
                }
                addBaristaId(userId, baristaDesc, years);
            }
        });
    }
    public static Integer queryBaristaSize(){
        FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/baristasize.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                String result = fetchData.getResult();
                Log.i(TAG, "queryBaristaSize: " + result);
                return Integer.parseInt(result);
            }
        }
        return 0;
    }

    public static void addBaristaId(String userId, String baristaDesc, String years){
        String baristaId = "b" + (queryBaristaSize() + 1);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "userId";
                field[1] = "baristaId";

                //Creating array for data
                String[] data = new String[2];
                data[0] = userId;
                data[1] = baristaId;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updateprofile.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "addBaristaId: " + result);
                    }
                }
                addBarista(baristaId, baristaDesc, years);
            }
        });
    }

    public static void addBarista(String baristaId, String baristaDesc, String years){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[4];
                field[0] = "baristaId";
                field[1] = "baristaDesc";
                field[2] = "status";
                field[3] = "years";

                //Creating array for data
                String[] data = new String[4];
                data[0] = baristaId;
                data[1] = baristaDesc;
                data[2] = "" + 1;
                data[3] = years;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addbarista.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "addBarista: " + result);
                    }
                }
            }
        });
    }

    public static void declineApplication(String applicationId){
        Provider.getPosts().clear();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "applicationId";
                field[1] = "status";

                //Creating array for data
                String[] data = new String[2];
                data[0] = applicationId;
                data[1] = "D";

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updateapplication.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);

                    }
                }
            }
        });
    }
}

