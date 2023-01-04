package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.ArticleModel;


public class LearnDetailsFragment extends Fragment {

    private static final String TAG = "LearnDetailsFragment";
    private ImageView learnDetailsBckBtn;
    private ImageView upvoteBtn, downvoteBtn, shareBtn;
    private TextView learnDetailsTitleText, learnDetailsTypeText;
    private TextView learnDetailsContentText;
    private ImageView learnDetailsImage;
    private TextView voteCountText;
    private ArticleModel currentArticle;
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
            }
        }
        onBind();
        return view;
    }
    
    private void initialiseId(View view) {
        upvoteBtn = view.findViewById(R.id.learnUpvoteBtn);
        downvoteBtn = view.findViewById(R.id.learnDownvoteBtn);
        shareBtn = view.findViewById(R.id.learnShareBtn);
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
                    upvoteBtn.setColorFilter(R.color.orange);
                    isUpvoted = true;
                    upvoteCount += 1;

                    // If the post is downvoted, remove the downvote and change the button color back to its original color
                    if (isDownvoted) {
                        downvoteBtn.setColorFilter(R.color.white);
                        isDownvoted = false;
                        upvoteCount -= 1;
                    }
                } else {
                    // Remove the upvote and change the button color back to its original color
                    upvoteBtn.setColorFilter(R.color.white);
                    isUpvoted = false;
                    upvoteCount -= 1;
                }

                // Update the count in the database
                updateUpvoteCountInDatabase(upvoteCount);
            }

            private void updateUpvoteCountInDatabase(int upvoteCount) {
                // code for update query to database

            }
        });

        //code for downvote
        downvoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isDownvoted) {
                    // Downvote the post and change the button color to red
                    downvoteBtn.setColorFilter(R.color.orange);
                    isDownvoted = true;
                    downvoteCount += 1;

                    // If the post is upvoted, remove the upvote and change the button color back to its original color
                    if (isUpvoted) {
                        upvoteBtn.setColorFilter(R.color.white);
                        isUpvoted = false;
                        downvoteCount -= 1;
                    }
                } else {
                    // Remove the downvote and change the button color back to its original color
                    downvoteBtn.setColorFilter(R.color.white);
                    isDownvoted = false;
                    downvoteCount -= 1;
                }

                // Update the count in the database
                updateDownvoteCountInDatabase(downvoteCount);
            }

            private void updateDownvoteCountInDatabase(int downvoteCount) {
                // code for update query to database

            }
        });

        //code for share
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code for share article function
            }
        });

        learnDetailsBckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to go back to the previous activity
                Intent intent = new Intent(view.getContext(), LearnActivityFragment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();

            }
        });
    }
}