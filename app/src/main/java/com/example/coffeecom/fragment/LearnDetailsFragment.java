package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.model.ArticleModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class LearnDetailsFragment extends Fragment {

    private static final String TAG = "LearnDetailsFragment";
    private ImageView learnDetailsBckBtn;
    private ImageView upvoteBtn, downvoteBtn;
    private TextView learnDetailsTitleText, learnDetailsTypeText;
    private TextView learnDetailsContentText;
    private ImageView learnDetailsImage;
    private TextView voteCountText;
    private ArticleModel currentArticle;
    private int currentArticleIndex;
    boolean isUpvoted = false;
    boolean isDownvoted = false;
    private int upvoteCount = 0;
    private int downvoteCount = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_learn_details,container,false);
        initialiseId(view);

        String articleId = Provider.getCurrentArticleId();
        for (int i = 0; i < Provider.getArticles().size(); i++) {
            if (Provider.getArticles().get(i).getArticleId().equals(articleId)){
                currentArticle = Provider.getArticles().get(i);
                currentArticleIndex = i;
            }
        }
        onBind();
        return view;
    }
    
    private void initialiseId(View view) {
        upvoteBtn = view.findViewById(R.id.learnUpvoteBtn);
        downvoteBtn = view.findViewById(R.id.learnDownvoteBtn);
        learnDetailsBckBtn = view.findViewById(R.id.learnDetailsBckBtn);
        learnDetailsTitleText = view.findViewById(R.id.learnDetailsTitleText);
        learnDetailsTypeText = view.findViewById(R.id.learnDetailsTypeText);
        learnDetailsContentText = view.findViewById(R.id.learnDetailsContentText);
        learnDetailsImage = view.findViewById(R.id.learnDetailsImage);
        voteCountText = view.findViewById(R.id.voteCountText);
    }

    private void onBind() {
        int drawableResourceId = this.getResources().getIdentifier(currentArticle.getArticlePic(), "drawable",getActivity().getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(learnDetailsImage);

        learnDetailsTitleText.setText(currentArticle.getArticleTitle());
        learnDetailsTypeText.setText(toTitleCase(currentArticle.getArticleType()));
        learnDetailsContentText.setText(currentArticle.getArticleContent());

        Log.i(TAG, String.valueOf(currentArticle.getArticleUpVote()));
        upvoteCount = currentArticle.getArticleUpVote();
        downvoteCount = currentArticle.getArticleDownVote();

        voteCountText.setText(String.valueOf(upvoteCount - downvoteCount));

        upvoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUpvoted) {
                    // Upvote the post and change the button color to green
                    upvoteBtn.setColorFilter(getResources().getColor(R.color.orange));
                    isUpvoted = true;
                    upvoteCount += 1;
                    // If the post is downvoted, remove the downvote and change the button color back to its original color
                    if (isDownvoted) {
                        downvoteBtn.setColorFilter(getResources().getColor(R.color.white));
                        isDownvoted = false;

                    }
                } else {
                    // Remove the upvote and change the button color back to its original color
                    upvoteBtn.setColorFilter(getResources().getColor(R.color.white));
                    isUpvoted = false;
                    upvoteCount -= 1;
                }
                updateUpVote(upvoteCount);
            }
        });

        //code for downvote
        downvoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isDownvoted) {
                    // Downvote the post and change the button color to red
                    downvoteBtn.setColorFilter(getResources().getColor(R.color.orange));
                    isDownvoted = true;
                    downvoteCount += 1;
                    // If the post is upvoted, remove the upvote and change the button color back to its original color
                    if (isUpvoted) {
                        upvoteBtn.setColorFilter(getResources().getColor(R.color.white));
                        isUpvoted = false;

                    }
                } else {
                    // Remove the downvote and change the button color back to its original color
                    downvoteBtn.setColorFilter(getResources().getColor(R.color.white));
                    isDownvoted = false;
                    downvoteCount -= 1;
                }
                updateDownVote(downvoteCount);
            }
        });


        learnDetailsBckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomNavigationActivity)getActivity()).onBackPressed();
            }
        });
    }



    private void updateDownVote(int count) {
        updateVote("articleDownVote", String.valueOf(count), currentArticle.getArticleId());
        Provider.getArticles().get(currentArticleIndex).setArticleDownVote(count);
        voteCountText.setText(String.valueOf(upvoteCount - downvoteCount));
    }

    private void updateUpVote(int count) {
        updateVote("articleUpVote", String.valueOf(count), currentArticle.getArticleId());
        Provider.getArticles().get(currentArticleIndex).setArticleDownVote(count);
        voteCountText.setText(String.valueOf(upvoteCount - downvoteCount));
    }

    private void updateVote(String upOrdown,  String number, String articleId) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[3];
                field[0] = "upOrDown";
                field[1] = "number";
                field[2] = "articleId";

                //Creating array for data
                String[] data = new String[3];
                data[0] = upOrdown;
                data[1] = number;
                data[2] = articleId;

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updatearticlevote.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Update success")){
                            Log.i(TAG, "Update Success Vote");
                        }else{
                            Log.i(TAG, "Update Fail");
                        }
                    }
                }
            }
        });
    }
}