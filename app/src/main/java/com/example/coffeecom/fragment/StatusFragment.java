package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;


public class StatusFragment extends Fragment {

    TextView statusText, statusHeading1Text, statusHeading2Text;
    Button statusBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_status,container,false);

        statusText = view.findViewById(R.id.statusText);
        statusHeading1Text = view.findViewById(R.id.statusHeading1Text);
        statusHeading2Text = view.findViewById(R.id.statusHeading2Text);
        statusBtn = view.findViewById(R.id.statusBtn);

        statusText.setText(Provider.getStatusTitle());
        statusHeading1Text.setText(Provider.getStatusHeading1());
        statusHeading2Text.setText(Provider.getStatusHeading2());
        statusBtn.setText(Provider.getStatusBtnText());
        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                //what fragment should i redirect this to ?
//                startActivity(new Intent(getContext(), Provider.getRedirectedCls().getClass()));

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                WalletFragment walletFragment = new WalletFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,walletFragment).addToBackStack(null).commit();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}