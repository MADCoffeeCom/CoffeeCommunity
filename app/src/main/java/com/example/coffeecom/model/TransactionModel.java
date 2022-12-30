package com.example.coffeecom.model;

public class TransactionModel {
    private int senderId;
    private int receiverId;
    private double totalPayment;
    private String paymentType; //Tng, Bank transfer or coffee wallet
    private String transactionDetails; //Top up or Payment

    public TransactionModel(int senderId, int receiverId, double totalPayment, String paymentType, String transactionDetails) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.totalPayment = totalPayment;
        this.paymentType = paymentType;
        this.transactionDetails = transactionDetails;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
}
