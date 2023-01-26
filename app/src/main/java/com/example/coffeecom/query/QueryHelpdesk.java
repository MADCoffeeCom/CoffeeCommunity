package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.BankCardModel;
import com.example.coffeecom.model.FeedbackModel;
import com.example.coffeecom.model.HelpdeskModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryHelpdesk {

    private static final String TAG = "QueryHelpdesk";

    public static void queryHelpdesk() {
        Provider.getHelpdesks().clear();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/helpdesk.php");

                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String[] strDetails = str.split(" - ");

                            String helpId = strDetails[0];
                            String question = strDetails[1];
                            String answer = strDetails[2];

                            HelpdeskModel helpdesk = new HelpdeskModel(helpId, question, answer);

                            Provider.addHelpdesks(helpdesk);
                            Log.i("Home Add new helpdesk", helpdesk.getHelpId() + " added!");
                        }
                    }
                }
            }
        });
    }

    public static void addHelpdesk(String ques, String ans) {
        Log.i(TAG, "updateHelpdesk: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[3];
                field[0] = "helpId";
                field[1] = "question";
                field[2] = "answer";

                String[] data = new String[3];
                data[0] = "h" + (Provider.getHelpdesks().size()+1);
                data[1] = ques;
                data[2] = ans;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addhelpdesk.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                    }
                }
            }
        });
    }

    public static void updateHelpdesk(String helpId, String ques, String ans) {
        Log.i(TAG, "updateHelpdesk: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[3];
                field[0] = "helpId";
                field[1] = "question";
                field[2] = "answer";

                String[] data = new String[3];
                data[0] = helpId;
                data[1] = ques;
                data[2] = ans;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updatehelpdesk.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                    }
                }
            }
        });
    }

    public static void removeHelpdesk(String helpId) {
        Log.i(TAG, "removeHelpdesk: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "helpId";

                String[] data = new String[1];
                data[0] = helpId;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/deletehelpdesk.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                    }
                    for (int i = 0; i < Provider.getHelpdesks().size(); i++) {
                        if(Provider.getHelpdesks().get(i).getHelpId().equals(helpId)){
                            Provider.getHelpdesks().remove(i);
                        }
                    }
                }
            }
        });
    }
}
