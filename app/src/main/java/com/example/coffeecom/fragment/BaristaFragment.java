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
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.OrderModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.sql.Connection;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_barista,container,false);

        noCoffeeSoldErrorText = view.findViewById(R.id.noCoffeeSoldErrorText);
        noCoffeeSoldErrorText.setVisibility(View.GONE);
        pendingOrderRecycleView = view.findViewById(R.id.pendingOrderRecycleView);
        coffeeSellingRecycleView = view.findViewById(R.id.coffeeSellingRecycleView);

        for (int i = 0; i < Provider.getCoffees().size(); i++) {
            for (int j = 0; j < Provider.getUser().getSellingCoffeeId().size(); j++) {
                if(Provider.getUser().getSellingCoffeeId().get(i).equals(Provider.getCoffees().get(j).getCoffeeId())){
                    coffeeTitle.add(Provider.getCoffees().get(j).getCoffeeTitle());
                    coffeePic.add(Provider.getCoffees().get(j).getCoffeePic());
                }
            }
        }

        Log.i(TAG, "Provider coffee size: " + Provider.getCoffees().size());
        Log.i(TAG, "Provider barista size: " + Provider.getUser().getSellingCoffeeId().size());

        queryOrder();


        return view;
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

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/baristaorder.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String[] orderDetails = str.split(" - ");
                            Log.i(TAG, "Successfully Added Order " + orderDetails[3]);
                            String orderId = orderDetails[0];
                            String baristaId = orderDetails[1];
                            String userId = orderDetails[2];
                            Date orderStartTime = null;
                            Date orderEndTime = null;
                            Date orderDuration = null;
                            try {
                                orderStartTime = convertStringtoDate(orderDetails[3]);
                                orderEndTime = convertStringtoDate(orderDetails[4]);
                                orderDuration = convertStringtoDate(orderDetails[5]);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            double orderTotalPrice = Double.valueOf(orderDetails[6]);
                            String orderStatus = orderDetails[7];

                            OrderModel order = new OrderModel(orderId, baristaId, userId, orderStartTime, orderEndTime, orderDuration, orderTotalPrice, orderStatus);

                            Provider.getUser().addBrewedOrder(order);
                            Log.i(TAG, "Successfully Added Order " + order.getOrderId());
//
                        }
                    }
                    Log.i(TAG, "Provider order size: " + Provider.getUser().getBrewedOrder().size());

                    recyclerViewCoffeeType();
                    recyclerViewPendingOrder();
                }
            }
        });
    }

    private void recyclerViewPendingOrder() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        pendingOrderRecycleView.setLayoutManager(linearLayoutManager);

        pendingOrderRecycleViewAdapter = new PendingOrderAdapter(Provider.getUser().getBrewedOrder());
        pendingOrderRecycleView.setAdapter(pendingOrderRecycleViewAdapter);
    }

    private void recyclerViewCoffeeType() {
        if(coffeeTitle.isEmpty()) {
            noCoffeeSoldErrorText.setVisibility(View.VISIBLE);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            coffeeSellingRecycleView.setLayoutManager(linearLayoutManager);

            coffeeSellingRecycleViewAdapter = new CoffeeTypeAdapter(coffeeTitle, coffeePic);
            coffeeSellingRecycleView.setAdapter(coffeeSellingRecycleViewAdapter);
        }
    }
}