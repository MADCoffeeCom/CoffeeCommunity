package com.example.coffeecom.query;

import static com.example.coffeecom.helper.FormatDateTime.convertStringtoDate;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.coffeecom.Provider;
import com.example.coffeecom.model.OrderedCoffeeModel;
import com.example.coffeecom.model.PostModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryPost {

    private static final String TAG = "QueryPost";

    public static void queryPost(){
        Provider.getPosts().clear();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "userId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = Provider.getUser().getUserId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/post.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("No results")){
                            Log.e(TAG, "queryPost No results ");
                        }else if(result.equals("Error: Database connection")){
                            Log.e(TAG, "queryPost Database connection problem");
                        }
                        else{
                            Log.i(TAG, "Successfully Added Post " + result);
                            String[] resultSplitted = result.split("split");
                            for (String str: resultSplitted) {
                                String[] postDetails = str.split(" - ");
                                String postId = postDetails[0];

                                String posterId = postDetails[1];
                                String username = postDetails[2];
                                int upVote = Integer.parseInt(postDetails[3]);
                                int downVote = Integer.parseInt(postDetails[4]);
                                String postDesc = postDetails[5];
                                String postPicUrl = postDetails[6];
                                Date postDate = null;
                                try {
                                    postDate = convertStringtoDate(postDetails[7]);
                                } catch (ParseException e) { e.printStackTrace(); }

                                PostModel post = new PostModel(postId, upVote, downVote, posterId, username, postDesc, postPicUrl, postDate);
                                Provider.addPosts(post);
                                Log.i(TAG, "Successfully Added Post " + post.getPostId());
                            }
                        }
                        queryMyPost();
                        }
                }
            }
        });
    }

    public static void queryMyPost() {
        Provider.getUser().getPostedPost().clear();

        for (int i = 0; i < Provider.getPosts().size(); i++) {
            if(Provider.getPosts().get(i).getPosterId().equals(Provider.getUser().getUserId())){
                Provider.getUser().addPostedPost(Provider.getPosts().get(i));
                Log.i(TAG, "Successfully Added My Post " + Provider.getPosts().get(i).getPosterId());

            }
        }
    }

    public static void addPost(String postDesc, String postPic) {

        String postId = "p" + (Provider.getPosts().size() + 1);
        Log.i(TAG, "addPost: Run here once");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[6];
                field[0] = "postId";
                field[1] = "posterId";
                field[2] = "upVote";
                field[3] = "downVote";
                field[4] = "postDesc";
                field[5] = "postPicUrl";
//                field[6] = "postDate";

                String[] data = new String[6];
                data[0] = postId;
                data[1] = Provider.getUser().getUserId();
                data[2] = "0";
                data[3] = "0";
                data[4] = postDesc;
                data[5] = postPic;
//                data[6] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

                PostModel post = new PostModel(postId, 0, 0, data[3], Provider.getUser().getUserName(), data[4], data[5], new Date());
                Provider.addPosts(post);

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/addpost.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Log.i(TAG, "run: " + result);
                    }
                }
            }
        });
    }
}
