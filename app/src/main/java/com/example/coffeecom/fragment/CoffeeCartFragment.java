package com.example.coffeecom.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.CartAdapter;
import com.example.coffeecom.model.CartCardModel;
import com.example.coffeecom.model.CartModel;
import com.example.coffeecom.query.DeleteFromCart;
import com.example.coffeecom.query.QueryCartItem;
import com.example.coffeecom.query.QueryWallet;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class CoffeeCartFragment extends Fragment {
    private static final String TAG = "CoffeeCartFragment";
    private RecyclerView coffeeCartCardRecyclerView;
    private RecyclerView.Adapter cartAdapter;
    private ArrayList<CartModel> cartModelList;
    private Button paymentTopUpBtn;
    private Button coffeeCartPayBtn;
    private static TextView coffeeWalletAmountText;
    private static TextView coffeeCartTotalPriceText;
    private RadioButton paymentBtn1;
    private RadioButton paymentBtn2;
    private ImageView coffeeCartBckBtn;
    private ConstraintLayout cl1;
    private ConstraintLayout cl2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RadioGroup group = new RadioGroup(getContext());
        Provider.getCartModelList().clear();
        cartModelList = Provider.getCartModelList();
        View rootView = inflater.inflate(R.layout.fragment_coffee_cart, container, false);
        coffeeCartTotalPriceText = rootView.findViewById(R.id.coffeeCartTotalPriceText);
        coffeeCartBckBtn = rootView.findViewById(R.id.coffeeCartBckBtn);
        coffeeCartCardRecyclerView = rootView.findViewById(R.id.coffeeCartRecyclerView);
        coffeeCartCardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        paymentTopUpBtn = rootView.findViewById((R.id.paymentTopUpBtn));
        coffeeCartPayBtn = rootView.findViewById((R.id.coffeeCartPayBtn));
        coffeeWalletAmountText = rootView.findViewById((R.id.coffeeWalletAmountText));
        paymentBtn1 = rootView.findViewById(R.id.coffeeBalanceRadioBtn);
        paymentBtn2 = rootView.findViewById(R.id.creditDebitRadioBtn);
        cl1 = rootView.findViewById(R.id.cl1);
        cl2 = rootView.findViewById(R.id.cl2);

        paymentBtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (paymentBtn1.isChecked()){
                    paymentBtn2.setChecked(false);
                }
            }
        });

        paymentBtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (paymentBtn2.isChecked()){
                    paymentBtn1.setChecked(false);
                }
            }
        });

        try{
            coffeeWalletAmountText.setText(String.format("%.2f", Provider.getUser().getWalletBalance()));
        }catch (NullPointerException e){
            coffeeWalletAmountText.setText("0.00");
        }
        paymentTopUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Provider.getUser().getWalletPin() == null) {
                    ((BottomNavigationActivity)getActivity()).replaceFragment(new ActivateWalletFragment());
                }
                else{
                    ((BottomNavigationActivity)getActivity()).replaceFragment(new WalletFragment());
                }
            }
        });

        coffeeCartPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Confirm payment");
                builder.setMessage("Please confirm your order, it will be put into pending and waiting for the barista to accept your ordering.");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    boolean wantPurchase = true;
                                    if (paymentBtn1.isChecked()){
                                        if (Provider.getUser().getWalletBalance() < Provider.getCartTotalPrice()){
                                            Toast.makeText(getContext(),"Not enough Money", Toast.LENGTH_SHORT).show();
                                            wantPurchase = false;
                                        }else{
                                            CompletableFuture.supplyAsync(() -> new initializeWalletAmount()).join().call();
                                        }
                                    }else if(paymentBtn2.isChecked()){
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                                        builder2.setCancelable(false);
                                        builder2.setTitle("Cash on Pick Up Order Submitted");
                                        builder2.setMessage("Please kindly pay when you are picking up your coffee.");
                                        builder2.setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialog.dismiss();
                                            }
                                        });
                                        AlertDialog dialog2 = builder2.create();
                                        dialog2.show();
                                    }

                                    if (wantPurchase == true){
                                        for (CartModel cm: Provider.getCartModelList()){
                                            String orderId = "o" + new Random().nextInt(9999);
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    String[] field = new String[8];
                                                    field[0] = "orderId";
                                                    field[1] = "baristaId";
                                                    field[2] = "userId";
                                                    field[3] = "orderStartTime";
                                                    field[4] = "orderEndTime";
                                                    field[5] = "orderDuration";
                                                    field[6] = "orderTotalPrice";
                                                    field[7] = "orderStatus";

                                                    String[] data = new String[8];
                                                    data[0] = orderId;
                                                    data[1] = cm.getBarista().getBaristaId();
                                                    data[2] = Provider.getUser().getUserId();
                                                    data[3] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                                    data[4] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                                    data[5] = new SimpleDateFormat("HH:mm:ss").format(new Date());
                                                    data[6] = Double.toString(cm.getTotalPrice());
                                                    data[7] = "P";

                                                    Log.i("order Checking", data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4] + " " + data[5] + " " + data[6] + " " + data[7]);
                                                    PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addOrder.php", "POST", field, data);
                                                    if (putData.startPut()) {
                                                        if (putData.onComplete()) {
                                                            String result = putData.getResult();
                                                            Log.i(TAG, "order: " + result);
                                                        }
                                                    }
                                                }
                                            });
                                            for( CartCardModel ccm : cm.getCartCardModelsList()){
                                                Handler handler2 = new Handler(Looper.getMainLooper());
                                                handler2.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        String[] field = new String[3];
                                                        field[0] = "orderId";
                                                        field[1] = "coffeeId";
                                                        field[2] = "amount";


                                                        String[] data = new String[3];
                                                        data[0] = orderId;
                                                        data[1] = ccm.getCoffeeId();
                                                        data[2] = Integer.toString(ccm.getCoffeeQuantity());


                                                        PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addOrderItem.php", "POST", field, data);
                                                        if (putData.startPut()) {
                                                            if (putData.onComplete()) {
                                                                String result = putData.getResult();
                                                                Log.i(TAG, "order_item: " + result);
                                                            }
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                    Provider.getCartModelList().clear();
                                    DeleteFromCart.deleteFromCart();
                                    Toast.makeText(getContext(), "Successfully ordered!", Toast.LENGTH_SHORT).show();
                                    getActivity().onBackPressed();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                if (Provider.getCartModelList().size()==0){
                    Toast.makeText(getContext(),"Please add some coffee into the cart", Toast.LENGTH_SHORT).show();
                }
                else if (!paymentBtn1.isChecked() && !paymentBtn2.isChecked()){
                    Toast.makeText(getContext(),"Please select payment method", Toast.LENGTH_SHORT).show();
                }else{
                    dialog.show();
                }

            }
        });

        coffeeCartBckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });



        for (String baristaId: Provider.getBaristaIdInCart()) {
            for (int i =0; i<Provider.getBaristas().size(); i++){
                if(baristaId.equals(Provider.getBaristas().get(i).getBaristaId())){
                    ArrayList<CartCardModel> tempCartCardModelList = new ArrayList<>();
                    Log.i(TAG, "baristaId is equal to baristaId in list " + baristaId + " " + Provider.getBaristas().get(i).getBaristaId());
                    for (CartCardModel ca: Provider.getUser().getCartCard()) {
                        Log.i(TAG, "card card model found " + ca.getBaristaId() + " Current baristaId " + baristaId + Provider.getBaristas().get(i).getUserStreetNo());
                        if (ca.getBaristaId().equals(baristaId)){

                            tempCartCardModelList.add(ca);
                            Log.i(TAG, "Successfully Added CartCardModel " + tempCartCardModelList.get(0));
                        }
                    }
                    cartModelList.add(new CartModel(Provider.getBaristas().get(i), tempCartCardModelList));
                }
            }
//            tempCartCardModelList.clear();
        }

        Log.i(TAG, ""+cartModelList.size());
        cartAdapter = new CartAdapter(getContext(), cartModelList);
        coffeeCartCardRecyclerView.setAdapter(cartAdapter);
        cartModelList = createCartModelList();
        try {
            CompletableFuture.supplyAsync(() -> new initializeCartTotalPrice()).join().call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Completed", "Completed Thread");


        return rootView;
    }

    private ArrayList<CartModel> createCartModelList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        coffeeCartCardRecyclerView.setLayoutManager(linearLayoutManager);

        coffeeCartCardRecyclerView.setAdapter(cartAdapter);

        // Code to create a list of CartModel objects goes here
        return cartModelList;
    }

    private void recyclerViewCartModel(){


    }

    public static class initializeCartTotalPrice implements Callable<Void> {
        @Override
        public Void call() throws Exception {
            Provider.setCartTotalPrice(0);
            QueryCartItem.queryCartItem();
            for (CartModel cm : Provider.getCartModelList()){
                for (CartCardModel ccm : cm.getCartCardModelsList()){
                    Log.i("Home", "before "+Provider.getCartTotalPrice());
                    cm.setTotalPrice(cm.getTotalPrice() + (ccm.getCoffeePrice()*ccm.getCoffeeQuantity()));
                    Provider.setCartTotalPrice(Provider.getCartTotalPrice() + (ccm.getCoffeePrice()*ccm.getCoffeeQuantity()));
                    Log.i("Home", "after "+Provider.getCartTotalPrice());
                }
            }
            coffeeCartTotalPriceText.setText(String.format("%.2f", Provider.getCartTotalPrice()));
            return null;
        }
    }

    public static class initializeWalletAmount implements Callable<Void> {
        @Override
        public Void call() throws Exception {
            Provider.getUser().setWalletBalance(Provider.getUser().getWalletBalance() - Provider.getCartTotalPrice());
            coffeeWalletAmountText.setText(String.format("%.2f", Provider.getUser().getWalletBalance()));
            QueryWallet.updateWallet();
            return null;
        }
    }
}