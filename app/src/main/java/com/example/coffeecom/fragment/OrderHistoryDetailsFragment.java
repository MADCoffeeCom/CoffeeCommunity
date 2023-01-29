package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.FormatDateTime.convertDatetoStringDate;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeOrderAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.model.BrewedOrderModel;
import com.example.coffeecom.model.OrderedCoffeeModel;

public class OrderHistoryDetailsFragment extends Fragment {

    ImageButton backBtn;
    TextView orderId, orderStartTimeText, orderEndTimeText, totalPriceText, orderHistoryDetailsText;
    RecyclerView orderCoffeeRV;

    char orderType; // b for brew, o for order

    BrewedOrderModel currentBrew;
    OrderedCoffeeModel currentOrder;
    private CoffeeTypeAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_history_details, container, false);
        initialiseView(view);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            if(bundle.getString("type").equals("brew")){
                for (BrewedOrderModel brew: Provider.getUser().getBrewedOrder()) {
                    if(brew.getOrderId().equals(bundle.getString("orderId"))) currentBrew = brew;
                }
                loadBrew(view);
            }else if(bundle.getString("type").equals("order")){
                for (OrderedCoffeeModel order: Provider.getUser().getOrderedHistory()) {
                    if(order.getOrderId().equals(bundle.getString("orderId"))) currentOrder = order;
                }
                loadOrder(view);
            }
        }


        return view;
    }

    private void initialiseView(View view) {
        backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
        orderId = view.findViewById(R.id.orderId);
        orderStartTimeText = view.findViewById(R.id.orderStartTimeText);
        orderEndTimeText = view.findViewById(R.id.orderEndTimeText);
        totalPriceText = view.findViewById(R.id.totalPriceText);
        orderCoffeeRV = view.findViewById(R.id.orderCoffeeRV);
        orderHistoryDetailsText = view.findViewById(R.id.orderHistoryDetailsText);
    }

    public void loadBrew(View view){
        orderType = 'b';
        orderId.setText(currentBrew.getOrderId());
        orderHistoryDetailsText.setText("Brew History");
        orderStartTimeText.setText(convertDatetoStringDate(currentBrew.getOrderStartTime()));
        orderEndTimeText.setText(convertDatetoStringDate(currentBrew.getOrderFulfillTime()));
        totalPriceText.setText("" + currentBrew.getOrderTotalPrice());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        orderCoffeeRV.setLayoutManager(linearLayoutManager);

        adapter = new CoffeeTypeAdapter(currentBrew.getOrderedCoffee(), getActivity());
        orderCoffeeRV.setAdapter(adapter);

    }

    public void loadOrder(View view){
        orderType = 'o';
        orderId.setText(currentOrder.getOrderId());
        orderStartTimeText.setText(convertDatetoStringDate(currentOrder.getOrderStartTime()));
        orderEndTimeText.setText(convertDatetoStringDate(currentOrder.getOrderFulfillTime()));
        totalPriceText.setText("" + currentOrder.getOrderTotalPrice());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        orderCoffeeRV.setLayoutManager(linearLayoutManager);

        adapter = new CoffeeTypeAdapter(currentOrder.getOrderedCoffee(), getActivity());
        orderCoffeeRV.setAdapter(adapter);

    }

//    private void recyclerViewCoffeeType() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        orderCoffeeRV.setLayoutManager(linearLayoutManager);
//
//        adapter = new CoffeeTypeAdapter(cur, getActivity());
//        orderCoffeeRV.setAdapter(adapter);
//    }
}