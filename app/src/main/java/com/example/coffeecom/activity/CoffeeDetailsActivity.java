package com.example.coffeecom.activity;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.RatingBarAdapter;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;

public class CoffeeDetailsActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_details);

        initializeView();

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
                finish();
            }
        });

        recyclerViewRating();

    }

    public void initializeView() {
        coffeeDetailsNameText = findViewById(R.id.coffeeDetailsNameText);
        baristaNameCoffeeDetailsText = findViewById(R.id.baristaNameCoffeeDetailsText);
        baristaLocationCoffeeDetailsText = findViewById(R.id.baristaLocationCoffeeDetailsText);
        coffeeDescCoffeeDetailsText = findViewById(R.id.coffeeDescCoffeeDetailsText);
        noRatingText = findViewById(R.id.noRatingText);
        ingredientText = findViewById(R.id.ingredientText);

        ratingRecyclerView = findViewById(R.id.ratingRecyclerView);

        totalPriceCoffeeDetailsText = findViewById(R.id.totalPriceCoffeeDetailsText);
        noOfCoffeeOrderedText = findViewById(R.id.noOfCoffeeOrderedText);

        plusBtnCoffeeDetails = findViewById(R.id.plusBtnCoffeeDetails);
        minusBtnCoffeeDetails = findViewById(R.id.minusBtnCoffeeDetails);
        addToCartBtnCoffeeDetails = findViewById(R.id.addToCartBtnCoffeeDetails);
        coffeeDetailsBackBtn = findViewById(R.id.coffeeDetailsBackBtn);
    }

    private void recyclerViewRating() {
        if (currentBarista.getRatings() == null) {
            noRatingText.setVisibility(View.VISIBLE);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            ratingRecyclerView = findViewById(R.id.ratingRecyclerView);
            ratingRecyclerView.setLayoutManager(linearLayoutManager);

            ratingAdapter = new RatingBarAdapter(currentBarista.getRatings());
            ratingRecyclerView.setAdapter(ratingAdapter);
        }
    }

}