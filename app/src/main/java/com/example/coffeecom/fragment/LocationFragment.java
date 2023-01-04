package com.example.coffeecom.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.LocateLocationActivity;


public class LocationFragment extends Fragment {

    private ImageButton locationBtn;
    private TextView currentLocationText;
    private ConstraintLayout locationFragment;

    String currentLocation = Provider.getUser().getUserLocation();

    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locationBtn = view.findViewById(R.id.locationBtn);
        currentLocationText = view.findViewById(R.id.currentLocationText);
        locationFragment = view.findViewById(R.id.locationFragment);

        //Input code below to locate current location

        currentLocationText.setText(currentLocation);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LocateLocationActivity.class));
            }
        });
    }
}