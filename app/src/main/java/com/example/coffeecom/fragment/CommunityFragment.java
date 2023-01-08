package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.PendingOrderAdapter;
import com.example.coffeecom.adapter.PostAdapter;

public class CommunityFragment extends Fragment {

    TextView TbSearch;
    ImageButton addPost;
    RecyclerView postRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community,container,false);
        postRecyclerView = view.findViewById(R.id.postRecyclerView);
        addPost = view.findViewById(R.id.addPost);
        TbSearch = view.findViewById(R.id.TbSearch);

        recyclerViewPost();

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomNavigationActivity)getActivity()).replaceFragment(new AddPostFragment());
            }
        });

        return view;
    }

    public void recyclerViewPost() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        postRecyclerView.setLayoutManager(linearLayoutManager);
        postRecyclerView.setAdapter(new PostAdapter(Provider.getPosts(), getActivity()));
    }
}