package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeecom.model.OrderModel;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.adapter.ProfilePostHistoryAdapter;
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
    OrderModel[] myOrderHistoryList, myBrewHistoryList;
    PostModel[] myPostHistoryList;

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
        myOrderHistoryList = new OrderModel[]{
                new OrderModel(strDate,"coffee1",25.00,"(2 items)"),
                new OrderModel(strDate,"beans",21.00,"(4 items)"),
                new OrderModel(strDate,"barista1",12.54,"(7 items)"),
                new OrderModel(strDate,"barista2",13.59,"(1 item)")
        };

        myBrewHistoryList = new OrderModel[]{
                new OrderModel(strDate,"barista2",2.00,"(2 items)"),
                new OrderModel(strDate,"beans",300.00,"(4 items)"),
                new OrderModel(strDate,"barista1",140.54,"(7 items)"),
                new OrderModel(strDate,"barista2",13.59,"(1 item)")
        };

        myPostHistoryList = new PostModel[]{
                new PostModel(strDate,"coffee1","Jason So Handsome",12,0),
                new PostModel(strDate,"beans","KY So Handsome",10,0),
                new PostModel(strDate,"barista1","GM So Handsome",0,15),
                new PostModel(strDate,"barista2","YY So Handsome",5,7),

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
