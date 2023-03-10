package com.example.coffeecom.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

    String currentLocation = "";

    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        currentLocation = Provider.getUser().getUserLocation();
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

        currentLocationText.setText(Provider.getUser().getUserTaman());
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                MapsFragment mapsFragment = new MapsFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,mapsFragment).addToBackStack(null).commit();
//                startActivity(new Intent(getActivity(), LocateLocationActivity.class));
            }
        });
    }
}