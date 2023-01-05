package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeecom.R;


public class BlankFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.activity_,container,false);




        // Inflate the layout for this fragment
//        return view;
        return inflater.inflate(R.layout.activity_main,container,false);

    }
}