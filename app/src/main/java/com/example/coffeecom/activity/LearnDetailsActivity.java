package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.coffeecom.R;
import com.example.coffeecom.model.ArticleModel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class LearnDetailsActivity extends AppCompatActivity {
    private ImageView learnDetailsBckBtn;
    private ImageView upvoteBtn, downvoteBtn, shareBtn;
    private TextView learnDetailsTitleText, learnDetailsTypeText;
    private TextView learnDetailsContentText;
    private ImageView learnDetailsImage;
    private TextView voteCountText;
    private ArticleModel object;
    boolean isUpvoted = false;
    boolean isDownvoted = false;
    private int upvoteCount = 0;
    private int downvoteCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_details);

        initializeView();
        getBundle();

    }

    private void getBundle() {


        //from youtube video but cannot work and need change to get data from database right?

//        object = (ArticleModel) getIntent().getSerializableExtra("object");
//
//        int drawableResourceId = this.getResources().getIdentifier(object.getArticlePic(), "drawable",this.getPackageName());
//        Glide.with(this)
//                .load(drawableResourceId)
//                .into(learnDetailsImage);
//
//        learnDetailsTitleText.setText(object.getArticleTitle());
//        learnDetailsTypeText.setText(object.getArticleType());
//        learnDetailsContentText.setText(object.getArticleContent());
//
//        voteCountText.setText(object.getArticleUpVote() - object.getArticleDownVote());

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
                        upvoteCount -= 1;
                    }
                } else {
                    // Remove the upvote and change the button color back to its original color
                    upvoteBtn.setColorFilter(getResources().getColor(R.color.white));
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
                    downvoteBtn.setColorFilter(getResources().getColor(R.color.orange));
                    isDownvoted = true;
                    downvoteCount += 1;

                    // If the post is upvoted, remove the upvote and change the button color back to its original color
                    if (isUpvoted) {
                        upvoteBtn.setColorFilter(getResources().getColor(R.color.white));
                        isUpvoted = false;
                        downvoteCount -= 1;
                    }
                } else {
                    // Remove the downvote and change the button color back to its original color
                    downvoteBtn.setColorFilter(getResources().getColor(R.color.white));
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

        //back button der code im not sure correct or not
        learnDetailsBckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(LearnDetailsActivity.this, LearnActivity.class);

                // Start the new activity
                startActivity(intent);
            }
        });

    }

    private void initializeView() {
        upvoteBtn = findViewById(R.id.learnUpvoteBtn);
        downvoteBtn = findViewById(R.id.learnDownvoteBtn);
        shareBtn = findViewById(R.id.learnShareBtn);
        learnDetailsBckBtn = findViewById(R.id.learnDetailsBckBtn);
        learnDetailsTitleText = findViewById(R.id.learnDetailsTitleText);
        learnDetailsTypeText = findViewById(R.id.learnDetailsTypeText);
        learnDetailsContentText = findViewById(R.id.learnDetailsContentText);
        learnDetailsImage = findViewById(R.id.learnDetailsImage);
    }
}