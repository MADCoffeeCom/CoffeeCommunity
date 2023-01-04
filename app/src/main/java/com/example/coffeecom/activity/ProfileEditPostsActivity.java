package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.coffeecom.R;
import com.example.coffeecom.adapter.ProfileEditPostsAdapter;
import com.example.coffeecom.model.ProfileEditPostsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProfileEditPostsActivity extends AppCompatActivity {

    private RecyclerView imagePostRV;
    private EditText captionEdit;
    private ImageButton btnBack, btnAddPicture;
    private FloatingActionButton btnDeletePicture;
    private Button btnUpdatePost, btnDeletePost;

    private ArrayList<Integer> myImageArrayList;

    ProfileEditPostsModel[] myImageList;
    ProfileEditPostsAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_posts);

        imagePostRV = findViewById(R.id.imagePostRV);
        captionEdit = findViewById(R.id.editTextCaption);
        btnBack = findViewById(R.id.imgEditPostBack);
        btnAddPicture = findViewById(R.id.imgEditPostButtonAddPicture);
        btnDeletePicture = findViewById(R.id.btnDeleteImage);
        btnUpdatePost = findViewById(R.id.btnUpdatePost);
        btnDeletePost = findViewById(R.id.btnDeletePost);

        myImageArrayList = new ArrayList<>();
        myImageArrayList.add(R.drawable.barista1);
        myImageArrayList.add(R.drawable.coffee1);
        myImageArrayList.add(R.drawable.barista2);
        myImageArrayList.add(R.drawable.barista1);
        myImageArrayList.add(R.drawable.barista1);
        myImageArrayList.add(R.drawable.barista1);




        imageAdapter = new ProfileEditPostsAdapter(this,myImageArrayList);
        imagePostRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        imagePostRV.setAdapter(imageAdapter);

    }
}