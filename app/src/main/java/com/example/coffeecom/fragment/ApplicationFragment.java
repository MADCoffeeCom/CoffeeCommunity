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
import com.example.coffeecom.adapter.RatingBarAdapter;
import com.example.coffeecom.model.ApplicationModel;
import com.example.coffeecom.query.QueryApplication;
import com.example.coffeecom.query.QueryArticle;
import com.example.coffeecom.query.QueryFeedback;
import com.example.coffeecom.query.QueryPost;

import java.util.ArrayList;

public class ApplicationFragment extends Fragment {

    RecyclerView applicationCardRecycleView;
    ApplicationAdapter adapter;
    TextView noApplicationText;

    ArrayList<ApplicationModel> application = new ArrayList<ApplicationModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application,container,false);

        applicationCardRecycleView = view.findViewById(R.id.applicationCardRecycleView);
        noApplicationText = view.findViewById(R.id.noApplicationText);

        QueryApplication.queryApplication();
        QueryArticle.queryArticle();
        QueryPost.queryReportedPost();
        QueryFeedback.getFeedback();
        application = Provider.getApplication();
        recyclerViewApplication();

        return view;
    }

    private void recyclerViewApplication() {
        if (!application.isEmpty()) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            applicationCardRecycleView.setLayoutManager(linearLayoutManager);
            Log.i("ni","it");
            adapter = new ApplicationAdapter(application, getActivity());
            applicationCardRecycleView.setAdapter(adapter);
        }else{
            noApplicationText.setVisibility(View.VISIBLE);
        }
    }
}