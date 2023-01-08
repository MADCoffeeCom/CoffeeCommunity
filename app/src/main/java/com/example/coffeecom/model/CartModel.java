package com.example.coffeecom.model;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.fragment.CoffeeCartFragment;

import java.util.ArrayList;
import java.util.List;

public class CartModel {
    private BaristaModel barista;
    private String baristaAddress;
    private ArrayList<CartCardModel> cartCardModelsList;


    public CartModel(BaristaModel barista, ArrayList<CartCardModel> cartCardModelsList) {
        this.barista = barista;
        this.cartCardModelsList = cartCardModelsList;
        this.baristaAddress = barista.getUserStreetNo() + " " + barista.getUserTaman() + " " + barista.getUserState() + " ";
    }

    public BaristaModel getBarista() {
        return barista;
    }

    public void setBarista(BaristaModel barista) {
        this.barista = barista;
    }

    public String getBaristaAddress() {
        return baristaAddress;
    }

    public void setBaristaAddress(String baristaAddress) {
        this.baristaAddress = baristaAddress;
    }

    public ArrayList<CartCardModel> getCartCardModelsList() {
        return cartCardModelsList;
    }

    public void addCartCardModel (CartCardModel cc){
        cartCardModelsList.add(cc);
    }

    public void setCartCardModelsList(ArrayList<CartCardModel> cartCardModelsList) {
        this.cartCardModelsList = cartCardModelsList;
    }


}

