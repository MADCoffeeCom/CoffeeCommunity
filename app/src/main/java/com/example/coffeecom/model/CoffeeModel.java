package com.example.coffeecom.model;

import java.util.ArrayList;

public class CoffeeModel {
    private String coffeePic;
    private String coffeeTitle, coffeeDesc, coffeeType;
    private double coffeePrice;
    private ArrayList<IngredientsModel> ingredients;
    private String userId;

    public CoffeeModel(String userId, String coffeePic, String coffeeTitle, String coffeeDesc, String coffeeType, double coffeePrice, ArrayList<IngredientsModel> ingredients) {
        this.coffeePic = coffeePic;
        this.coffeeTitle = coffeeTitle;
        this.coffeeDesc = coffeeDesc;
        this.coffeeType = coffeeType;
        this.coffeePrice = coffeePrice;
        this.ingredients = ingredients;
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

    public ArrayList<IngredientsModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<IngredientsModel> ingredients) {
        this.ingredients = ingredients;
    }
}
