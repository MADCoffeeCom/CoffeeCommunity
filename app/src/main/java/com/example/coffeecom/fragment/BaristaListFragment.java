package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeBaristaListAdapter;
import com.example.coffeecom.model.CoffeeModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;


public class BaristaListFragment extends Fragment {

    private TextView baristaListName, baristaListLocation, baristaListDesc;
    private ImageView baristaListChatBtn, baristaListPic;
    private ImageButton backBtn;
    private RecyclerView baristaListRecyclerView;
    private RecyclerView.Adapter coffeeListInBaristaAdapter;

    int currentBaristaIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_barista_list,container,false);

        baristaListName = view.findViewById(R.id.baristaListName);
        baristaListLocation = view.findViewById(R.id.baristaListLocation);
        baristaListDesc = view.findViewById(R.id.baristaListDesc);
        baristaListChatBtn = view.findViewById(R.id.baristaListChatBtn);
        baristaListPic = view.findViewById(R.id.baristaListPic);
        backBtn = (ImageButton) view.findViewById(R.id.backBtn);

        baristaListRecyclerView = view.findViewById(R.id.coffeeListInBaristaRecyclerView);


        for (int i = 0; i < Provider.getBaristas().size(); i++) {
            if(Provider.getBaristas().get(i).getBaristaId().equals(Provider.getCurrentBaristaId())){
                currentBaristaIndex = i;
            }
        }

        baristaListName.setText(toTitleCase(Provider.getBaristas().get(currentBaristaIndex).getUserName()));
        baristaListLocation.setText(Provider.getBaristas().get(currentBaristaIndex).getUserTaman());
        baristaListDesc.setText(Provider.getBaristas().get(currentBaristaIndex).getBaristaDesc());

        //code to update picture
        //i update this to getactivity.getpackagename by Jason
        int drawableResourceId = this.getResources().getIdentifier(Provider.getBaristas().get(currentBaristaIndex).getUserPic(), "drawable", getActivity().getPackageName());
        Glide.with(this).load(drawableResourceId).into(baristaListPic);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //need to find the previous fragment then add
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                if (activity.getSupportFragmentManager().getBackStackEntryCount()>0){
                    activity.getSupportFragmentManager().popBackStack();
                }
//                HomeActivityFragment buyCoffeeHome = new HomeActivityFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,buyCoffeeHome).addToBackStack(null).commit();

            }
        });

        baristaListChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Link to chat function with barista
            }
        });

        querySellingCoffee();

        // Inflate the layout for this fragment
        return view;
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
                data[0] = Provider.getBaristas().get(currentBaristaIndex).getBaristaId();

                PutData putData = new PutData("http://192.168.100.11/CoffeeCommunityPHP/coffeeinbarista.php", "POST", field, data);

//                FetchData fetchData = new FetchData("http://192.168.56.1/CoffeeCommunityPHP/coffeeinbarista.php");
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String coffeeId = str;
                            Provider.getBaristas().get(currentBaristaIndex).addSellingCoffeeId(coffeeId);
                            Log.i("BaristaList - Add Selling Coffee", " success");
                        }
                    }
                    //Query for coffee sold by barista and pass into recyclerview

                    ArrayList<CoffeeModel> sellingCoffee = new ArrayList<>();
                    for (int i = 0; i < Provider.getCoffees().size(); i++) {
                        for (int j = 0; j < Provider.getBaristas().get(currentBaristaIndex).getSellingCoffeeId().size(); j++) {
                            if (Provider.getCoffees().get(i).getCoffeeId().equals(Provider.getBaristas().get(currentBaristaIndex).getSellingCoffeeId().get(j))) {
                                sellingCoffee.add(Provider.getCoffees().get(i));
                            }
                        }
                    }
                    recyclerViewCoffee(sellingCoffee);
                }
            }
        });
    }

    private void recyclerViewCoffee(ArrayList<CoffeeModel> sellingCoffee) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        baristaListRecyclerView.setLayoutManager(linearLayoutManager);

        coffeeListInBaristaAdapter = new CoffeeBaristaListAdapter(Provider.getBaristas().get(currentBaristaIndex), sellingCoffee,'b');
        baristaListRecyclerView.setAdapter(coffeeListInBaristaAdapter);
    }
}