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


    }


}
