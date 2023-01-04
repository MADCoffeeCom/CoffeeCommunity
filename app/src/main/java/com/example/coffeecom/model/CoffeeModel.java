package com.example.coffeecom.model;

import java.util.ArrayList;

public class CoffeeModel {
    private String coffeeId;
    private String coffeePic;
    private String coffeeTitle, coffeeDesc, coffeeType;
    private double coffeePrice;
    private String ingredients;
    private String baristaId;
//    private String baristaUsername;
//    private String baristaTaman;
//    private String baristaLocation;
    private ArrayList<BaristaRatingModel> rating;

    public CoffeeModel(String coffeeId) {
        this.coffeeId = coffeeId;
    }

    public CoffeeModel(String coffeeId, String coffeePic, String coffeeTitle, String coffeeDesc, String coffeeType, double coffeePrice, String ingredients, String baristaId) {
        this.coffeeId = coffeeId;
        this.coffeePic = coffeePic;
        this.coffeeTitle = coffeeTitle;
        this.coffeeDesc = coffeeDesc;
        this.coffeeType = coffeeType;
        this.coffeePrice = coffeePrice;
        this.ingredients = ingredients;
        this.baristaId = baristaId;
//        this.baristaUsername = baristaUsername;
//        this.baristaTaman = baristaTaman;
//        this.baristaLocation = baristaLocation;
    }

    public CoffeeModel(String coffeeId, String coffeePic, String coffeeTitle, String coffeeDesc, double coffeePrice) {
        this.coffeeId = coffeeId;
        this.coffeePic = coffeePic;
        this.coffeeTitle = coffeeTitle;
        this.coffeeDesc = coffeeDesc;
        this.coffeePrice = coffeePrice;
    }

    public String getCoffeePic() {
        return coffeePic;
    }

    public void setCoffeePic(String coffeePic) {
        this.coffeePic = coffeePic;
    }

    public String getCoffeeTitle() {
        return coffeeTitle;
    }

    public void setCoffeeTitle(String coffeeTitle) {
        this.coffeeTitle = coffeeTitle;
    }

    public String getCoffeeDesc() {
        return coffeeDesc;
    }

    public void setCoffeeDesc(String coffeeDesc) {
        this.coffeeDesc = coffeeDesc;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public double getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(double coffeePrice) {
        this.coffeePrice = coffeePrice;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<BaristaRatingModel> getRating() {
        return rating;
    }

    public void setRating(ArrayList<BaristaRatingModel> rating) {
        this.rating = rating;
    }

    public String getBaristaId() {
        return baristaId;
    }

    public void setBaristaId(String baristaId) {
        this.baristaId = baristaId;
    }

    public String getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(String coffeeId) {
        this.coffeeId = coffeeId;
    }
}
