package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.FormatDateTime.convertDatetoStringDate;
import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.AdminBottomNavigationActivity;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.query.QueryArticle;
import com.example.coffeecom.query.QueryPost;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class PostDetailsFragment extends Fragment {

    private static final String TAG = "PostDetailsFragment";

    PostModel currentPost;
    ImageView postPic;
    TextView posterText, postDateText, postDescText, postVoteCountText;
    ImageButton backBtn, postUpVoteBtn, postDownVoteBtn, reportBtn;
    ImageButton editPostBtn, deletePostBtn;

    int currentPostIndex = 0;
    boolean isUpvoted = false;
    boolean isDownvoted = false;
    private int upvoteCount = 0;
    private int downvoteCount = 0;
    boolean isUserPost = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_details,container,false);
        initialiseId(view);

        Bundle bundle = this.getArguments();
        for (int i = 0; i < Provider.getPosts().size(); i++) {
            if(Provider.getPosts().get(i).getPostId().equals(bundle.getString("postId"))){
                currentPost = Provider.getPosts().get(i);
                currentPostIndex = i;

            }
        }

        for (PostModel article: Provider.getUser().getPostedPost()) {
            if(article.getPostId().equals(currentPost.getPostId())){
                isUserPost = true;
                Log.i(TAG, "isUserPost: " + isUserPost);
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
        postVoteCountText = view.findViewById(R.id.postVoteCountText);
        backBtn = view.findViewById(R.id.backBtn);
        postUpVoteBtn = view.findViewById(R.id.postUpVoteBtn);
        postDownVoteBtn = view.findViewById(R.id.postDownVoteBtn);
        reportBtn = view.findViewById(R.id.reportBtn);
        deletePostBtn = view.findViewById(R.id.deletePostBtn);
        editPostBtn = view.findViewById(R.id.editPostBtn);
    }

    private void onBind(View view) {
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        posterText.setText(toTitleCase(currentPost.getSenderName()));
        postDateText.setText(convertDatetoStringDate(currentPost.getPostDateTime()));
        postDescText.setText(currentPost.getPostDesc());

        upvoteCount = currentPost.getUpVote();
        downvoteCount = currentPost.getDownVote();
        postVoteCountText.setText(String.valueOf(upvoteCount - downvoteCount));

        String picUrl = currentPost.getPostPic();
        int drawableResourceId = view.getContext().getResources().getIdentifier(picUrl, "drawable", view.getContext().getPackageName());
        Glide.with(view.getContext()).load(drawableResourceId).into(postPic);

        editPostBtn.setVisibility(View.GONE);
        deletePostBtn.setVisibility(View.GONE);

        if(isUserPost){
            reportBtn.setVisibility(View.GONE);
            editPostBtn.setVisibility(View.VISIBLE);
            deletePostBtn.setVisibility(View.VISIBLE);

            deletePostBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setCancelable(true);
                    builder.setTitle("Delete Post");
                    builder.setMessage("Are you sure want to delete " + currentPost.getPostId() + "?");
                    builder.setPositiveButton("Delete", (dialog, which) -> {
                        QueryPost.deletePost(currentPost.getPostId());
                        Toast.makeText(getContext(), "Delete Post success!", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    });
                    builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
                    builder.show();
                }
            });

            editPostBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("postId", currentPost.getPostId().toString());
                    ((BottomNavigationActivity)getActivity()).replaceFragmentWithData(new AddPostFragment(), bundle);
                }
            });
        }

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("postId", currentPost.getPostId());
                ((BottomNavigationActivity)getActivity()).replaceFragmentWithData(new ReportPostFragment(),bundle);
            }
        });

        postUpVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUpvoted) {
                    // Upvote the post and change the button color to green
                    postUpVoteBtn.setColorFilter(getResources().getColor(R.color.orange));
                    isUpvoted = true;
                    upvoteCount += 1;
                    // If the post is downvoted, remove the downvote and change the button color back to its original color
                    if (isDownvoted) {
                        postDownVoteBtn.setColorFilter(getResources().getColor(R.color.white));
                        isDownvoted = false;
                        downvoteCount -=1;
                    }
                } else {
                    // Remove the upvote and change the button color back to its original color
                    postUpVoteBtn.setColorFilter(getResources().getColor(R.color.white));
                    isUpvoted = false;
                    upvoteCount -= 1;
                }
                updateUpVote(upvoteCount);
            }
        });

        //code for downvote
        postDownVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isDownvoted) {
                    // Downvote the post and change the button color to red
                    postDownVoteBtn.setColorFilter(getResources().getColor(R.color.orange));
                    isDownvoted = true;
                    downvoteCount += 1;
                    // If the post is upvoted, remove the upvote and change the button color back to its original color
                    if (isUpvoted) {
                        postUpVoteBtn.setColorFilter(getResources().getColor(R.color.white));
                        isUpvoted = false;
                        upvoteCount -=1;
                    }
                } else {
                    // Remove the downvote and change the button color back to its original color
                    postDownVoteBtn.setColorFilter(getResources().getColor(R.color.white));
                    isDownvoted = false;
                    downvoteCount -= 1;
                }
                updateDownVote(downvoteCount);
            }
        });
    }

    private void updateDownVote(int count) {
        updateVote("downVote", String.valueOf(count), currentPost.getPostId());
        Provider.getPosts().get(currentPostIndex).setDownVote(count);
        postVoteCountText.setText(String.valueOf(upvoteCount - downvoteCount));
    }

    private void updateUpVote(int count) {
        updateVote("upVote", String.valueOf(count), currentPost.getPostId());
        Provider.getPosts().get(currentPostIndex).setUpVote(count);
        postVoteCountText.setText(String.valueOf(upvoteCount - downvoteCount));
    }

    private void updateVote(String upOrdown,  String number, String articleId) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[3];
                field[0] = "upOrDown";
                field[1] = "number";
                field[2] = "postId";

                //Creating array for data
                String[] data = new String[3];
                data[0] = upOrdown;
                data[1] = number;
                data[2] = currentPost.getPostId();

                PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updatepostvote.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Update success")){
                            Log.i(TAG, "Update Success Vote");
                        }else{
                            Log.i(TAG, "Update Fail");
                        }
                    }
                }
            }
        });
    }
}