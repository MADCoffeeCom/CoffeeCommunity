package com.example.coffeecom.helper;

import com.example.coffeecom.query.NewQueryProfile;
import com.example.coffeecom.query.QueryArticle;
import com.example.coffeecom.query.QueryBankCard;
import com.example.coffeecom.query.QueryBarista;
import com.example.coffeecom.query.QueryBrewedCoffee;
import com.example.coffeecom.query.QueryCoffeeType;
import com.example.coffeecom.query.QueryOrderedCoffee;
import com.example.coffeecom.query.QueryPost;
import com.example.coffeecom.query.QueryWallet;

public class QueryHomePage {


    public static void queryHomepage(){
        NewQueryProfile.queryProfile();
        QueryCoffeeType.queryCoffeeType();
        QueryBarista.queryBarista();
        QueryPost.queryPost();
        QueryOrderedCoffee.queryOrderedCoffee();
        QueryBrewedCoffee.queryOrder();
        QueryWallet.queryWallet();
        QueryBankCard.queryBankCard();
        QueryArticle.queryArticle();
    }
}
