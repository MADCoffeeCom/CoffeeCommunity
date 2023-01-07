package com.example.coffeecom;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import com.example.coffeecom.model.ArticleModel;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.model.CoffeeModel;
import com.example.coffeecom.model.HelpdeskModel;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.model.ProfileModel;
import com.example.coffeecom.model.TransactionModel;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Provider{


    private static ArrayList<CoffeeModel> coffees = new ArrayList<>();
    private static ArrayList<BaristaModel> baristas = new ArrayList<>();
    private static ArrayList<ArticleModel> articles = new ArrayList<>();
    private static ArrayList<PostModel> posts = new ArrayList<>();
    private static ArrayList<TransactionModel> transactions = new ArrayList<>();
    private static String currentCoffeeType;
    private static String currentBaristaId;
    private static String currentCoffeeId;
    private static String currentArticleId;
    private static ProfileModel user;
    private static String ipAddress;

    public static String getLocalIpAddress() {
        try {
            Log.d("ipv4","bruh");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    Log.d("ipv4","bruh1");
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        Log.d("ipv4","bruh2");
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void setIpAddress(String ipAddress) {
        Log.d("ipv4",ipAddress);
        Provider.ipAddress = ipAddress;
    }

    public static String getIpAddress() {
        return ipAddress;
    }



    public static ArrayList<CoffeeModel> getCoffees() {
        return coffees;
    }

    public static ArrayList<PostModel> getPosts() {
        return posts;
    }

    public static void addPosts(PostModel post) {
        Provider.posts.add(post);
    }

    public static void addCoffee(CoffeeModel coffee) {
        Provider.coffees.add(coffee);
        Log.i("Provider: ", "Successfully adds coffee!");
    }

    public static ArrayList<BaristaModel> getBaristas() {
        return baristas;
    }


    public static void addBarista(BaristaModel barista) {
        Provider.baristas.add(barista);
        Log.i("Provider: ", "Successfully adds barista!");
    }

    public static String getCurrentCoffeeType() {
        return currentCoffeeType;
    }

    public static void setCurrentCoffeeType(String coffeeType) {
        currentCoffeeType = coffeeType;
        Log.i("Provider: ", "Current Coffee Type " + currentCoffeeType);

    }

    public static String getCurrentBaristaId() {
        return currentBaristaId;
    }

    public static void setCurrentBaristaId(String newBarista) {
        currentBaristaId = newBarista;
    }

    public static String getCurrentCoffeeId() {
        return currentCoffeeId;
    }

    public static void setCurrentCoffeeId(String currentCoffeeId) {
        Provider.currentCoffeeId = currentCoffeeId;
    }

    public static ProfileModel getUser() {
        return user;
    }

    public static void setUser(ProfileModel user) {
        Provider.user = user;
    }

    public static ArrayList<TransactionModel> getTransactions() {
        return transactions;
    }

    public static void addTransactions(TransactionModel transaction) {
        Provider.transactions.add(transaction);
    }

    public static ArrayList<ArticleModel> getArticles() {
        return articles;
    }

    public static void addArticles(ArticleModel article) {
        articles.add(article);
    }

    public static String getCurrentArticleId() {
        return currentArticleId;
    }

    public static void setCurrentArticleId(String currentArticleId) {
        Provider.currentArticleId = currentArticleId;
    }

}
