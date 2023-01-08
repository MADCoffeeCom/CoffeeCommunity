package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.adapter.PostAdapter;
import com.example.coffeecom.model.ArticleModel;
import com.example.coffeecom.model.PostModel;

import java.util.ArrayList;

public class CommunityFragment extends Fragment {

    private static final String TAG = "CommunityFragment";

    TextView tbSearch;
    ImageButton addPost;
    RecyclerView postRecyclerView;
    ArrayList<PostModel> posts = new ArrayList<>();
    PostAdapter postAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community,container,false);
        postRecyclerView = view.findViewById(R.id.postRecyclerView);
        addPost = view.findViewById(R.id.addPost);
        tbSearch = view.findViewById(R.id.tbSearch);

        posts = Provider.getPosts();

        recyclerViewPost();

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BottomNavigationActivity)getActivity()).replaceFragment(new AddPostFragment());
            }
        });

        tbSearch.setText("");
        tbSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    private void filter(String text) {
        ArrayList<PostModel> filteredlist = new ArrayList<>();

        for (PostModel item : posts) {
            if (item.getPostDesc().toLowerCase().contains(text.toLowerCase())) {
                Log.i(TAG, "filter: " + item.getPostDesc());
                filteredlist.add(item);
            }
        }
        if (!filteredlist.isEmpty()) {
            postAdapter.filterList(filteredlist);
        }
    }

    public void recyclerViewPost() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        postRecyclerView.setLayoutManager(linearLayoutManager);
        postAdapter = new PostAdapter(posts, getActivity());
        postRecyclerView.setAdapter(postAdapter);
    }
}