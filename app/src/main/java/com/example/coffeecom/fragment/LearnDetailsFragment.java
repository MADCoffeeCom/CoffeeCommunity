package com.example.coffeecom.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.coffeecom.R;
import com.example.coffeecom.activity.LearnActivity;
import com.example.coffeecom.activity.LearnDetailsActivity;
import com.example.coffeecom.model.ArticleModel;


public class LearnDetailsFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_learn_details,container,false);

        upvoteBtn = view.findViewById(R.id.learnUpvoteBtn);
        downvoteBtn = view.findViewById(R.id.learnDownvoteBtn);
        shareBtn = view.findViewById(R.id.learnShareBtn);
        learnDetailsBckBtn = view.findViewById(R.id.learnDetailsBckBtn);
        learnDetailsTitleText = view.findViewById(R.id.learnDetailsTitleText);
        learnDetailsTypeText = view.findViewById(R.id.learnDetailsTypeText);
        learnDetailsContentText = view.findViewById(R.id.learnDetailsContentText);
        learnDetailsImage = view.findViewById(R.id.learnDetailsImage);

        getBundle();

        // Inflate the layout for this fragment
        return view;
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