package com.example.coffeecom;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Provider{

    private static ArrayList<BaristaModel> baristas = new ArrayList<>();
    private static ArrayList<String> coffeeType = new ArrayList<>();
    private static BaristaModel currentBarista;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        updateCoffeeType();
//
//    }

    public static ArrayList<BaristaModel> getBaristas() {
        return baristas;
    }

    public static void addBaristas(BaristaModel newBaristas) {
        baristas.add(newBaristas);
    }

    public static ArrayList<CoffeeModel> getCoffees(String coffeeType){
        ArrayList<CoffeeModel> coffeeSearched = new ArrayList<>();

        for (int i = 0; i < baristas.size(); i++) {
            for (int j = 0; j < baristas.get(i).getSellingCoffee().size(); j++) {
                if (baristas.get(i).getSellingCoffee().get(j).getCoffeeType().equals(coffeeType)){
                    coffeeSearched.add(baristas.get(i).getSellingCoffee().get(j));
                }
            }
        }

        return coffeeSearched;
    }

    public static void updateCoffeeType(){
        for (int i = 0; i < baristas.size(); i++) {
            for (int j = 0; j < baristas.get(i).getSellingCoffee().size(); j++) {
                if (coffeeType.contains(baristas.get(i).getSellingCoffee().get(j).getCoffeeType())){
                    coffeeType.add(baristas.get(i).getSellingCoffee().get(j).getCoffeeType());
                }
            }
        }
    }

    public static ArrayList<String> getCoffeeType(){
        return coffeeType;
    }

    public static BaristaModel getCurrentBarista() {
        return currentBarista;
    }

    public static void setCurrentBarista(BaristaModel newBarista) {
        currentBarista = newBarista;
    }
}
