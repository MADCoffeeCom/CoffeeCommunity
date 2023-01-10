package com.example.coffeecom.fragment;

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
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.BaristaCardAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CartCardModel;
import com.example.coffeecom.model.CartModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.query.QueryBankCard;
import com.example.coffeecom.query.QueryBrewedCoffee;
import com.example.coffeecom.query.QueryCartItem;
import com.example.coffeecom.query.QueryOrderedCoffee;
import com.example.coffeecom.query.QueryPost;
import com.example.coffeecom.query.QueryWallet;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class HomeActivityFragment extends Fragment {

    private static final String TAG = "HomeActivityFragment";

    private RecyclerView recyclerViewCoffeeTypeList, recyclerViewBaristaList;
    private CoffeeTypeAdapter coffeeTypeAdapter;
    private BaristaCardAdapter baristaAdapter;
    private ImageButton cartButton;
    private TextView TBSearch;
    ArrayList<BaristaModel> baristas = new ArrayList<>();
    ArrayList<CoffeeModel> coffees = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Provider.getBaristas().clear();
        Provider.getCoffees().clear();
        baristas.clear();
        coffees.clear();

        View view = inflater.inflate(R.layout.activity_home,container,false);

//        Provider.setUser(new ProfileModel("UID_abang"));
        recyclerViewCoffeeTypeList = view.findViewById(R.id.coffeeListInBaristaRecyclerView);
        recyclerViewBaristaList = view.findViewById(R.id.baristaRecyclerView);
        TBSearch = view.findViewById(R.id.TBSearch);
        cartButton = view.findViewById(R.id.BtnCart);
        cartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                QueryCartItem.queryCartItem();
                ((BottomNavigationActivity)getActivity()).replaceFragment(new CoffeeCartFragment());
//                AppCompatActivity activity = (AppCompatActivity) HomeActivityFragment.this.getContext();
//                CoffeeCartFragment baristaListFragment = new CoffeeCartFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,baristaListFragment).addToBackStack("HomeActivityFragment").commit();

//                ((BottomNavigationActivity)getActivity()).replaceFragment(new CoffeeCartFragment());
            }
        });


        queryProfile();
        queryCoffeeType();
        queryBarista();
        QueryPost.queryPost();
        QueryOrderedCoffee.queryOrderedCoffee();
        QueryBrewedCoffee.queryOrder();
        QueryWallet.queryWallet();
        QueryBankCard.queryBankCard();

        TBSearch.setText("");
        TBSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterBarista(String.valueOf(charSequence));
                filterCoffee(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        return view;
    }

    private void filterBarista(String text) {
        ArrayList<BaristaModel> filteredlist = new ArrayList<>();

        for (BaristaModel barista : baristas) {
            if (barista.getUserName().toLowerCase().contains(text.toLowerCase())) {
                Log.i(TAG, "filter: " + barista.getUserName());
                filteredlist.add(barista);
            }
        }

        if (!filteredlist.isEmpty()) {
            baristaAdapter.filterList(filteredlist);
        }
    }

    private void filterCoffee(String text) {
        ArrayList<CoffeeModel> filteredlistCoffee = new ArrayList<>();

        for (int i = 0; i < coffees.size(); i++) {
            if (Provider.getCoffees().get(i).getCoffeeTitle().contains(text.toLowerCase()) || Provider.getCoffees().get(i).getCoffeeType().contains(text.toLowerCase())) {
                Log.i(TAG, "filter: " + coffees.get(i).getCoffeeTitle());
                filteredlistCoffee.add(coffees.get(i));
            }
        }

        if (!filteredlistCoffee.isEmpty()) {
            coffeeTypeAdapter.filterList(filteredlistCoffee);
        }
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

//                            if(!coffeeTypeA.contains(coffeeType)){
//                                coffeeTypeA.add(toTitleCase(coffeeType));
//                                coffeePicA.add(coffeePicUrl);
//                            }
                        }
                    }
                    coffees = Provider.getCoffees();
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
                    baristas = Provider.getBaristas();
                    recyclerViewBarista();
                }
            }
        });
    }


    private void recyclerViewCoffeeType() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeTypeList.setLayoutManager(linearLayoutManager);

        coffeeTypeAdapter = new CoffeeTypeAdapter(coffees, getActivity());
        recyclerViewCoffeeTypeList.setAdapter(coffeeTypeAdapter);
    }

    private void recyclerViewBarista() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBaristaList.setLayoutManager(linearLayoutManager);

        baristaAdapter = new BaristaCardAdapter(baristas,getActivity());
        recyclerViewBaristaList.setAdapter(baristaAdapter);
    }



}