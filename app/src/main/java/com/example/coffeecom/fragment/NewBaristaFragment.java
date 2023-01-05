package com.example.coffeecom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;


public class NewBaristaFragment extends Fragment {

    Button submitApplicationBtn;
    TextView experiencesTextBox, yearsOfExperiencesTextBox, noInputErrorText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_new_barista,container,false);

        experiencesTextBox=view.findViewById(R.id.experiencesTextBox);
        yearsOfExperiencesTextBox=view.findViewById(R.id.yearsOfExperiencesTextBox);
        submitApplicationBtn=view.findViewById(R.id.submitApplicationBtn);
        noInputErrorText=view.findViewById(R.id.noInputErrorText);
        noInputErrorText.setVisibility(View.GONE);

        //Query first from the sql to see whether the person has submitted application before
        //If yes, remove the button and setText

        submitApplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(experiencesTextBox.getText().length() != 0 && yearsOfExperiencesTextBox.length() != 0){
                    //Write sql code here to directly update database
                    ((BottomNavigationActivity)getActivity()).replaceFragment(new BaristaFragment());

                    Toast.makeText(getContext(), "Application submitted!", Toast.LENGTH_SHORT);
                }else{
                    noInputErrorText.setVisibility(View.VISIBLE);
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}