package com.example.coffeecom.model;

public class CartCardModel {
    private String coffeePic;
    private String coffeeName;
    private double coffeePrice;
    private int coffeeQuantity;

    public CartCardModel(String coffeePic, String coffeeName, double coffeePrice, int coffeeQuantity) {
        this.coffeePic = coffeePic;
        this.coffeeName = coffeeName;
        this.coffeePrice = coffeePrice;
        this.coffeeQuantity = coffeeQuantity;
    }

    public String getCoffeePic() {
        return coffeePic;
    }

    public void setCoffeePic(String coffeePic) {
        this.coffeePic = coffeePic;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public double getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(double coffeePrice) {
        this.coffeePrice = coffeePrice;
    }

    public int getCoffeeQuantity() {
        return coffeeQuantity;
    }

    public void setCoffeeQuantity(int coffeeQuantity) {
        this.coffeeQuantity = coffeeQuantity;
    }
}
