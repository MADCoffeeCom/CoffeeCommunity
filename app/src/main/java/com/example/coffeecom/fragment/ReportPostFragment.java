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

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.query.QueryPost;


public class ReportPostFragment extends Fragment {

    ImageButton backBtn;
    TextView postIdText, postDescText;
    TextView reasonTextBox;
    Button reportBtn;

    PostModel currentPost;
    int currentPostIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_post, container, false);
        backBtn = view.findViewById(R.id.backBtn);
        postIdText = view.findViewById(R.id.postIdText);
        postDescText = view.findViewById(R.id.postDescText);
        reasonTextBox = view.findViewById(R.id.reasonTextBox);
        reportBtn = view.findViewById(R.id.reportBtn);

        Bundle bundle = this.getArguments();
        for (int i = 0; i < Provider.getPosts().size(); i++) {
            if(Provider.getPosts().get(i).getPostId().equals(bundle.getString("postId"))){
                currentPost = Provider.getPosts().get(i);
                currentPostIndex = i;
            }
        }
        postIdText.setText(bundle.getString("postId"));
        postDescText.setText(currentPost.getPostDesc());

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!reasonTextBox.getText().equals("")){
                    QueryPost.reportPost(currentPost.getPostId(), "" + reasonTextBox.getText());
                    Toast.makeText(getContext(), "Report Successfully!", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                }
            }
        });
        return view;
    }
}