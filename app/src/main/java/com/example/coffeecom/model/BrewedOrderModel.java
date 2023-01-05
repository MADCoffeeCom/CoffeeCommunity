package com.example.coffeecom.model;

import java.util.ArrayList;
import java.util.Date;

public class BrewedOrderModel {

    private String orderId;
    private String baristaId;
    private String baristaDesc;
    private String customerId;
    private String customerName;
    private String customerLocation;
    private Date orderStartTime;
    private Date orderFulfillTime;
    private Date duration;
    private double orderTotalPrice;
    private String orderStatus; //pending, accepted, declined, taken
    private ArrayList<CoffeeModel> orderedCoffee;
    private String orderHistoryDateTime, orderHistoryImage, orderHistoryTotalItems;

    public BrewedOrderModel(String orderId, String baristaId, String baristaDesc, String customerId, String customerName, String customerLocation, Date orderStartTime, Date orderFulfillTime, Date duration, double orderTotalPrice, String orderStatus) {
        this.orderId = orderId;
        this.baristaId = baristaId;
        this.baristaDesc = baristaDesc;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerLocation = customerLocation;
        this.orderStartTime = orderStartTime;
        this.orderFulfillTime = orderFulfillTime;
        this.duration = duration;
        this.orderTotalPrice = orderTotalPrice;
        this.orderStatus = orderStatus;
        this.orderedCoffee = new ArrayList<>();
    }

    //this is for Order History in Profile Page
    public BrewedOrderModel(String orderHistoryDateTime, String orderHistoryImage, double orderTotalPrice, String orderHistoryTotalItems) {
        this.orderHistoryDateTime = orderHistoryDateTime;
        this.orderHistoryImage = orderHistoryImage;
        this.orderTotalPrice = orderTotalPrice;
        this.orderHistoryTotalItems = orderHistoryTotalItems;
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

    public String getBaristaDesc() {
        return baristaDesc;
    }

    public void setBaristaDesc(String baristaDesc) {
        this.baristaDesc = baristaDesc;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
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

    public String getOrderHistoryDateTime() {
        return orderHistoryDateTime;
    }

    public void setOrderHistoryDateTime(String orderHistoryDateTime) {
        this.orderHistoryDateTime = orderHistoryDateTime;
    }

    public String getOrderHistoryImage() {
        return orderHistoryImage;
    }

    public void setOrderHistoryImage(String orderHistoryImage) {
        this.orderHistoryImage = orderHistoryImage;
    }

    public String getOrderHistoryTotalItems() {
        return orderHistoryTotalItems;
    }

    public void setOrderHistoryTotalItems(String orderHistoryTotalItems) {
        this.orderHistoryTotalItems = orderHistoryTotalItems;
    }
}
