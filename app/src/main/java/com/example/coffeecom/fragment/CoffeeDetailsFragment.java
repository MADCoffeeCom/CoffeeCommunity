package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.RatingBarAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;


public class CoffeeDetailsFragment extends Fragment {

    private ImageView coffeeDetailsBackBtn;
    private TextView coffeeDetailsNameText, baristaNameCoffeeDetailsText, baristaLocationCoffeeDetailsText, ingredientText;
    private TextView coffeeDescCoffeeDetailsText;
    private TextView noRatingText;

    RecyclerView ratingRecyclerView;
    RecyclerView.Adapter ratingAdapter;

    private TextView totalPriceCoffeeDetailsText, noOfCoffeeOrderedText;
    private ImageView plusBtnCoffeeDetails, minusBtnCoffeeDetails;
    private Button addToCartBtnCoffeeDetails;

    int noOfOrder = 1;

    int currentBaristaIndex = 0;
    int currentCoffeeIndex = 0;

    BaristaModel currentBarista;
    CoffeeModel currentCoffee;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_coffee_details,container,false);

        coffeeDetailsNameText = view.findViewById(R.id.coffeeDetailsNameText);
        baristaNameCoffeeDetailsText = view.findViewById(R.id.baristaNameCoffeeDetailsText);
        baristaLocationCoffeeDetailsText = view.findViewById(R.id.baristaLocationCoffeeDetailsText);
        coffeeDescCoffeeDetailsText = view.findViewById(R.id.coffeeDescCoffeeDetailsText);
        noRatingText = view.findViewById(R.id.noRatingText);
        ingredientText = view.findViewById(R.id.ingredientText);

        ratingRecyclerView = view.findViewById(R.id.ratingRecyclerView);

        totalPriceCoffeeDetailsText = view.findViewById(R.id.totalPriceCoffeeDetailsText);
        noOfCoffeeOrderedText = view.findViewById(R.id.noOfCoffeeOrderedText);

        plusBtnCoffeeDetails = view.findViewById(R.id.plusBtnCoffeeDetails);
        minusBtnCoffeeDetails = view.findViewById(R.id.minusBtnCoffeeDetails);
        addToCartBtnCoffeeDetails = view.findViewById(R.id.addToCartBtnCoffeeDetails);
        coffeeDetailsBackBtn = view.findViewById(R.id.coffeeDetailsBackBtn);

        ratingRecyclerView = view.findViewById(R.id.ratingRecyclerView);


        for (int i = 0; i < Provider.getBaristas().size(); i++) {
            if(Provider.getBaristas().get(i).getBaristaId().equals(Provider.getCurrentBaristaId())){
                currentBaristaIndex = i;
                for (int j = 0; j < Provider.getBaristas().get(i).getSellingCoffee().size(); j++) {
                    if(Provider.getBaristas().get(i).getSellingCoffee().get(j).getCoffeeId().equals(Provider.getCurrentCoffeeId())){
                        currentCoffeeIndex = j;
                        break;
                    }
                }
            }
        }

        currentBarista = Provider.getBaristas().get(currentBaristaIndex);
        currentCoffee = Provider.getBaristas().get(currentBaristaIndex).getSellingCoffee().get(currentCoffeeIndex);

        coffeeDetailsNameText.setText(currentCoffee.getCoffeeTitle());
        baristaNameCoffeeDetailsText.setText(toTitleCase(currentBarista.getUserName()));
        baristaLocationCoffeeDetailsText.setText(currentBarista.getUserTaman());
        coffeeDescCoffeeDetailsText.setText(currentCoffee.getCoffeeDesc());
        ingredientText.setText(currentCoffee.getIngredients());

        totalPriceCoffeeDetailsText.setText(String.format("%.2f", (currentCoffee.getCoffeePrice() * noOfOrder )));
        noOfCoffeeOrderedText.setText(String.valueOf(noOfOrder));

        plusBtnCoffeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOfOrder++;
                noOfCoffeeOrderedText.setText(String.valueOf(noOfOrder));
                totalPriceCoffeeDetailsText.setText(String.format("%.2f", (currentCoffee.getCoffeePrice() * noOfOrder )));
            }
        });

        minusBtnCoffeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noOfOrder > 1) {
                    noOfOrder--;
                    noOfCoffeeOrderedText.setText(String.valueOf(noOfOrder));
                    totalPriceCoffeeDetailsText.setText(String.format("%.2f", (currentCoffee.getCoffeePrice() * noOfOrder )));
                }
            }
        });

        coffeeDetailsBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),CoffeeListFragment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();;
            }
        });

        recyclerViewRating();



        // Inflate the layout for this fragment
        return view;
    }


    private void recyclerViewRating() {
        if (currentBarista.getRatings() == null) {
            noRatingText.setVisibility(View.VISIBLE);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            ratingRecyclerView.setLayoutManager(linearLayoutManager);

            ratingAdapter = new RatingBarAdapter(currentBarista.getRatings());
            ratingRecyclerView.setAdapter(ratingAdapter);
        }
    }

}