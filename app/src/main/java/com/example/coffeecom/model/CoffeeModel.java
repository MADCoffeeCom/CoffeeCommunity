package com.example.coffeecom.model;

public class CoffeeModel {
    private String coffeeId;
    private String coffeePic;
    private String coffeeTitle, coffeeDesc, coffeeType;
    private double coffeePrice;
    private String ingredients;
    private String baristaId;

    public CoffeeModel(String coffeeId) {
        this.coffeeId = coffeeId;
    }

    public CoffeeModel(String coffeeId, String coffeeTitle, String coffeePic, String coffeeDesc, String coffeeType, double coffeePrice, String ingredients, String baristaId) {
        this.coffeeId = coffeeId;
        this.coffeePic = coffeePic;
        this.coffeeTitle = coffeeTitle;
        this.coffeeDesc = coffeeDesc;
        this.coffeeType = coffeeType;
        this.coffeePrice = coffeePrice;
        this.ingredients = ingredients;
        this.baristaId = baristaId;
    }

    public CoffeeModel(String coffeeId, String coffeeTitle, String coffeePic, String coffeeDesc, double coffeePrice) {
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
