package com.example.coffeecom.model;

import java.util.ArrayList;
import java.util.Date;

public class OrderModel {

    private String orderId;
    private Date orderStartTime;
    private Date orderFulfillTime;
    private double orderTotalPrice;
    private String baristaId;
    private String userId;
    private ArrayList<CoffeeModel> orderedCoffee;
    private String orderStatus; //pending, accepted, declined

    public OrderModel(String orderId, Date orderStartTime, double orderTotalPrice, String baristaId, String userId, ArrayList<CoffeeModel> orderedCoffee) {
        this.orderId = orderId;
        this.orderStartTime = orderStartTime;
        this.orderTotalPrice = orderTotalPrice;
        this.baristaId = baristaId;
        this.userId = userId;
        this.orderedCoffee = orderedCoffee;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderFulfillTime() {
        return orderFulfillTime;
    }

    public void setOrderFulfillTime(Date orderFulfillTime) {
        this.orderFulfillTime = orderFulfillTime;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getBaristaId() {
        return baristaId;
    }

    public void setBaristaId(String baristaId) {
        this.baristaId = baristaId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<CoffeeModel> getOrderedCoffee() {
        return orderedCoffee;
    }

    public void setOrderedCoffee(ArrayList<CoffeeModel> orderedCoffee) {
        this.orderedCoffee = orderedCoffee;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
