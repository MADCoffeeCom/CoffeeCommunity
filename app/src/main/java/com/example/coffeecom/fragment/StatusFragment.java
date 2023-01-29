package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.model.OrderedCoffeeModel;
import com.example.coffeecom.query.QueryOrderedAndPendingCoffee;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class StatusFragment extends Fragment {

    private static final String TAG = "StatusFragment";

    TextView statusText, statusHeading1Text, statusHeading2Text;
    Button statusBtn;
    ImageButton backBtn;
    OrderedCoffeeModel currentOrder;
    int currentOrderIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_status,container,false);

        statusText = view.findViewById(R.id.statusText);
        statusHeading1Text = view.findViewById(R.id.statusHeading1Text);
        statusHeading2Text = view.findViewById(R.id.statusHeading2Text);
        statusBtn = view.findViewById(R.id.statusBtn);
        backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        Bundle bundle = this.getArguments();

        statusText.setText(bundle.getString("Title"));
        statusHeading1Text.setText(bundle.getString("Heading1"));
        statusHeading2Text.setText(bundle.getString("Heading2"));
        statusBtn.setText(bundle.getString("BtnText"));
        for (int i = 0; i < Provider.getUser().getPendingOrder().size(); i++) {
            if(Provider.getUser().getPendingOrder().get(i).getOrderId().equals(bundle.getString("orderId"))){
                Log.i(TAG, "orderId: " + bundle.getString("orderId"));
                currentOrder = Provider.getUser().getPendingOrder().get(i);
                currentOrderIndex = i;
            }
        }
        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bundle.getString("Relocate").equals("home")){

//======================================TAKEN=====================================
                    Provider.getUser().getPendingOrder().get(currentOrderIndex).setOrderStatus("T");
                    updateOrderStatus("T", currentOrder.getOrderId());
                    Provider.getUser().getPendingOrder().remove(currentOrderIndex);
                    Provider.getUser().addOrderedHistory(currentOrder);
                    Toast.makeText(view.getContext(), "Taken!", Toast.LENGTH_SHORT).show();
//                    QueryOrderedAndPendingCoffee.queryOrderedAndPendingCoffee();

                    Bundle bundle2 = new Bundle();
                    bundle2.putString("baristaId", bundle.getString("baristaId"));
                    ((BottomNavigationActivity)getActivity()).replaceFragmentWithData(new RateBaristaFragment(), bundle);
//                    ((BottomNavigationActivity)getActivity()).clearBackStack();

                }else{
                    getActivity().onBackPressed();
                    getActivity().onBackPressed();
                }
            }
        });


        // Inflate the layout for this fragment
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
                    else{
                        Log.i(TAG,result +" " + status + " " +orderId);
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
}