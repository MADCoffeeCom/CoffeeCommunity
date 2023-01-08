package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.HelpdeskAdapter;
import com.example.coffeecom.adapter.ProfileOrderHistoryAdapter;
import com.example.coffeecom.model.HelpdeskModel;

public class HelpdeskFragment extends Fragment {

    ImageButton backBtn;
    private HelpdeskModel helpdesk = new HelpdeskModel();

    HelpdeskAdapter helpAdapter;
    RecyclerView helpdeskQuestionRecycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_helpdesk,container,false);
        helpdeskQuestionRecycleView = view.findViewById(R.id.helpdeskQuestionRecycleView);
        backBtn = view.findViewById(R.id.backBtn);

        backBtn.setOnClickListener(view1 -> ((BottomNavigationActivity)getActivity()).onBackPressed());

        orderRecycleView();

        return view;
    }

    public void orderRecycleView() {
        helpAdapter = new HelpdeskAdapter(helpdesk, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        helpdeskQuestionRecycleView.setLayoutManager(linearLayoutManager);
        helpdeskQuestionRecycleView.setAdapter(helpAdapter);
    }


}