package com.example.coffeecom;

import java.util.ArrayList;

public class BaristaModel {

    private String baristaPic;
    private String baristaName;
    private String baristaDesc;
    private String baristaStreetNo;
    private String baristaTaman;
    private int baristaPostalCode;
    private String baristaState;
    private ArrayList<BaristaRatingModel> ratings;
    private ArrayList<CoffeeModel> sellingCoffee;

    public BaristaModel(String baristaPic, String baristaName, String baristaDesc, String baristaStreetNo, String baristaTaman, int baristaPostalCode, String baristaState, ArrayList<BaristaRatingModel> ratings, ArrayList<CoffeeModel> sellingCoffee) {
        this.baristaPic = baristaPic;
        this.baristaName = baristaName;
        this.baristaDesc = baristaDesc;
        this.baristaStreetNo = baristaStreetNo;
        this.baristaTaman = baristaTaman;
        this.baristaPostalCode = baristaPostalCode;
        this.baristaState = baristaState;
        this.ratings = ratings;
        this.sellingCoffee = sellingCoffee;
    }

    //new barista but dunwan add coffee dulu
    public BaristaModel(String baristaPic, String baristaName, String baristaDesc, String baristaStreetNo, String baristaTaman, int baristaPostalCode, String baristaState) {
        this.baristaPic = baristaPic;
        this.baristaName = baristaName;
        this.baristaDesc = baristaDesc;
        this.baristaStreetNo = baristaStreetNo;
        this.baristaTaman = baristaTaman;
        this.baristaPostalCode = baristaPostalCode;
        this.baristaState = baristaState;
    }

    //new barista and added coffee

    public BaristaModel(String baristaPic, String baristaName, String baristaDesc, String baristaStreetNo, String baristaTaman, int baristaPostalCode, String baristaState, ArrayList<CoffeeModel> sellingCoffee) {
        this.baristaPic = baristaPic;
        this.baristaName = baristaName;
        this.baristaDesc = baristaDesc;
        this.baristaStreetNo = baristaStreetNo;
        this.baristaTaman = baristaTaman;
        this.baristaPostalCode = baristaPostalCode;
        this.baristaState = baristaState;
        this.sellingCoffee = sellingCoffee;
    }

    public String getBaristaName() {
        return baristaName;
    }

    public void setBaristaName(String baristaName) {
        this.baristaName = baristaName;
    }

    public String getBaristaDesc() {
        return baristaDesc;
    }

    public void setBaristaDesc(String baristaDesc) {
        this.baristaDesc = baristaDesc;
    }

    public String getBaristaStreetNo() {
        return baristaStreetNo;
    }

    public void setBaristaStreetNo(String baristaStreetNo) {
        this.baristaStreetNo = baristaStreetNo;
    }

    public String getBaristaTaman() {
        return baristaTaman;
    }

    public void setBaristaTaman(String baristaTaman) {
        this.baristaTaman = baristaTaman;
    }

    public int getBaristaPostalCode() {
        return baristaPostalCode;
    }

    public void setBaristaPostalCode(int baristaPostalCode) {
        this.baristaPostalCode = baristaPostalCode;
    }

    public String getBaristaState() {
        return baristaState;
    }

    public void setBaristaState(String baristaState) {
        this.baristaState = baristaState;
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

    public String getBaristaPic() {
        return baristaPic;
    }

    public void setBaristaPic(String baristaPic) {
        this.baristaPic = baristaPic;
    }
}
