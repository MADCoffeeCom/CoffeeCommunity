package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeBaristaListAdapter;
import com.example.coffeecom.adapter.FeedbackListAdapter;
import com.example.coffeecom.model.CoffeeModel;

import java.util.ArrayList;

public class FeedbackListFragment extends Fragment {
    private static final String TAG = "FeedbackListFragment";
    RecyclerView feedbackRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback_list, container, false);
        feedbackRV = view.findViewById(R.id.feedbackRV);

        recyclerViewFeedback();

        return view;
    }

    private void recyclerViewFeedback() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        feedbackRV.setLayoutManager(linearLayoutManager);
        Log.i(TAG, "feedback: " + Provider.getFeedbacks().size());
        feedbackRV.setAdapter(new FeedbackListAdapter(Provider.getFeedbacks(), getActivity()));
    }
}