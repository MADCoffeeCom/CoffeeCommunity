package com.example.coffeecom.fragment;

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
import com.example.coffeecom.adapter.CoffeeOrderAdapter;
import com.example.coffeecom.adapter.CoffeeTypeAdapter;
import com.example.coffeecom.helper.QueryHomePage;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.OrderedCoffeeModel;
import com.example.coffeecom.query.QueryCartItem;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class  HomeActivityFragment extends Fragment {

    private static final String TAG = "HomeActivityFragment";

    private RecyclerView recyclerViewCoffeeTypeList, recyclerViewBaristaList, coffeeOrderRecyclerView;
    private CoffeeTypeAdapter coffeeTypeAdapter;
    private BaristaCardAdapter baristaAdapter;
    private CoffeeOrderAdapter coffeeOrderAdapter;
    private ImageButton cartButton;
    private TextView TBSearch, noCoffeeOrderErrorText;
    ArrayList<BaristaModel> baristas = new ArrayList<>();
    ArrayList<CoffeeModel> coffees = new ArrayList<>();
    ArrayList<OrderedCoffeeModel> pendingOrder = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home,container,false);

//        Provider.setUser(new ProfileModel("UID_abang"));
        recyclerViewCoffeeTypeList = view.findViewById(R.id.coffeeListInBaristaRecyclerView);
        recyclerViewBaristaList = view.findViewById(R.id.baristaRecyclerView);
        coffeeOrderRecyclerView = view.findViewById(R.id.coffeeOrderRecyclerView);
        noCoffeeOrderErrorText = view.findViewById(R.id.noCoffeeOrderErrorText);
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
        pendingOrder = Provider.getUser().getPendingOrder();
        Log.i(TAG, "onCreateView: " + pendingOrder.size());
        recyclerViewCoffeeOrder();




        TBSearch.setText("");
        TBSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.equals("")){
                    filterBarista(String.valueOf(charSequence));
                    filterCoffee(String.valueOf(charSequence));
                    filterOrder(String.valueOf(charSequence));
                }else{
                    baristaAdapter.filterList(baristas);
                    coffeeTypeAdapter.filterList(coffees);
                    coffeeOrderAdapter.filterList(pendingOrder);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.equals("")){
                    filterBarista(String.valueOf(editable));
                    filterCoffee(String.valueOf(editable));
                    filterOrder(String.valueOf(editable));
                }else{
                    baristaAdapter.filterList(baristas);
                    coffeeTypeAdapter.filterList(coffees);
                    coffeeOrderAdapter.filterList(pendingOrder);
                }
            }

        });

        return view;
    }

    private void filterBarista(String text) {
        ArrayList<BaristaModel> filteredlist = new ArrayList<>();
        double sensitivity = 0.5;
        for (BaristaModel barista : baristas) {
                if (similarity(text, barista.getUserName()) > sensitivity || isSubString(text,barista.getUserName() )) {
//                    Log.i(TAG, "filter b: " + text + "\n" + barista.getUserName());
                    filteredlist.add(barista);
//            if (barista.getUserName().toLowerCase().contains(text.toLowerCase())) {

            }
        }
        if (!text.isEmpty()){
            baristaAdapter.filterList(filteredlist);
        }else{
            baristaAdapter.filterList(baristas);
        }

    }

    private void filterCoffee(String text) {
        ArrayList<CoffeeModel> filteredlistCoffee = new ArrayList<>();
        double sensitivity = 0.5;
        for (int i = 0; i < coffees.size(); i++) {
            if (similarity(text,(Provider.getCoffees().get(i).getCoffeeTitle())) > sensitivity|| similarity(text,Provider.getCoffees().get(i).getCoffeeType())>sensitivity || isSubString(text, Provider.getCoffees().get(i).getCoffeeTitle()) || isSubString(text, Provider.getCoffees().get(i).getCoffeeType())){
                filteredlistCoffee.add(coffees.get(i));
//                Log.i(TAG, "filter c: " + text + "\n" + coffees.get(i).getCoffeeTitle());
            }
//            if (Provider.getCoffees().get(i).getCoffeeTitle().contains(text.toLowerCase()) || Provider.getCoffees().get(i).getCoffeeType().contains(text.toLowerCase())) {


        }

        if (!text.isEmpty()) {
            coffeeTypeAdapter.filterList(filteredlistCoffee);
        }else{
            coffeeTypeAdapter.filterList(coffees);
        }
    }

    private void filterOrder(String text) {
        ArrayList<OrderedCoffeeModel> filteredOrder = new ArrayList<>();
        double sensitivity = 0.5;
        for (int i = 0; i < pendingOrder.size(); i++) {
            if (similarity(text,(Provider.getUser().getPendingOrder().get(i).getBaristaName())) > sensitivity || isSubString(text, Provider.getUser().getPendingOrder().get(i).getBaristaName())){
                filteredOrder.add(pendingOrder.get(i));
//                Log.i(TAG, "filter c: " + text + "\n" + pendingOrder.get(i).getOrderId());
            }
//            if (Provider.getCoffees().get(i).getCoffeeTitle().contains(text.toLowerCase()) || Provider.getCoffees().get(i).getCoffeeType().contains(text.toLowerCase())) {


        }

        if (!text.isEmpty()) {
            coffeeOrderAdapter.filterList(filteredOrder);
        }else{
            coffeeOrderAdapter.filterList(pendingOrder);
        }
    }

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
    /* // If you have Apache Commons Text, you can use it to calculate the edit distance:
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    public static boolean isSubString(String s1, String s2){
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return true; /* both strings are zero length */ }
        if (longer.substring(0,shorter.length()).equalsIgnoreCase(shorter)){
            return true;
        }
        return false;
    }

    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
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

    private void recyclerViewCoffeeOrder() {
        if(pendingOrder.isEmpty()){
            noCoffeeOrderErrorText.setVisibility(View.VISIBLE);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            coffeeOrderRecyclerView.setLayoutManager(linearLayoutManager);

            coffeeOrderAdapter = new CoffeeOrderAdapter(pendingOrder,getActivity());
            coffeeOrderRecyclerView.setAdapter(coffeeOrderAdapter);
        }
    }



}