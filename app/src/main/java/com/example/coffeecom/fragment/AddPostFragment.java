package com.example.coffeecom.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.query.QueryPost;

public class AddPostFragment extends Fragment {

    private static final String TAG = "AddPostFragment";
    private Uri selectedImageUri = null;

    ImageButton backBtn;
    Button createPostBtn;
    ImageButton uploadPostPicBtn;
    ImageView postImage;
    TextView captionTextBox, pictureTextBox, createPostTitle;

    PostModel currentPost;
    int currentPostIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_post,container,false);
        backBtn = view.findViewById(R.id.backBtn);
        createPostBtn = view.findViewById(R.id.createPostBtn);
        captionTextBox = view.findViewById(R.id.captionTextBox);
        pictureTextBox = view.findViewById(R.id.pictureTextBox);
        createPostTitle = view.findViewById(R.id.createPostTitle);
        uploadPostPicBtn = view.findViewById(R.id.uploadPostPicBtn);
        postImage = view.findViewById(R.id.postImage);

        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());

        Bundle bundle = this.getArguments();
        if(bundle != null){
            for (int i = 0; i < Provider.getPosts().size(); i++) {
                if(Provider.getPosts().get(i).getPostId().equals(bundle.getString("postId"))){
                    currentPost = Provider.getPosts().get(i);
                    currentPostIndex = i;
                }
            }
            createPostTitle.setText("Edit Post");
            createPostBtn.setText("Update");

            captionTextBox.setText(currentPost.getPostDesc());
            pictureTextBox.setText(currentPost.getPostPic());

            postImage.setVisibility(View.VISIBLE);
            String picUrl = currentPost.getPostPic();
            int drawableResourceId = getContext().getResources().getIdentifier(picUrl, "drawable", getContext().getPackageName());
            Glide.with(getContext()).load(drawableResourceId).into(postImage);

        }

        createPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!captionTextBox.getText().equals("")){
                    String postDesc = "" + captionTextBox.getText();
                    String postPic = "" + pictureTextBox.getText();

                    if(bundle == null){
                        postPic = "post10";
                        QueryPost.addPost(postDesc, postPic);
                        Toast.makeText(getContext(), "Posted successfully!", Toast.LENGTH_SHORT).show();
                    }else{
                        QueryPost.editPost(currentPost.getPostId(), postDesc, postPic);
                        Toast.makeText(getContext(), "Post update successfully!", Toast.LENGTH_SHORT).show();
                    }
                    Log.i(TAG, "aft in Addpost: " + Provider.getPosts().size());

//                    QueryPost.queryPost();
                    getActivity().onBackPressed();
                }
            }
        });

        uploadPostPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ((BottomNavigationActivity)getActivity()).replaceFragment(new ImageUploaderFragment());
                openImageChooser();
            }
        });

        return view;
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/jpg"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, 101);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 101:
                    selectedImageUri = data.getData();
                    pictureTextBox.setText(selectedImageUri.toString());
                    postImage.setVisibility(View.VISIBLE);
                    postImage.setImageURI(selectedImageUri);
                    break;
            }
        }
    }

}