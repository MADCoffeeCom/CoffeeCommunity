package com.example.coffeecom.helper;

import com.example.coffeecom.query.NewQueryProfile;
import com.example.coffeecom.query.QueryArticle;
import com.example.coffeecom.query.QueryBankCard;
import com.example.coffeecom.query.QueryBarista;
import com.example.coffeecom.query.QueryBrewedCoffee;
import com.example.coffeecom.query.QueryCoffee;
import com.example.coffeecom.query.QueryHelpdesk;
import com.example.coffeecom.query.QueryOrderedAndPendingCoffee;
import com.example.coffeecom.query.QueryPost;
import com.example.coffeecom.query.QueryWallet;

public class QueryHomePage {


    public static void queryHomepage(){
        NewQueryProfile.queryProfile();
        QueryCoffee.queryCoffeeType();
        QueryBarista.queryBarista();
        QueryPost.queryPost();
        QueryOrderedAndPendingCoffee.queryOrderedAndPendingCoffee();
        QueryBrewedCoffee.queryOrder();
        QueryWallet.queryWallet();
        QueryBankCard.queryBankCard();
        QueryArticle.queryArticle();
        QueryHelpdesk.queryHelpdesk();
    }
}
