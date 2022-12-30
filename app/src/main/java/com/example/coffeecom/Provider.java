package com.example.coffeecom;

import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;

import java.util.ArrayList;

public class Provider{

    private static ArrayList<CoffeeModel> coffees = new ArrayList<>();
    private static ArrayList<BaristaModel> baristas = new ArrayList<>();
    private static String currentCoffeeType;
    private static BaristaModel currentBarista;
    private static CoffeeModel currentCoffee;

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

    public static String getCurrentcoffeeType() {
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
}
