package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.R;
import com.example.coffeecom.query.QueryPost;

public class AddPostFragment extends Fragment {

    ImageButton backBtn;
    Button createPostBtn;
    TextView captionTextBox, pictureTextBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_post,container,false);
        backBtn = view.findViewById(R.id.backBtn);
        createPostBtn = view.findViewById(R.id.createPostBtn);
        captionTextBox = view.findViewById(R.id.captionTextBox);
        pictureTextBox = view.findViewById(R.id.pictureTextBox);

        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
        createPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!captionTextBox.getText().equals("")){
                    String postDesc = "" + captionTextBox.getText();
                    String postPic = "" + pictureTextBox.getText();
                    QueryPost.addPost(postDesc, postPic);
                    Toast.makeText(getContext(), "Posted successfully!", Toast.LENGTH_SHORT).show();
                    QueryPost.queryPost();
                    getActivity().onBackPressed();
                }
            }
        });

        return view;
    }
}