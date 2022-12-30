package com.example.coffeecom;

import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.ProfileModel;
import com.example.coffeecom.model.TransactionModel;

import java.util.ArrayList;

public class Provider{


    private static ArrayList<CoffeeModel> coffees = new ArrayList<>();
    private static ArrayList<BaristaModel> baristas = new ArrayList<>();
    private static String currentCoffeeType;
    private static BaristaModel currentBarista;
    private static CoffeeModel currentCoffee;
    private static ProfileModel user;

    private static ArrayList<TransactionModel> transactions = new ArrayList<>();

    public static void addBaristas(BaristaModel newBaristas) {
        //need to insert code to update new baristas in sql here
        baristas.add(newBaristas);
    }

    public static ArrayList<CoffeeModel> getCoffees() {
        return coffees;
    }

    public static void setCoffees(ArrayList<CoffeeModel> coffees) {
        Provider.coffees = coffees;
    }

    public static ArrayList<BaristaModel> getBaristas() {
        return baristas;
    }

    public static void setBaristas(ArrayList<BaristaModel> baristas) {
        Provider.baristas = baristas;
    }

    public static String getCurrentCoffeeType() {
        return currentCoffeeType;
    }

    public static void setCurrentcoffeeType(String currentcoffeeType) {
        Provider.currentCoffeeType = currentcoffeeType;
    }

    public static BaristaModel getCurrentBarista() {
        return currentBarista;
    }

    public static void setCurrentBarista(BaristaModel newBarista) {
        currentBarista = newBarista;
    }

    public static CoffeeModel getCurrentCoffee() {
        return currentCoffee;
    }

    public static void setCurrentCoffee(CoffeeModel currentCoffee) {
        Provider.currentCoffee = currentCoffee;
    }

    public static ProfileModel getUser() {
        return user;
    }

    public static ArrayList<TransactionModel> getTransactions() {
        return transactions;
    }

    public static void setTransactions(ArrayList<TransactionModel> transactions) {
        Provider.transactions = transactions;
    }
}
