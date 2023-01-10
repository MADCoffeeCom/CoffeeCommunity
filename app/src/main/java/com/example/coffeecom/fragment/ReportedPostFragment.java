package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.ApplicationAdapter;
import com.example.coffeecom.adapter.ReportedPostAdapter;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.model.ReportedPostModel;

import java.util.ArrayList;

public class ReportedPostFragment extends Fragment {

    RecyclerView reportedPostRecycleView;
    RecyclerView.Adapter reportedPostAdapter;
    TextView noReportedPostText;

    ArrayList<ReportedPostModel> reportedPost = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reported_post, container, false);
        reportedPostRecycleView = view.findViewById(R.id.reportedPostRecycleView);
        noReportedPostText = view.findViewById(R.id.noReportedPostText);

        reportedPost = Provider.getReportedPosts();
        recyclerViewApplication();
        return view;
    }

    private void recyclerViewApplication() {
        if (!reportedPost.isEmpty()) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            reportedPostRecycleView.setLayoutManager(linearLayoutManager);
            Log.i("ni","it");
            reportedPostAdapter = new ReportedPostAdapter(reportedPost, getActivity());
            reportedPostRecycleView.setAdapter(reportedPostAdapter);
        }else{
            noReportedPostText.setVisibility(View.VISIBLE);
        }
    }
}