package com.example.coffeecom.model;

import java.util.ArrayList;

public class BaristaModel extends ProfileModel{

    private String baristaDesc;
    private ArrayList<BaristaRatingModel> ratings;
    private ArrayList<CoffeeModel> sellingCoffee;

    public BaristaModel(String username, String email, String password, String userRole, String baristaDesc, ArrayList<BaristaRatingModel> ratings, ArrayList<CoffeeModel> sellingCoffee) {
        super(username, email, password, userRole);
        this.baristaDesc = baristaDesc;
        this.ratings = ratings;
        this.sellingCoffee = sellingCoffee;
    }

    public String getBaristaDesc() {
        return baristaDesc;
    }

    public void setBaristaDesc(String baristaDesc) {
        this.baristaDesc = baristaDesc;
    }

    public ArrayList<BaristaRatingModel> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<BaristaRatingModel> ratings) {
        this.ratings = ratings;
    }

    public ArrayList<CoffeeModel> getSellingCoffee() {
        return sellingCoffee;
    }

    public void setSellingCoffee(ArrayList<CoffeeModel> sellingCoffee) {
        this.sellingCoffee = sellingCoffee;
    }
}
