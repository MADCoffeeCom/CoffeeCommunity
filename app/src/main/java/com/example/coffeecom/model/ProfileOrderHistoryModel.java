package com.example.coffeecom.model;

import java.sql.Time;
import java.util.Date;

public class ProfileOrderHistoryModel {
    private String orderHistoryDateTime;
    private int orderHistoryImage;
    private String orderHistoryTotalPrice;
    private String orderHistoryAmount;

    public ProfileOrderHistoryModel(String orderHistoryDateTime, int orderHistoryImage, String orderHistoryTotalPrice, String orderHistoryAmount) {
        this.orderHistoryDateTime = orderHistoryDateTime;
        this.orderHistoryImage = orderHistoryImage;
        this.orderHistoryTotalPrice = orderHistoryTotalPrice;
        this.orderHistoryAmount = orderHistoryAmount;
    }

    public String getOrderHistoryDateTime() {
        return orderHistoryDateTime;
    }

    public void setOrderHistoryDateTime(String orderHistoryDateTime) {
        this.orderHistoryDateTime = orderHistoryDateTime;
    }

    public int getOrderHistoryImage() {
        return orderHistoryImage;
    }

    public void setOrderHistoryImage(int orderHistoryImage) {
        this.orderHistoryImage = orderHistoryImage;
    }

    public String getOrderHistoryTotalPrice() {
        return orderHistoryTotalPrice;
    }

    public void setOrderHistoryTotalPrice(String orderHistoryTotalPrice) {
        this.orderHistoryTotalPrice = orderHistoryTotalPrice;
    }

    public String getOrderHistoryAmount() {
        return orderHistoryAmount;
    }

    public void setOrderHistoryAmount(String orderHistoryAmount) {
        this.orderHistoryAmount = orderHistoryAmount;
    }
}
