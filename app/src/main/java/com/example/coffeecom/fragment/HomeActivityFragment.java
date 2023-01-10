package com.example.coffeecom.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.example.coffeecom.helper.QueryHomePage;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CartCardModel;
import com.example.coffeecom.model.CartModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.query.QueryCartItem;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;


public class  HomeActivityFragment extends Fragment {

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
                coffees = Provider.getCoffees();
                //Put here so that it pop the coffee after complete query
                recyclerViewCoffeeType();
                baristas = Provider.getBaristas();
                recyclerViewBarista();




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




    public static class QueryWaiterHomePage implements Callable<Void> {
        @Override
        public Void call() throws Exception {
            QueryHomePage.queryHomepage();
            return null;
        }
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