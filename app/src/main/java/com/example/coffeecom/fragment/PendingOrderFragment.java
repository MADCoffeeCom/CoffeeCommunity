package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeBaristaListAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.model.BrewedOrderModel;
import com.example.coffeecom.model.CoffeeModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

public class PendingOrderFragment extends Fragment {

    private static final String TAG = "PendingOrderFragment";

    TextView orderId, customerName, totalPriceText;
    RecyclerView coffeeRecyclerView;
    RecyclerView.Adapter coffeeTypeAdapter;
    ImageButton backBtn;
    Button brewedButton;

    BrewedOrderModel currentOrder;
    int currentOrderIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_order, container, false);

        orderId = view.findViewById(R.id.orderId);
        customerName = view.findViewById(R.id.customerName);
        totalPriceText = view.findViewById(R.id.totalPriceText);
        coffeeRecyclerView = view.findViewById(R.id.coffeeRecyclerView);
        backBtn = view.findViewById(R.id.backBtn);
        brewedButton = view.findViewById(R.id.brewedButton);
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        Bundle bundle = this.getArguments();
        String oId = bundle.getString("orderId");
        for (int i = 0; i < Provider.getUser().getBrewedOrder().size(); i++) {
            if(Provider.getUser().getBrewedOrder().get(i).getOrderId().equals(oId)){
                currentOrder = Provider.getUser().getBrewedOrder().get(i);
                currentOrderIndex = i;
            }
        }

        customerName.setText(currentOrder.getCustomerName());
        orderId.setText(currentOrder.getOrderId());
        totalPriceText.setText("" + currentOrder.getOrderTotalPrice());

        brewedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Provider.getUser().getBrewedOrder().get(currentOrderIndex).setOrderStatus("B");
                updateOrderStatus("B", currentOrder.getOrderId());
            }
        });

        recyclerViewCoffeeType();

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
            }
        });
    }

    private void recyclerViewCoffeeType() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        coffeeRecyclerView.setLayoutManager(linearLayoutManager);

        coffeeTypeAdapter = new CoffeeTypeAdapter(currentOrder.getOrderedCoffee(), getActivity());
        coffeeRecyclerView.setAdapter(coffeeTypeAdapter);
    }
}