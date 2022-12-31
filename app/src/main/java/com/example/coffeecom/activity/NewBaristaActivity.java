package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeecom.R;

public class NewBaristaActivity extends AppCompatActivity {

    Button submitApplicationBtn;
    TextView experiencesTextBox, yearsOfExperiencesTextBox, noInputErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_barista);

        experiencesTextBox=findViewById(R.id.experiencesTextBox);
        yearsOfExperiencesTextBox=findViewById(R.id.yearsOfExperiencesTextBox);
        submitApplicationBtn=findViewById(R.id.submitApplicationBtn);
        noInputErrorText=findViewById(R.id.noInputErrorText);
        noInputErrorText.setVisibility(View.GONE);

        //Query first from the sql to see whether the person has submitted application before
        //If yes, remove the button and setText

        submitApplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(experiencesTextBox.getText().length() != 0 && yearsOfExperiencesTextBox.length() != 0){
                    //Write sql code here to directly update database

                    Toast.makeText(getApplicationContext(), "Application submitted!", Toast.LENGTH_SHORT);
                }else{
                    noInputErrorText.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}