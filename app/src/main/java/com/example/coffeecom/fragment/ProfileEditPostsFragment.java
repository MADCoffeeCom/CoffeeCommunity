package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.R;
import com.example.coffeecom.adapter.ProfileEditPostsAdapter;
import com.example.coffeecom.model.ProfileEditPostsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ProfileEditPostsFragment extends Fragment {


    private RecyclerView imagePostRV;
    private EditText captionEdit;
    private ImageButton btnBack, btnAddPicture;
    private Button btnUpdatePost, btnDeletePost;

    private ArrayList<Integer> myImageArrayList;

    ProfileEditPostsModel[] myImageList;
    ProfileEditPostsAdapter imageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile_edit_posts,container,false);

        imagePostRV = view.findViewById(R.id.imagePostRV);
        captionEdit = view.findViewById(R.id.editTextCaption);
        btnBack = view.findViewById(R.id.imgEditPostBack);
        btnAddPicture = view.findViewById(R.id.imgEditPostButtonAddPicture);
        btnUpdatePost = view.findViewById(R.id.btnUpdatePost);
        btnDeletePost = view.findViewById(R.id.btnDeletePost);

        myImageArrayList = new ArrayList<>();
        myImageArrayList.add(R.drawable.barista1);
        myImageArrayList.add(R.drawable.coffee1);
        myImageArrayList.add(R.drawable.barista2);
        myImageArrayList.add(R.drawable.barista1);
        myImageArrayList.add(R.drawable.barista1);
        myImageArrayList.add(R.drawable.barista1);




        imageAdapter = new ProfileEditPostsAdapter(getContext(),myImageArrayList);
        imagePostRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        imagePostRV.setAdapter(imageAdapter);

        // Inflate the layout for this fragment
        return view;
    }
}