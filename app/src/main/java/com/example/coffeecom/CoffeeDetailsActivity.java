package com.example.coffeecom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CoffeeDetailsActivity extends AppCompatActivity {

    private ImageView coffeeDetailsBackBtn;
    private TextView coffeeDetailsNameText, baristaNameCoffeeDetailsText, baristaLocationCoffeeDetailsText;
    private TextView coffeeDescCoffeeDetailsText;
    private TextView noRatingText;

    RecyclerView ratingRecyclerView, ingredientsRecyclerView;
    RecyclerView.Adapter ratingAdapter, ingredientsAdapter;

    private TextView totalPriceCoffeeDetailsText, noOfCoffeeOrderedText;
    private ImageView plusBtnCoffeeDetails, minusBtnCoffeeDetails;
    private Button addToCartBtnCoffeeDetails;

    int noOfOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_details);

        initializeView();

        coffeeDetailsNameText.setText(Provider.getCurrentCoffee().getCoffeeTitle());
        baristaNameCoffeeDetailsText.setText(Provider.getCurrentBarista().getBaristaName());
        baristaLocationCoffeeDetailsText.setText(Provider.getCurrentBarista().getBaristaTaman());
        coffeeDescCoffeeDetailsText.setText(Provider.getCurrentCoffee().getCoffeeDesc());
        coffeeDetailsNameText.setText(Provider.getCurrentCoffee().getCoffeeTitle());

        totalPriceCoffeeDetailsText.setText(String.format("%.2f", (Provider.getCurrentCoffee().getCoffeePrice() * noOfOrder )));
        noOfCoffeeOrderedText.setText(String.valueOf(noOfOrder));

        plusBtnCoffeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOfOrder++;
                noOfCoffeeOrderedText.setText(String.valueOf(noOfOrder));
                totalPriceCoffeeDetailsText.setText(String.format("%.2f", (Provider.getCurrentCoffee().getCoffeePrice() * noOfOrder )));
            }
        });

        minusBtnCoffeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noOfOrder > 1) {
                    noOfOrder--;
                    noOfCoffeeOrderedText.setText(String.valueOf(noOfOrder));
                    totalPriceCoffeeDetailsText.setText(String.format("%.2f", (Provider.getCurrentCoffee().getCoffeePrice() * noOfOrder )));
                }
            }
        });

        recyclerViewRating();
        recyclerViewIngredients();
//        Log.d(Provider.getCurrentBarista().getRatings().toString(), "why so buggy");

    }

    public void initializeView() {

        coffeeDetailsNameText = findViewById(R.id.coffeeDetailsNameText);
        baristaNameCoffeeDetailsText = findViewById(R.id.baristaNameCoffeeDetailsText);
        baristaLocationCoffeeDetailsText = findViewById(R.id.baristaLocationCoffeeDetailsText);
        coffeeDescCoffeeDetailsText = findViewById(R.id.coffeeDescCoffeeDetailsText);
        noRatingText = findViewById(R.id.noRatingText);

        ratingRecyclerView = findViewById(R.id.ratingRecyclerView);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);

        totalPriceCoffeeDetailsText = findViewById(R.id.totalPriceCoffeeDetailsText);
        noOfCoffeeOrderedText = findViewById(R.id.noOfCoffeeOrderedText);

        plusBtnCoffeeDetails = findViewById(R.id.plusBtnCoffeeDetails);
        minusBtnCoffeeDetails = findViewById(R.id.minusBtnCoffeeDetails);
        addToCartBtnCoffeeDetails = findViewById(R.id.addToCartBtnCoffeeDetails);
        coffeeDetailsBackBtn = findViewById(R.id.coffeeDetailsBackBtn);
    }

    private void recyclerViewRating() {
        if (Provider.getCurrentBarista().getRatings() == null) {
            noRatingText.setVisibility(View.VISIBLE);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            ratingRecyclerView = findViewById(R.id.ratingRecyclerView);
            ratingRecyclerView.setLayoutManager(linearLayoutManager);

            ratingAdapter = new RatingBarAdapter(Provider.getCurrentBarista().getRatings());
            ratingRecyclerView.setAdapter(ratingAdapter);
        }
    }

    private void recyclerViewIngredients() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);
        ingredientsRecyclerView.setLayoutManager(linearLayoutManager);

        ingredientsAdapter = new IngredientsAdapter(Provider.getCurrentCoffee().getIngredients());
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
    }
}