package com.example.coffeecom.model;

import com.example.coffeecom.Provider;

import java.util.ArrayList;

public class ProfileModel {

    private String userId;
    private String baristaId;
    private String adminId;
    private String cartId;
    private String userPic;
    private String userName, email;

    private String userStreetNo;
    private String userTaman;
    private String userPostalCode;
    private String userState;

    private String userLocation;

    private String walletId;
    private String walletPin;
    private double walletBalance;

    private ArrayList<String> sellingCoffeeId = new ArrayList<>();
    private ArrayList<OrderedCoffeeModel> orderedHistory = new ArrayList<>();
    private ArrayList<OrderedCoffeeModel> pendingOrder = new ArrayList<>();
    private ArrayList<PostModel> postedPost = new ArrayList<>();
    private ArrayList<BankCardModel> bankCard = new ArrayList<>();
    private ArrayList<BrewedOrderModel> brewedOrder = new ArrayList<>();
    private ArrayList<CartCardModel> cartItem = new ArrayList<>();

    public ProfileModel(String a) {
        this.userId = a;
    }

    public ProfileModel(String baristaId, String userPic, String userStreetNo, String userTaman, String userPostalCode, ArrayList<String> sellingCoffee) {
        this.baristaId = baristaId;
        this.userStreetNo = userStreetNo;
        this.userPic = userPic;
        this.userTaman = userTaman;
        this.userPostalCode = userPostalCode;
        this.sellingCoffeeId = sellingCoffee;
    }

    public void setUserDetails(String userPic, String userId, String baristaId, String adminId, String username, String email, String userStreetNo, String userTaman, String userPostalCode, String userState) {
        this.userPic = userPic;
        this.userId = userId;
        this.baristaId = baristaId;
        this.adminId = adminId;
        this.userName = username;
        this.email = email;
        this.userStreetNo = userStreetNo;
        this.userTaman = userTaman;
        this.userPostalCode = userPostalCode;
        this.userState = userState;
    }

    //for barista use
    public ProfileModel(String baristaId, String userPic, String userName, String userTaman, String userLocation) {
        this.baristaId = baristaId;
        this.userPic = userPic;
        this.userName = userName;
        this.userTaman = userTaman;
        this.userLocation = userLocation;
    }

    public ArrayList<CartCardModel> getCartCard() {
        return cartItem;
    }

    public CartCardModel getOneCardCart(String coffeeId){
        for (CartCardModel cc : Provider.getUser().getCartCard()) {
            if (cc.getCoffeeId().equals(coffeeId)){
                return cc;
            }
        }
        return null;
    }

    public void setCartItem(ArrayList<CartCardModel> cartItem) {
        this.cartItem = cartItem;
    }

    public void addCartCard(CartCardModel cc){
        cartItem.add(cc);
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

    public String getUserPostalCode() {
        return userPostalCode;
    }

    public void setUserPostalCode(String userPostalCode) {
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

    public ArrayList<OrderedCoffeeModel> getOrderedHistory() {
        return orderedHistory;
    }

    public void addOrderedHistory(OrderedCoffeeModel order) {
        this.orderedHistory.add(order);
    }

    public ArrayList<PostModel> getPostedPost() {
        return postedPost;
    }

    public void addPostedPost(PostModel post) {
        this.postedPost.add(post);
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

    public ArrayList<BrewedOrderModel> getBrewedOrder() {
        return brewedOrder;
    }

    public void addBrewedOrder(BrewedOrderModel order) {
        this.brewedOrder.add(order);
    }

    public ArrayList<OrderedCoffeeModel> getPendingOrder() {
        return pendingOrder;
    }

    public void addPendingOrder(OrderedCoffeeModel order) {
        this.pendingOrder.add(order);
    }
}
