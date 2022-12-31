package com.example.coffeecom.model;

import java.sql.Time;
import java.util.Date;

public class ProfileBrewHistoryModel {
    private String brewHistoryDateTime;
    private int brewHistoryImage;
    private String brewHistoryTotalPrice;
    private String brewHistoryAmount;

    public ProfileBrewHistoryModel(String brewHistoryDateTime, int brewHistoryImage, String brewHistoryTotalPrice, String brewHistoryAmount) {
        this.brewHistoryDateTime = brewHistoryDateTime;
        this.brewHistoryImage = brewHistoryImage;
        this.brewHistoryTotalPrice = brewHistoryTotalPrice;
        this.brewHistoryAmount = brewHistoryAmount;
    }

    public String getBrewHistoryDateTime() {
        return brewHistoryDateTime;
    }

    public void setBrewHistoryDateTime(String brewHistoryDateTime) {
        this.brewHistoryDateTime = brewHistoryDateTime;
    }

    public int getBrewHistoryImage() {
        return brewHistoryImage;
    }

    public void setBrewHistoryImage(int brewHistoryImage) {
        this.brewHistoryImage = brewHistoryImage;
    }

    public String getBrewHistoryTotalPrice() {
        return brewHistoryTotalPrice;
    }

    public void setBrewHistoryTotalPrice(String brewHistoryTotalPrice) {
        this.brewHistoryTotalPrice = brewHistoryTotalPrice;
    }

    public String getBrewHistoryAmount() {
        return brewHistoryAmount;
    }

    public void setBrewHistoryAmount(String brewHistoryAmount) {
        this.brewHistoryAmount = brewHistoryAmount;
    }
}
