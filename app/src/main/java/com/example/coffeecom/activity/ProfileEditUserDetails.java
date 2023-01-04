package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.coffeecom.R;

public class ProfileEditUserDetails extends AppCompatActivity {

    private ImageView userPic;
    private EditText userEmail, userPassword;
    private ImageButton btnBack;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_user_details);

        userPic = findViewById(R.id.imgEditProfileUserPic);
        userEmail = findViewById(R.id.editTextProfileEmail);
        userPassword = findViewById(R.id.editTextProfilePassword);
        btnBack = findViewById(R.id.imgEditPostBack);
        btnUpdate = findViewById(R.id.btnProfileUpdate);

    }
}