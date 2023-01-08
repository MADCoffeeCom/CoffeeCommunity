package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.query.QueryFeedback;

public class FeedbackFragment extends Fragment {

    ImageButton backBtn;
    RatingBar feedbackRatingBar;
    TextView feedbackTextBox;
    Button feedbackSubmitBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback,container,false);
        feedbackRatingBar = view.findViewById(R.id.feedbackRatingBar);
        feedbackTextBox = view.findViewById(R.id.feedbackTextBox);
        feedbackSubmitBtn = view.findViewById(R.id.feedbackSubmitBtn);
        backBtn = view.findViewById(R.id.backBtn);

        backBtn.setOnClickListener(view1 -> ((BottomNavigationActivity)getActivity()).onBackPressed());

        feedbackSubmitBtn.setOnClickListener(view12 -> {
            if(!feedbackTextBox.getText().toString().matches("")){
                QueryFeedback feedback = new QueryFeedback();
                feedback.addFeedback(feedbackRatingBar.getNumStars(), "" + feedbackTextBox.getText());
                Toast.makeText((BottomNavigationActivity)getContext(), "Submitted!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText((BottomNavigationActivity)getContext(), "Please write your feedback!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}