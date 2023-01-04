package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.adapter.LearnArticleAdapter;
import com.example.coffeecom.adapter.ProfileBrewHistoryAdapter;
import com.example.coffeecom.adapter.ProfileOrderHistoryAdapter;
import com.example.coffeecom.adapter.ProfilePostHistoryAdapter;
import com.example.coffeecom.model.OrderModel;
import com.example.coffeecom.model.PostModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class LearnActivityFragment extends Fragment {

    private RecyclerView recyclerViewGeneralArticleList, recyclerViewCoffeeHistoryArticleList, recyclerViewCoffeeBeanArticleList;
    private RecyclerView.Adapter learnArticleAdapter;

    ArrayList<String> articleTitle = new ArrayList<>();
    ArrayList<String> articlePic = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_learn,container,false);

        recyclerViewGeneralArticleList = view.findViewById(R.id.generalArticleRecyclerView);
        recyclerViewCoffeeHistoryArticleList = view.findViewById(R.id.coffeeHistoryArticleRecyclerView);
        recyclerViewCoffeeBeanArticleList = view.findViewById(R.id.coffeeBeanArticleRecycleView);

        recyclerViewGeneralArticle();
        recyclerViewCoffeeHistoryArticle();
        recyclerViewCoffeeBeanArticle();

        // Inflate the layout for this fragment
        return view;
    }

    private void recyclerViewGeneralArticle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGeneralArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articleTitle, articlePic);
        recyclerViewGeneralArticleList.setAdapter(learnArticleAdapter);
    }

    private void recyclerViewCoffeeHistoryArticle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeHistoryArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articleTitle, articlePic);
        recyclerViewCoffeeHistoryArticleList.setAdapter(learnArticleAdapter);
    }

    private void recyclerViewCoffeeBeanArticle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeBeanArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articleTitle, articlePic);
        recyclerViewCoffeeBeanArticleList.setAdapter(learnArticleAdapter);
    }


}