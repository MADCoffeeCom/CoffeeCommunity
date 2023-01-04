package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.LearnArticleAdapter;
import com.example.coffeecom.model.ArticleModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import java.util.ArrayList;


public class LearnActivityFragment extends Fragment {

    private RecyclerView recyclerViewGeneralArticleList, recyclerViewCoffeeHistoryArticleList, recyclerViewCoffeeBeanArticleList, recyclerViewLearnArticleList;
    private RecyclerView.Adapter learnArticleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Provider.getArticles().clear();

        View view = inflater.inflate(R.layout.activity_learn,container,false);

        recyclerViewGeneralArticleList = view.findViewById(R.id.generalArticleRecyclerView);
        recyclerViewCoffeeHistoryArticleList = view.findViewById(R.id.coffeeHistoryArticleRecyclerView);
        recyclerViewCoffeeBeanArticleList = view.findViewById(R.id.coffeeBeanArticleRecycleView);
        recyclerViewLearnArticleList = view.findViewById(R.id.LearnArticleRecycleView);

        queryArticle();

        // Inflate the layout for this fragment
        return view;
    }

    private void queryArticle() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://10.167.58.200/CoffeeCommunityPHP/article.php");
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        String[] resultSplitted = result.split("split");
                        for (String str: resultSplitted) {
//                            Log.i(str, "Printing all user...");

                            String[] profileDetails = str.split(" - ");
                            String articleId = profileDetails[0];
                            String adminId = profileDetails[1];
                            String articleTitle = profileDetails[2];
                            String articleType = profileDetails[3];
                            int articleUpVote = Integer.parseInt(profileDetails[4]);
                            int articleDownVote = Integer.parseInt(profileDetails[5]);
                            String articleContent = profileDetails[6];
                            String articlePicUrl = profileDetails[7];

                            ArticleModel article = new ArticleModel(articleId, adminId, articleTitle, articleType, articleContent, articlePicUrl, articleUpVote, articleDownVote);
                            Provider.addArticles(article);
                            Log.i("Learn", "Successfully Added Article " + article.getArticleId());

                        }
                        Log.i("Learn", "Successfully Added Article " + Provider.getArticles().get(0).getArticleUpVote());
                    }
                    recyclerViewGeneralArticle(filterArticleBasedOnType("general"));
                    recyclerViewHistoryArticle(filterArticleBasedOnType("history"));
                    recyclerViewBeanArticle(filterArticleBasedOnType("beans"));
                    recyclerViewLearnArticle(filterArticleBasedOnType("learn"));
                }
            }
        });
    }

    private ArrayList<ArticleModel> filterArticleBasedOnType(String type){
        ArrayList<ArticleModel> articlesWithType = new ArrayList<>();
        for (ArticleModel article: Provider.getArticles()) {
            if (article.getArticleType().equals(type)){
                articlesWithType.add(article);
            }
        }
        return articlesWithType;
    }

    private void recyclerViewGeneralArticle(ArrayList<ArticleModel> articles) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGeneralArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articles, getContext());
        recyclerViewGeneralArticleList.setAdapter(learnArticleAdapter);
    }

    private void recyclerViewHistoryArticle(ArrayList<ArticleModel> articles) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeHistoryArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articles, getContext());
        recyclerViewCoffeeHistoryArticleList.setAdapter(learnArticleAdapter);
    }

    private void recyclerViewBeanArticle(ArrayList<ArticleModel> articles) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCoffeeBeanArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articles, getContext());
        recyclerViewCoffeeBeanArticleList.setAdapter(learnArticleAdapter);
    }

    private void recyclerViewLearnArticle(ArrayList<ArticleModel> articles) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewLearnArticleList.setLayoutManager(linearLayoutManager);

        learnArticleAdapter = new LearnArticleAdapter(articles, getContext());
        recyclerViewLearnArticleList.setAdapter(learnArticleAdapter);
    }




}