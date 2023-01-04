package com.example.coffeecom.model;

import java.util.ArrayList;

public class ProfileModel {

    private String userId;
    private String baristaId;
    private String adminId;

    private String userPic;
    private String userName, email;
    private String userRole;

    private String userStreetNo;
    private String userTaman;
    private int userPostalCode;
    private String userState;

    private String userLocation;

    private String walletId;
    private String walletPin;
    private double walletBalance;

    private ArrayList<String> sellingCoffeeId = new ArrayList<>();
    private ArrayList<OrderModel> orderedHistory = new ArrayList<>();
    private ArrayList<OrderModel> brewedHistory = new ArrayList<>();
    private ArrayList<PostModel> postedPost = new ArrayList<>();
    private ArrayList<BankCardModel> bankCard = new ArrayList<>();
    private ArrayList<OrderModel> pendingOrder = new ArrayList<>();


    public ProfileModel(String a) {
        this.userId = a;
    }

    public ProfileModel() {
    }

    public ProfileModel(String baristaId, String userPic, String userStreetNo, String userTaman, int userPostalCode, ArrayList<String> sellingCoffee) {
        this.baristaId = baristaId;
        this.userStreetNo = userStreetNo;
        this.userPic = userPic;
        this.userTaman = userTaman;
        this.userPostalCode = userPostalCode;
        this.sellingCoffeeId = sellingCoffee;
    }

    public void setUserDetails(String userPic, String userId, String username, String email, String userRole, String userStreetNo, String userTaman, int userPostalCode, String userState, String walletId, String walletPin, double walletBalance) {
        this.userPic = userPic;
        this.userId = userId;
        this.userName = username;
        this.email = email;
        this.userRole = userRole;
        this.userStreetNo = userStreetNo;
        this.userTaman = userTaman;
        this.userPostalCode = userPostalCode;
        this.userState = userState;
        this.walletPin = walletPin;
        this.walletId = walletId;
        this.walletBalance = walletBalance;
    }

    //for barista use
    public ProfileModel(String baristaId, String userPic, String userName, String userTaman, String userLocation) {
        this.baristaId = baristaId;
        this.userPic = userPic;
        this.userName = userName;
        this.userTaman = userTaman;
        this.userLocation = userLocation;
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

    public String getWalletPin() {
        return walletPin;
    }

    public void setWalletPin(String walletPin) {
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

    public ArrayList<String> getSellingCoffeeId() {
        return sellingCoffeeId;
    }

    public void addSellingCoffeeId(String coffee) {
        sellingCoffeeId.add(coffee);
    }

    public ArrayList<OrderModel> getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(ArrayList<OrderModel> pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public String getBaristaId() {
        return baristaId;
    }

    public void setBaristaId(String baristaId) {
        this.baristaId = baristaId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }
}
