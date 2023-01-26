package com.example.coffeecom.adapter;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.model.FeedbackModel;

import java.util.ArrayList;

public class FeedbackListAdapter extends RecyclerView.Adapter<FeedbackListAdapter.ViewHolder>{
    private static final String TAG = "FeedbackListAdapter";

    ArrayList<FeedbackModel> feedbacks;
    Context activity;

    public FeedbackListAdapter(ArrayList<FeedbackModel> feedbacks, Context activity) {
        this.feedbacks = feedbacks;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView raterName, ratingDesc;
        RatingBar ratingStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            raterName = itemView.findViewById(R.id.raterName);
            ratingDesc = itemView.findViewById(R.id.ratingDesc);
            ratingStar = itemView.findViewById(R.id.ratingStar);
        }
    }

    @NonNull
    @Override
    public FeedbackListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_feedback_list, parent, false);
        return new FeedbackListAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackListAdapter.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: " + feedbacks.get(position).getUserName());
        holder.raterName.setText(toTitleCase(feedbacks.get(position).getUserName()));
        holder.ratingDesc.setText(feedbacks.get(position).getFeedbackDesc());
        holder.ratingStar.setRating(feedbacks.get(position).getAppRating());
    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }


}
