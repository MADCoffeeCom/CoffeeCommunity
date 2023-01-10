package com.example.coffeecom.query;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.ArticleModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QueryArticle {

    private static final String TAG = "QueryArticle";

    public static void queryArticle() {
        Provider.getArticles().clear();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                FetchData fetchData = new FetchData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/article.php");
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
                    }
                }
                Log.i(TAG, "articles: " +  Provider.getArticles().size());
            }
        });
    }

    public static void addArticle(String title, String type, String content, String pic){
        String articleId = "ar" + (Provider.getArticles().size()+1);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[6];
                field[0] = "articleId";
                field[1] = "articleTitle";
                field[2] = "articleType";
                field[3] = "articleContent";
                field[4] = "articlePic";
                field[5] = "adminId";

                //Creating array for data
                String[] data = new String[6];
                data[0] = articleId;
                data[1] = title;
                data[2] = type;
                data[3] = content;
                data[4] = pic;
                data[5] = "a0001";

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addarticle.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "addBarista: " + result);
                    }
                }
            }
        });
    }
}
