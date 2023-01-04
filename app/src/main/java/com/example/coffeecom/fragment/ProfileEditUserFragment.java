package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.coffeecom.R;


public class ProfileEditUserFragment extends Fragment {

    private ImageView userPic;
    private EditText userEmail, userPassword;
    private ImageButton btnBack;
    private Button btnUpdate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile_edit_user_details,container,false);

        userPic = view.findViewById(R.id.imgEditProfileUserPic);
        userEmail = view.findViewById(R.id.editTextProfileEmail);
        userPassword = view.findViewById(R.id.editTextProfilePassword);
        btnBack = view.findViewById(R.id.imgEditPostBack);
        btnUpdate = view.findViewById(R.id.btnProfileUpdate);


        // Inflate the layout for this fragment
        return view;
    }
}