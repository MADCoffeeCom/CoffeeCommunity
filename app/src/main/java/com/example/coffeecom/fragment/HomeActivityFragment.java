package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;
import static com.example.coffeecom.query.QueryBrewedCoffee.queryOrder;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.BaristaCardAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.ProfileModel;
import com.example.coffeecom.query.QueryBankCard;
import com.example.coffeecom.query.QueryBrewedCoffee;
import com.example.coffeecom.query.QueryCartItem;
import com.example.coffeecom.query.QueryOrderedCoffee;
import com.example.coffeecom.query.QueryPost;
import com.example.coffeecom.query.QuerySellingCoffee;
import com.example.coffeecom.query.QueryWallet;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;


public class HomeActivityFragment extends Fragment {

    private RecyclerView recyclerViewCoffeeTypeList, recyclerViewBaristaList;
    private RecyclerView.Adapter coffeeTypeAdapter, baristaAdapter;
    private ImageButton cartButton;
    ArrayList<String> coffeeTypeA = new ArrayList<>();
    ArrayList<String> coffeePicA = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Provider.getBaristas().clear();
        Provider.getCoffees().clear();
        coffeeTypeA.clear();
        coffeePicA.clear();

        View view = inflater.inflate(R.layout.activity_home,container,false);

        Provider.setUser(new ProfileModel("UID_abang"));
        recyclerViewCoffeeTypeList = view.findViewById(R.id.coffeeListInBaristaRecyclerView);
        recyclerViewBaristaList = view.findViewById(R.id.baristaRecyclerView);
        cartButton = view.findViewById(R.id.BtnCart);
        cartButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                QueryCartItem.queryCartItem();
                ((BottomNavigationActivity)getActivity()).replaceFragment(new CoffeeCartFragment());
            }
        });
        QueryPost.queryPost();
        QueryOrderedCoffee.queryOrderedCoffee();
        QueryWallet.queryWallet();
        QueryBankCard.queryBankCard();

        queryProfile();
        queryCoffeeType();
        queryBarista();

        return view;
    }

    private void queryProfile() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "username";

                //Creating array for data
                String[] data = new String[1];
                data[0] = Provider.getUser().getUserId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/profile.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();

                        String[] profileDetails = result.split(" - ");
                        String userId = profileDetails[0];
                        String picUrl = profileDetails[1];
                        String baristaId = profileDetails[2];
                        String adminId = profileDetails[3];
                        String userName = profileDetails[4];
                        String email = profileDetails[5];
                        String streetNo = profileDetails[6];
                        String taman = profileDetails[7];
                        String postCode = (profileDetails[8]);
                        String state = profileDetails[9];

                        Provider.getUser().setUserDetails(picUrl, userId, baristaId, adminId, userName, email, streetNo, taman, postCode, state);

                        Log.i(Provider.getUser().getUserName(), "User Updated in Provider" + Provider.getUser().getUserId());

                    }
                }
            }
        });
    }

    private void queryCoffeeType() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/coffee.php");

                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        String[] resultSplitted = new String[11];
                        resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
                            String[] strDetails = str.split(" - ");

                            String coffeeId = strDetails[0];
                            String coffeeTitle = strDetails[1];
                            String coffeePicUrl = strDetails[2];
                            String coffeeDesc = strDetails[3];
                            String coffeeType = strDetails[4];
                            double coffeePrice = Double.parseDouble(strDetails[5]);
                            String ingredients = strDetails[6];
                            String baristaId = strDetails[7];
//                            String baristaName = strDetails[8];
//                            String baristaTaman = strDetails[9];
//                            String baristaLocation = strDetails[10];

                            Log.i("coffeeTitle", coffeeTitle);
                            Log.i("coffeePicUrl", coffeePicUrl);


                            CoffeeModel coffee = new CoffeeModel(coffeeId, coffeeTitle, coffeePicUrl, coffeeDesc, coffeeType, coffeePrice, ingredients, baristaId);
                            Log.i("in model coffeeTitle", coffee.getCoffeeTitle());
                            Log.i("in model coffeePicUrl", coffee.getCoffeePic());

                            Provider.addCoffee(coffee);
                            Log.i("Home Add new coffee", coffee.getCoffeeId() + " added!");

                            if(!coffeeTypeA.contains(coffeeType)){
                                coffeeTypeA.add(toTitleCase(coffeeType));
                                coffeePicA.add(coffeePicUrl);
                            }
                        }
                    }
                    //Put here so that it pop the coffee after complete query
                    recyclerViewCoffeeType();
                }
            }
        });
    }

    private void queryBarista() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/barista.php");

                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {

                            String[] baristaDetails = str.split(" - ");
                            String baristaId = baristaDetails[0];
                            String baristaPic = baristaDetails[1];
                            String username = baristaDetails[2];
                            String baristaDesc = baristaDetails[3];
                            String userStreetNo =  baristaDetails[4];
                            String userPostalCode = baristaDetails[5];
                            String userState = baristaDetails[6];
                            String userTaman = baristaDetails[7];
                            String userLocation = baristaDetails[8];

                            BaristaModel barista = new BaristaModel(baristaId, baristaPic, username, baristaDesc, userStreetNo, userPostalCode, userState, userTaman, userLocation);

                            if (!Provider.getBaristas().contains(barista)){
                                Provider.addBarista(barista);
                                //                            Log.i("HomeActivity - Add Barista", "");
                            }
                        }
                    }
                    recyclerViewBarista();
                }
            }
        });
    }


    private void recyclerViewCoffeeType() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeTypeList.setLayoutManager(linearLayoutManager);

        coffeeTypeAdapter = new CoffeeTypeAdapter(coffeeTypeA, coffeePicA, getActivity());
        recyclerViewCoffeeTypeList.setAdapter(coffeeTypeAdapter);
    }

    private void recyclerViewBarista() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBaristaList.setLayoutManager(linearLayoutManager);

        baristaAdapter = new BaristaCardAdapter(Provider.getBaristas(),getActivity());
        recyclerViewBaristaList.setAdapter(baristaAdapter);
    }


}