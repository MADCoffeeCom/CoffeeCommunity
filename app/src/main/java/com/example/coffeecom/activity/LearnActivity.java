package com.example.coffeecom.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.LearnArticleAdapter;

import java.util.ArrayList;

public class LearnActivity extends AppCompatActivity {
    private RecyclerView recyclerViewGeneralArticleList, recyclerViewCoffeeHistoryArticleList, recyclerViewCoffeeBeanArticleList;
    private RecyclerView.Adapter learnArticleAdapter;

    ArrayList<String> articleTitle = new ArrayList<>();
    ArrayList<String> articlePic = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        recyclerViewGeneralArticle();
        recyclerViewCoffeeHistoryArticle();
        recyclerViewCoffeeBeanArticle();
    }

    private void recyclerViewGeneralArticle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGeneralArticleList = findViewById(R.id.generalArticleRecyclerView);
        recyclerViewGeneralArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articleTitle, articlePic);
        recyclerViewGeneralArticleList.setAdapter(learnArticleAdapter);
    }

    private void recyclerViewCoffeeHistoryArticle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeHistoryArticleList = findViewById(R.id.coffeeHistoryArticleRecyclerView);
        recyclerViewCoffeeHistoryArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articleTitle, articlePic);
        recyclerViewCoffeeHistoryArticleList.setAdapter(learnArticleAdapter);
    }

    private void recyclerViewCoffeeBeanArticle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeBeanArticleList = findViewById(R.id.coffeeBeanArticleRecycleView);
        recyclerViewCoffeeBeanArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articleTitle, articlePic);
        recyclerViewCoffeeBeanArticleList.setAdapter(learnArticleAdapter);
    }
}
