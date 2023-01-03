package com.example.coffeecom.model;

import java.util.ArrayList;

public class ProfileModel {

    private String userPic;
    private String userId;
    private String userName, email, password;
    private String userRole;

    private String userStreetNo;
    private String userTaman;
    private int userPostalCode;
    private String userState;

    private int walletPin;
    private double walletBalance;

    ArrayList<OrderModel> orderedHistory = new ArrayList<>();
    ArrayList<OrderModel> brewedHistory = new ArrayList<>();
    ArrayList<PostModel> postedPost = new ArrayList<>();
    ArrayList<BankCardModel> bankCard = new ArrayList<>();
    ArrayList<CoffeeModel> sellingCoffee = new ArrayList<>();
    ArrayList<OrderModel> pendingOrder = new ArrayList<>();
//    ArrayList<TransactionModel> transactionHistories = new ArrayList<>();

    public ProfileModel(String userPic, String userId, String username, String email, String password, String userRole, String userStreetNo, String userTaman, int userPostalCode, String userState, int walletPin, double walletBalance, ArrayList<OrderModel> orderedHistory, ArrayList<OrderModel> brewedHistory, ArrayList<PostModel> postedPost, ArrayList<BankCardModel> bankCard) {
        this.userPic = userPic;
        this.userId = userId;
        this.userName = username;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.userStreetNo = userStreetNo;
        this.userTaman = userTaman;
        this.userPostalCode = userPostalCode;
        this.userState = userState;
        this.walletPin = walletPin;
        this.walletBalance = walletBalance;
        this.orderedHistory = orderedHistory;
        this.brewedHistory = brewedHistory;
        this.postedPost = postedPost;
        this.bankCard = bankCard;
    }

    public ProfileModel(String username, String email, String password, String userRole) {
        this.userName = username;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserStreetNo() {
        return userStreetNo;
    }

    public void setUserStreetNo(String userStreetNo) {
        this.userStreetNo = userStreetNo;
    }

    public String getUserTaman() {
        return userTaman;
    }

    public void setUserTaman(String userTaman) {
        this.userTaman = userTaman;
    }

    public int getUserPostalCode() {
        return userPostalCode;
    }

    public void setUserPostalCode(int userPostalCode) {
        this.userPostalCode = userPostalCode;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public int getWalletPin() {
        return walletPin;
    }

    public void setWalletPin(int walletPin) {
        this.walletPin = walletPin;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public ArrayList<OrderModel> getOrderedHistory() {
        return orderedHistory;
    }

    public void setOrderedHistory(ArrayList<OrderModel> orderedHistory) {
        this.orderedHistory = orderedHistory;
    }

    public ArrayList<OrderModel> getBrewedHistory() {
        return brewedHistory;
    }

    public void setBrewedHistory(ArrayList<OrderModel> brewedHistory) {
        this.brewedHistory = brewedHistory;
    }

    public ArrayList<PostModel> getPostedPost() {
        return postedPost;
    }

    public void setPostedPost(ArrayList<PostModel> postedPost) {
        this.postedPost = postedPost;
    }

    public ArrayList<BankCardModel> getBankCard() {
        return bankCard;
    }

    public void setBankCard(ArrayList<BankCardModel> bankCard) {
        this.bankCard = bankCard;
    }

    public void addBankCard(BankCardModel card) {
        bankCard.add(card);
    }

    public ArrayList<CoffeeModel> getSellingCoffee() {
        return sellingCoffee;
    }

    public void setSellingCoffee(ArrayList<CoffeeModel> sellingCoffee) {
        this.sellingCoffee = sellingCoffee;
    }

    public ArrayList<OrderModel> getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(ArrayList<OrderModel> pendingOrder) {
        this.pendingOrder = pendingOrder;
    }
}
