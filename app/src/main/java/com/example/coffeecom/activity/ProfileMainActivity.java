package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.model.ProfileBrewHistoryModel;
import com.example.coffeecom.model.ProfileOrderHistoryModel;
import com.example.coffeecom.adapter.ProfilePostHistoryAdapter;
import com.example.coffeecom.model.ProfilePostHistoryModel;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.ProfileBrewHistoryAdapter;
import com.example.coffeecom.adapter.ProfileOrderHistoryAdapter;

//for current testing, may delete later
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//lack of edit profile button function
//lack onclick to each history page
//lack general button onclick

public class ProfileMainActivity extends AppCompatActivity {

    private TextView txtProfileName, txtProfileType;
    private ImageView imgBarista;
    private RecyclerView orderListRV, brewListRV, postListRV;
    private ImageButton btnEditProfile;
    private ConstraintLayout btnTerms, btnPrivacy, btnBankCard, btnHelpDesk, btnFeedback, btnLogOut;

    //data source for recycle view
    //maybe after this retrieve from SQL, currently just dummy
    ProfileOrderHistoryModel[] myOrderHistoryList;
    ProfileBrewHistoryModel[] myBrewHistoryList;
    ProfilePostHistoryModel[] myPostHistoryList;

    //Adapter
    ProfileOrderHistoryAdapter orderAdapter;
    ProfileBrewHistoryAdapter brewAdapter;
    ProfilePostHistoryAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);

        txtProfileName = findViewById(R.id.textViewProfileName);
        txtProfileType = findViewById(R.id.textViewProfileType);

        btnEditProfile = findViewById(R.id.imageButtonProfileEdit);

        btnTerms = findViewById(R.id.btnProfile1);
        btnPrivacy = findViewById(R.id.btnProfile2);
        btnBankCard = findViewById(R.id.btnProfile3);
        btnHelpDesk = findViewById(R.id.btnProfile4);
        btnFeedback = findViewById(R.id.btnProfile5);
        btnLogOut = findViewById(R.id.btnProfile6);

        imgBarista = findViewById(R.id.baristaPic);

        //code for
        orderListRV = findViewById(R.id.orderListRV);
        brewListRV = findViewById(R.id.brewListRV);
        postListRV = findViewById(R.id.postListRV);

        // to edit profile name, picture, etc
        // after got SQL, this line should be altered
//        int drawableResourceId = this.getResources().getIdentifier(Provider.getCurrentBarista().getUserPic(), "drawable", this.getPackageName());
//        Glide.with(this).load(drawableResourceId).into(imgBarista);


        //dummydata for date
        Date date = new Date(2022,12,12,12,12,12);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        //dummydata to test all recycle view
        myOrderHistoryList = new ProfileOrderHistoryModel[]{
                new ProfileOrderHistoryModel(strDate,R.drawable.barista1,"RM25.00","(2 items)"),
                new ProfileOrderHistoryModel(strDate,R.drawable.barista1,"RM23.00","(4 items)"),
                new ProfileOrderHistoryModel(strDate,R.drawable.barista1,"RM12.54","(7 items)"),
                new ProfileOrderHistoryModel(strDate,R.drawable.barista1,"RM212.24","(1 item)")
        };

        myBrewHistoryList = new ProfileBrewHistoryModel[]{
                new ProfileBrewHistoryModel(strDate,R.drawable.barista1,"RM25.00","(2 items)"),
                new ProfileBrewHistoryModel(strDate,R.drawable.barista1,"RM23.00","(4 items)"),
                new ProfileBrewHistoryModel(strDate,R.drawable.barista1,"RM12.54","(7 items)"),
                new ProfileBrewHistoryModel(strDate,R.drawable.barista1,"RM212.24","(1 item)")
        };

        myPostHistoryList = new ProfilePostHistoryModel[]{
                new ProfilePostHistoryModel(strDate,R.drawable.barista1,"Jason So Handsome",12,0,2),
                new ProfilePostHistoryModel(strDate,R.drawable.barista1,"KY So Handsome",10,0,2),
                new ProfilePostHistoryModel(strDate,R.drawable.barista1,"GM So Handsome",0,15,2),
                new ProfilePostHistoryModel(strDate,R.drawable.barista1,"YY So Handsome",5,7,2),

        };

        //Adapter
        orderAdapter = new ProfileOrderHistoryAdapter(myOrderHistoryList);
        orderListRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        brewAdapter = new ProfileBrewHistoryAdapter(myBrewHistoryList);
        brewListRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        postAdapter = new ProfilePostHistoryAdapter(myPostHistoryList);
        postListRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        orderListRV.setAdapter(orderAdapter);
        brewListRV.setAdapter(brewAdapter);
        postListRV.setAdapter(postAdapter);

    }
}
