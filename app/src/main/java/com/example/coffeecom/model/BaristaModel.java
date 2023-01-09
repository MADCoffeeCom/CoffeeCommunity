package com.example.coffeecom.model;

import java.util.ArrayList;

public class BaristaModel extends ProfileModel{

    private String baristaDesc;
    private int yearsOfExperience;
    private ArrayList<BaristaRatingModel> ratings = new ArrayList<>();


    public BaristaModel(String userPic, String userStreetNo, String userTaman, String userPostalCode, ArrayList<String> sellingCoffeeId, String baristaId, String baristaDesc, int yearsOfExperience, ArrayList<BaristaRatingModel> ratings) {
        super(baristaId, userPic, userStreetNo, userTaman, userPostalCode, sellingCoffeeId);
        this.yearsOfExperience = yearsOfExperience;
        this.baristaDesc = baristaDesc;
        this.ratings = ratings;
    }

    public BaristaModel(String baristaId, String pic, String userName, String baristaDesc, String userStreetNo, String userPostalCode, String userState, String userTaman, String userLocation) {
        super(baristaId, pic, userName, userTaman, userLocation);
        this.baristaDesc = baristaDesc;
        this.setUserStreetNo(userStreetNo);
        this.setUserPostalCode(userPostalCode);
        this.setUserState(userState);

    }

    public BaristaModel(String userId) {
        super(userId);
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

    public void addRatings(BaristaRatingModel rating) {
        this.ratings.add(rating);
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

}
