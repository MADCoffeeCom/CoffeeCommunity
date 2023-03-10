package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.FormatDateTime.convertStringtoDate;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.adapter.PendingOrderAdapter;
import com.example.coffeecom.adapter.SellingCoffeeAdapter;
import com.example.coffeecom.model.BrewedOrderModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.OrderedCoffeeModel;
import com.example.coffeecom.query.QueryBrewedCoffee;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;


public class BaristaFragment extends Fragment {

    private static final String TAG = "Barista";

    private TextView noCoffeeSoldErrorText, noOrderErrorText;
    private ImageButton addSellingCoffeeBtn;

    RecyclerView pendingOrderRecycleView, coffeeSellingRecycleView;
    RecyclerView.Adapter pendingOrderRecycleViewAdapter, coffeeSellingRecycleViewAdapter;

    ArrayList<CoffeeModel> sellingCoffee = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sellingCoffee.clear();
//        QueryBrewedCoffee.queryOrder();
        Log.i(TAG, "onCreateView: Run here once");
        View view = inflater.inflate(R.layout.activity_barista,container,false);

        noCoffeeSoldErrorText = view.findViewById(R.id.noCoffeeSoldErrorText);
        noOrderErrorText = view.findViewById(R.id.noOrderErrorText);
        addSellingCoffeeBtn = view.findViewById(R.id.addSellingCoffeeBtn);
        noCoffeeSoldErrorText.setVisibility(View.GONE);
        noOrderErrorText.setVisibility(View.GONE);

        pendingOrderRecycleView = view.findViewById(R.id.pendingOrderRecycleView);
        coffeeSellingRecycleView = view.findViewById(R.id.coffeeSellingRecycleView);

        recyclerViewPendingOrder();
        querySellingCoffee();

        addSellingCoffeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomNavigationActivity)getActivity()).replaceFragment(new AddCoffeeFragment());
            }
        });

        return view;
    }


    private void querySellingCoffee() {
        Log.i(TAG, "querySellingCoffee: Run here once");
        Provider.getUser().getSellingCoffeeId().clear();

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
                            if(Provider.getUser().getSellingCoffeeId().get(j).equals(Provider.getCoffees().get(i).getCoffeeId())){
                                sellingCoffee.add(Provider.getCoffees().get(i));
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

    public void recyclerViewPendingOrder() {
        Provider.getOrder().clear();
        Log.i("BaristaFragment BrewedOrder size",""+Provider.getUser().getBrewedOrder().size());
        for (int i = 0; i < Provider.getUser().getBrewedOrder().size(); i++) {
            if (Provider.getUser().getBrewedOrder().get(i).getOrderStatus().equals("P")||Provider.getUser().getBrewedOrder().get(i).getOrderStatus().equals("A")){
                Provider.getOrder().add(Provider.getUser().getBrewedOrder().get(i));

//                Log.i("BaristaFragment added brewedOrder",""+Provider.getOrder().get(i).getOrderedCoffee().get(0).getCoffeeTitle());
            }
        }
        Log.i("BaristaFragment Order size",""+Provider.getOrder().size());
        for (BrewedOrderModel bom : Provider.getOrder()){
            Log.i("cm barista order Id", ""+bom.getOrderId());
            for (CoffeeModel cm : bom.getOrderedCoffee()){
                Log.i("cm barista", ""+cm.getCoffeeTitle());
            }
        }
//        for (int i = 0; i < Provider.getOrder().size(); i++) {
//            if (!(Provider.getUser().getBrewedOrder().get(i).getOrderStatus().equals("P")||Provider.getUser().getBrewedOrder().get(i).getOrderStatus().equals("A"))){
//                Provider.getOrder().remove(i);
//
////                Log.i("BaristaFragment added brewedOrder",""+Provider.getOrder().get(i).getOrderedCoffee().get(0));
//            }
//        }

        if(Provider.getOrder().isEmpty()){
            noOrderErrorText.setVisibility(View.VISIBLE);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            pendingOrderRecycleView.setLayoutManager(linearLayoutManager);

            pendingOrderRecycleViewAdapter = new PendingOrderAdapter(Provider.getOrder(), getActivity());
            pendingOrderRecycleView.setAdapter(pendingOrderRecycleViewAdapter);
        }
    }

    private void recyclerViewCoffeeType() {
        if(sellingCoffee.isEmpty()) {
            noCoffeeSoldErrorText.setVisibility(View.VISIBLE);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            coffeeSellingRecycleView.setLayoutManager(linearLayoutManager);

            coffeeSellingRecycleViewAdapter = new SellingCoffeeAdapter(sellingCoffee, getActivity());
            coffeeSellingRecycleView.setAdapter(coffeeSellingRecycleViewAdapter);
        }
    }
}