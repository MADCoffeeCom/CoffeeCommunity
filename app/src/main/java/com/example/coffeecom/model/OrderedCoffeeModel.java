package com.example.coffeecom.model;

import java.util.ArrayList;
import java.util.Date;

public class OrderedCoffeeModel {

    private String orderId;
    private String baristaId;
    private String baristaName;
    private String baristaTaman;
    private String baristaDesc;
    private String userId;
    private Date orderStartTime;
    private Date orderFulfillTime;
    private Date duration;
    private double orderTotalPrice;
    private String orderStatus; //pending, accepted, declined, taken
    private ArrayList<CoffeeModel> orderedCoffee;

    public OrderedCoffeeModel(String orderId, String baristaId, String baristaName, String baristaTaman, String baristaDesc, String userId, Date orderStartTime, Date orderFulfillTime, Date duration, double orderTotalPrice, String orderStatus) {
        this.orderId = orderId;
        this.baristaId = baristaId;
        this.baristaName = baristaName;
        this.baristaTaman = baristaTaman;
        this.baristaDesc = baristaDesc;
        this.userId = userId;
        this.orderStartTime = orderStartTime;
        this.orderFulfillTime = orderFulfillTime;
        this.duration = duration;
        this.orderTotalPrice = orderTotalPrice;
        this.orderStatus = orderStatus;
        this.orderedCoffee = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBaristaId() {
        return baristaId;
    }

    public void setBaristaId(String baristaId) {
        this.baristaId = baristaId;
    }

    public String getBaristaName() {
        return baristaName;
    }

    public void setBaristaName(String baristaName) {
        this.baristaName = baristaName;
    }

    public String getBaristaTaman() {
        return baristaTaman;
    }

    public void setBaristaTaman(String baristaTaman) {
        this.baristaTaman = baristaTaman;
    }

    public String getBaristaDesc() {
        return baristaDesc;
    }

    public void setBaristaDesc(String baristaDesc) {
        this.baristaDesc = baristaDesc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<CoffeeModel> getOrderedCoffee() {
        return orderedCoffee;
    }

    public void addOrderedCoffee(CoffeeModel coffee) {
        this.orderedCoffee.add(coffee);
    }
}
