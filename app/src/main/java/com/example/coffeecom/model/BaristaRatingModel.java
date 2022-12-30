package com.example.coffeecom.model;

public class BaristaRatingModel {
    private int rating;
    private String ratingDesc;
    private String raterName;

    public BaristaRatingModel(int rating, String ratingDesc, String raterName) {
        this.rating = rating;
        this.ratingDesc = ratingDesc;
        this.raterName = raterName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRatingDesc() {
        return ratingDesc;
    }

    public void setRatingDesc(String ratingDesc) {
        this.ratingDesc = ratingDesc;
    }

    public String getRaterName() {
        return raterName;
    }

    public void setRaterName(String raterName) {
        this.raterName = raterName;
    }
}
