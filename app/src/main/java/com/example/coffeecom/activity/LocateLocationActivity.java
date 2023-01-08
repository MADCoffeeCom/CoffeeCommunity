package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.R;

public class LocateLocationActivity extends AppCompatActivity {

//    private GoogleMap mMap;
//    private ActivityMapsBinding binding;
//    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code = 101;
    private ImageButton backBtn;
    private TextView TbSearch;
    private Button relocateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_location);


        backBtn = findViewById(R.id.backBtn);
        TbSearch = findViewById(R.id.articleTbSearch);
        relocateBtn = findViewById(R.id.relocateBtn);

        backBtn.setOnClickListener(view -> finish());

        //Insert relocate here
    }


}