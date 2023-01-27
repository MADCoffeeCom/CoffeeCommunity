package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.FormatDateTime.convertDatetoStringDate;
import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.model.ReportedPostModel;
import com.example.coffeecom.query.QueryPost;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.SimpleDateFormat;

public class ReportedPostDetailsFragment extends Fragment {

    private static final String TAG = "PostDetailsFragment";

    ReportedPostModel currentPost;
    ImageView postPic;
    TextView posterText, postDateText, postDescText, reasonText;
    ImageButton backBtn;
    Button keepPostBtn, deletePostBtn;

    int currentPostIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reported_post_details,container,false);
        initialiseId(view);

        Bundle bundle = this.getArguments();
        for (int i = 0; i < Provider.getReportedPosts().size(); i++) {
            if(Provider.getReportedPosts().get(i).getPostId().equals(bundle.getString("postId"))){
                currentPost = Provider.getReportedPosts().get(i);
                currentPostIndex = i;
            }
        }

        onBind(view);

        return view;
    }

    public void initialiseId(View view) {
        postPic = view.findViewById(R.id.postPic);
        posterText = view.findViewById(R.id.posterText);
        postDateText = view.findViewById(R.id.postDateText);
        postDescText = view.findViewById(R.id.postDescText);
        reasonText = view.findViewById(R.id.reasonText);
        backBtn = view.findViewById(R.id.backBtn);
        keepPostBtn = view.findViewById(R.id.keepPostBtn);
        deletePostBtn = view.findViewById(R.id.deletePostBtn);
    }

    private void onBind(View view) {
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        posterText.setText(toTitleCase(currentPost.getSenderName()));
        postDateText.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((currentPost.getPostDateTime())));
        postDescText.setText(currentPost.getPostDesc());

        String picUrl = currentPost.getPostPic();
        int drawableResourceId = view.getContext().getResources().getIdentifier(picUrl, "drawable", view.getContext().getPackageName());
        Glide.with(view.getContext()).load(drawableResourceId).into(postPic);

        reasonText.setText(currentPost.getReason());

        keepPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryPost.keepReportedPost(currentPost.getPostId());
                Toast.makeText(getContext(), "Post keeped!", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });

        deletePostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryPost.deleteReportedPost(currentPost.getPostId());
                QueryPost.deletePost(currentPost.getPostId());
                Toast.makeText(getContext(), "Post deleted!", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });


    }
}