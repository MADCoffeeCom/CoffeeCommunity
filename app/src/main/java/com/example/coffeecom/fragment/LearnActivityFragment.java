package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.AdminBottomNavigationActivity;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.LearnArticleAdapter;
import com.example.coffeecom.model.ArticleModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import java.util.ArrayList;


public class LearnActivityFragment extends Fragment {

    private static final String TAG = "LearnActivityFragment";

    private RecyclerView recyclerViewGeneralArticleList, recyclerViewCoffeeHistoryArticleList, recyclerViewCoffeeBeanArticleList, recyclerViewLearnArticleList;
    private LearnArticleAdapter generalArticleAdapter, historyArticleAdapter, beanArticleAdapter, learnArticleAdapter;
    private TextView articleTbSearch;
    private ImageButton addArticleBtn;

    ArrayList<ArticleModel> articles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_learn,container,false);
        initialiseID(view);
        articles = Provider.getArticles();
        Log.i(TAG, "articles: " +  articles.size());
        Log.i(TAG, "articles: " +  Provider.getArticles().size());
        buildRV(articles);

        if(Provider.getUser().getUserId().equals("UID_admin")){
            addArticleBtn.setVisibility(View.VISIBLE);
            addArticleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((AdminBottomNavigationActivity)getActivity()).replaceFragment(new CreateArticleFragment());
                }
            });
        }


        articleTbSearch.setText("");
        articleTbSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        return view;
    }

    private void filter(String text) {
        ArrayList<ArticleModel> filteredlist = new ArrayList<>();

        for (ArticleModel item : articles) {
            if (item.getArticleTitle().toLowerCase().contains(text.toLowerCase())) {
                Log.i(TAG, "filter: " + item.getArticleTitle());
                filteredlist.add(item);
            }
        }
        if (!filteredlist.isEmpty()) {
            generalArticleAdapter.filterList(filterArticleBasedOnType("general", filteredlist));
            historyArticleAdapter.filterList(filterArticleBasedOnType("history", filteredlist));
            beanArticleAdapter.filterList(filterArticleBasedOnType("beans", filteredlist));
            learnArticleAdapter.filterList(filterArticleBasedOnType("learn", filteredlist));
        }
    }

    private void initialiseID(View view){
        recyclerViewGeneralArticleList = view.findViewById(R.id.generalArticleRecyclerView);
        recyclerViewCoffeeHistoryArticleList = view.findViewById(R.id.coffeeHistoryArticleRecyclerView);
        recyclerViewCoffeeBeanArticleList = view.findViewById(R.id.coffeeBeanArticleRecycleView);
        recyclerViewLearnArticleList = view.findViewById(R.id.LearnArticleRecycleView);
        articleTbSearch = view.findViewById(R.id.TBSearch);
        addArticleBtn = view.findViewById(R.id.addArticleBtn);
    }



    private ArrayList<ArticleModel> filterArticleBasedOnType(String type, ArrayList<ArticleModel> oriArticles){
        ArrayList<ArticleModel> articlesWithType = new ArrayList<>();
        for (ArticleModel article: oriArticles) {
            if (article.getArticleType().equals(type)){
                articlesWithType.add(article);
            }
        }
        return articlesWithType;
    }

    private void buildRV(ArrayList<ArticleModel> articles) {
        recyclerViewGeneralArticleList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCoffeeHistoryArticleList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCoffeeBeanArticleList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewLearnArticleList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        generalArticleAdapter = new LearnArticleAdapter(filterArticleBasedOnType("general", articles), getActivity());
        historyArticleAdapter = new LearnArticleAdapter(filterArticleBasedOnType("history", articles), getActivity());
        beanArticleAdapter = new LearnArticleAdapter(filterArticleBasedOnType("beans", articles), getActivity());
        learnArticleAdapter = new LearnArticleAdapter(filterArticleBasedOnType("learn", articles), getActivity());

        recyclerViewGeneralArticleList.setAdapter(learnArticleAdapter);
        recyclerViewCoffeeHistoryArticleList.setAdapter(historyArticleAdapter);
        recyclerViewCoffeeBeanArticleList.setAdapter(beanArticleAdapter);
        recyclerViewLearnArticleList.setAdapter(learnArticleAdapter);
    }
}