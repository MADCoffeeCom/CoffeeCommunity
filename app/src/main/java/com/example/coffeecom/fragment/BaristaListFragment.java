package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeBaristaListAdapter;
import com.example.coffeecom.helper.DownloadImageHelper;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


public class BaristaListFragment extends Fragment {

    private static final String TAG = "BaristaListFragment";

    private TextView baristaListName, baristaListLocation, baristaListDesc, tbSearch;
    private ImageView baristaListPic;
    private ImageButton backBtn;
    private RecyclerView baristaListRecyclerView;
    private CoffeeBaristaListAdapter coffeeListInBaristaAdapter;

    int currentBaristaIndex = 0;
    private BaristaModel currentBarista;
    ArrayList<CoffeeModel> sellingCoffee = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sellingCoffee.clear();

        View view = inflater.inflate(R.layout.activity_barista_list,container,false);
        baristaListName = view.findViewById(R.id.baristaListName);
        baristaListLocation = view.findViewById(R.id.baristaListLocation);
        baristaListDesc = view.findViewById(R.id.userDesc);
        baristaListPic = view.findViewById(R.id.baristaListPic);
        tbSearch = view.findViewById(R.id.tbSearch);
        backBtn = view.findViewById(R.id.backBtn);
        baristaListRecyclerView = view.findViewById(R.id.coffeeListInBaristaRecyclerView);


        for (int i = 0; i < Provider.getBaristas().size(); i++) {
            if(Provider.getBaristas().get(i).getBaristaId().equals(Provider.getCurrentBaristaId())){
                currentBaristaIndex = i;
                currentBarista = Provider.getBaristas().get(i);
                Log.i(TAG, "onCreateView: " + currentBaristaIndex);
            }
        }

        baristaListName.setText(toTitleCase(Provider.getBaristas().get(currentBaristaIndex).getUserName()));
        baristaListLocation.setText(Provider.getBaristas().get(currentBaristaIndex).getUserTaman());
        baristaListDesc.setText(Provider.getBaristas().get(currentBaristaIndex).getBaristaDesc());
//        Code insert pic with URL
        String picUrl = "http://" + Provider.getIpAddress() + "/images/" + Provider.getBaristas().get(currentBaristaIndex).getUserPic()+".jpg";
        CompletableFuture cf = null;
        try {
            DownloadImageHelper dit = new DownloadImageHelper(baristaListPic);
            Bitmap bitmap = cf.supplyAsync(() -> dit.execute(picUrl)).join().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Code to insert pic with Drawable
//        int drawableResourceId = this.getResources().getIdentifier(Provider.getBaristas().get(currentBaristaIndex).getUserPic(), "drawable", getActivity().getPackageName());
//        Glide.with(this).load(drawableResourceId).into(baristaListPic);
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        querySellingCoffee();

        tbSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void filter(String text) {
        ArrayList<CoffeeModel> filteredlist = new ArrayList<>();

        for (CoffeeModel coffee : sellingCoffee) {
            if (coffee.getCoffeeTitle().toLowerCase().contains(text.toLowerCase()) || coffee.getCoffeeType().toLowerCase().contains(text.toLowerCase())) {
                Log.i(TAG, "filter: " + coffee.getCoffeeTitle());
                filteredlist.add(coffee);
            }
        }

        if (!filteredlist.isEmpty()) {
            coffeeListInBaristaAdapter.filterList(filteredlist);
        }
    }


    private void querySellingCoffee() {
        Provider.getBaristas().get(currentBaristaIndex).getSellingCoffeeId().clear();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[1];
                field[0] = "baristaId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = currentBarista.getBaristaId();

                PutData putData = new PutData("http://" + Provider.getIpAddress()+ "/CoffeeCommunityPHP/coffeesoldbybarista.php", "POST", field, data);

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

        coffeeListInBaristaAdapter = new CoffeeBaristaListAdapter(Provider.getBaristas().get(currentBaristaIndex), sellingCoffee,'b', getActivity());
        baristaListRecyclerView.setAdapter(coffeeListInBaristaAdapter);
    }
}