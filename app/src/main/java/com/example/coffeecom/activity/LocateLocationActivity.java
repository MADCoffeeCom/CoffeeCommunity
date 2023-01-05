package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeecom.R;
import com.example.coffeecom.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
        TbSearch = findViewById(R.id.TbSearch);
        relocateBtn = findViewById(R.id.relocateBtn);

        backBtn.setOnClickListener(view -> finish());

        //Insert relocate here
    }


}