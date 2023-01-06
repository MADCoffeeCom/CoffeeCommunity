package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.FormatDateTime.convertStringtoDate;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.adapter.PendingOrderAdapter;
import com.example.coffeecom.model.BrewedOrderModel;
import com.example.coffeecom.model.CoffeeModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;


public class BaristaFragment extends Fragment {

    private static final String TAG = "Barista";

    private TextView noCoffeeSoldErrorText;

    RecyclerView pendingOrderRecycleView, coffeeSellingRecycleView;
    RecyclerView.Adapter pendingOrderRecycleViewAdapter, coffeeSellingRecycleViewAdapter;

    ArrayList<String> coffeeTitle = new ArrayList<>();
    ArrayList<String> coffeePic = new ArrayList<>();

    ArrayList<CoffeeModel> coffeesInOrder = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        coffeeTitle.clear();
        coffeePic.clear();
        View view = inflater.inflate(R.layout.activity_barista,container,false);

        noCoffeeSoldErrorText = view.findViewById(R.id.noCoffeeSoldErrorText);
        noCoffeeSoldErrorText.setVisibility(View.GONE);

        pendingOrderRecycleView = view.findViewById(R.id.pendingOrderRecycleView);
        coffeeSellingRecycleView = view.findViewById(R.id.coffeeSellingRecycleView);

        queryOrder();
        querySellingCoffee();

        return view;
    }

    private void updateOrderStatus(String status, String orderId) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            String[] field = new String[2];
            field[0] = "orderStatus";
            field[1] = "orderId";

            //Creating array for data
            String[] data = new String[2];
            data[0] = status;
            data[1] = orderId;

            PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updateorderstatus.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    if(result.equals("Update success")){
                        Log.i(TAG, "Update Successful");
                    }
                    for (int i = 0; i < Provider.getUser().getBrewedOrder().size(); i++) {
                        if (Provider.getUser().getBrewedOrder().get(i).getOrderId().equals(orderId)){
                            Provider.getUser().getBrewedOrder().get(i).setOrderStatus(status);
                        }
                    }
                }
                recyclerViewPendingOrder();
            }
        });
    }

    private void querySellingCoffee() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "baristaId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = Provider.getUser().getBaristaId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/coffeesoldbybarista.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String coffeeIdSold:resultSplitted) {
                            Provider.getUser().getSellingCoffeeId().add(coffeeIdSold);
                        }
                    }

                    //Match the coffeeId in coffee model
                    for (int i = 0; i < Provider.getCoffees().size(); i++) {
                        for (int j = 0; j < Provider.getUser().getSellingCoffeeId().size(); j++) {
                            if(Provider.getUser().getSellingCoffeeId().get(i).equals(Provider.getCoffees().get(j).getCoffeeId())){
                                coffeeTitle.add(Provider.getCoffees().get(j).getCoffeeTitle());
                                coffeePic.add(Provider.getCoffees().get(j).getCoffeePic());
                            }
                        }
                    }
                    recyclerViewCoffeeType();

                    Log.i(TAG, "Provider coffee size: " + Provider.getCoffees().size());
                    Log.i(TAG, "Provider barista size: " + Provider.getUser().getSellingCoffeeId().size());

                }
            }
        });
    }

    private void queryOrder() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "baristaId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = Provider.getUser().getBaristaId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/brewedorder.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String[] orderDetails = str.split(" - ");
                            String orderId = null;
                            String baristaId = null;
                            String baristaDesc = null;
                            String customerID = null;
                            String customerName = null;
                            String customerLocation = null;
                            Date orderStartTime = null;
                            Date orderEndTime = null;
                            Date orderDuration = null;
                            try {
                                orderStartTime = convertStringtoDate("2023-01-03 16:00:00");
                                orderEndTime = convertStringtoDate("2023-01-03 16:20:00");
                                orderDuration = convertStringtoDate("00:20:00");
                            } catch (ParseException e) { e.printStackTrace(); }
                            double orderTotalPrice = 0;
                            String orderStatus = null;

                            BrewedOrderModel order = new BrewedOrderModel("o0001", "b0001", "I am a barista", "UID_abang", "abang", "1.00", orderStartTime, orderEndTime, orderDuration, 9.9, "P");
                            Provider.getUser().addBrewedOrder(order);
                            Log.i(TAG, "Successfully Added Order " + order.getOrderId());
//
                        }
                    }
                    queryCoffeeInOrder();
                }
            }
        });
    }

    private void queryCoffeeInOrder() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/coffeeinorder.php");
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        Log.i(TAG, "Result: " + result);
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String[] orderDetails = str.split(" - ");
                            String orderId = null;
                            String coffeeId = null;
                            int amount = Integer.parseInt(orderDetails[2]);

                            CoffeeModel coffee = null;
                            for (int i = 0; i < Provider.getCoffees().size(); i++) {
                                if(Provider.getCoffees().get(i).getCoffeeId().equals(coffeeId)){
                                    coffee = Provider.getCoffees().get(i);
                                    break;
                                }
                            }

                            for (int i = 0; i < Provider.getUser().getBrewedOrder().size(); i++) {
                                for (int j = 0; j < amount; j++) {
                                    if (Provider.getUser().getBrewedOrder().get(i).getOrderId().equals(orderId)) {
                                        Provider.getUser().getBrewedOrder().get(i).addOrderedCoffee(coffee);
                                        Log.i(TAG, "Successfully Coffee in Order " + coffee.getCoffeeId());
                                    }
                                }
                            }
                        }
                    }
                    recyclerViewPendingOrder();
                }
            }
        });
    }

    public void recyclerViewPendingOrder() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        pendingOrderRecycleView.setLayoutManager(linearLayoutManager);

        pendingOrderRecycleViewAdapter = new PendingOrderAdapter(Provider.getUser().getBrewedOrder(), getActivity());
        pendingOrderRecycleView.setAdapter(pendingOrderRecycleViewAdapter);
    }

    private void recyclerViewCoffeeType() {
        if(coffeeTitle.isEmpty()) {
            noCoffeeSoldErrorText.setVisibility(View.VISIBLE);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            coffeeSellingRecycleView.setLayoutManager(linearLayoutManager);

            coffeeSellingRecycleViewAdapter = new CoffeeTypeAdapter(coffeeTitle, coffeePic, getActivity());
            coffeeSellingRecycleView.setAdapter(coffeeSellingRecycleViewAdapter);
        }
    }
}