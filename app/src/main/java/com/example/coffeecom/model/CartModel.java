package com.example.coffeecom.model;

import java.util.List;

public class CartModel {
    private String baristaAddress;
    private List<CartCardModel> cartCardModelsList;

    public CartModel(String baristaAddress, List<CartCardModel> cartCardModelsList) {
        this.baristaAddress = baristaAddress;
        this.cartCardModelsList = cartCardModelsList;
    }

    public String getBaristaAddress() {
        return baristaAddress;
    }

    public void setBaristaAddress(String baristaAddress) {
        this.baristaAddress = baristaAddress;
    }

    public List<CartCardModel> getCartCardModelsList() {
        return cartCardModelsList;
    }

    public void setCartCardModelsList(List<CartCardModel> cartCardModelsList) {
        this.cartCardModelsList = cartCardModelsList;
    }

}

