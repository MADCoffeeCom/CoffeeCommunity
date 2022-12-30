package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.CoffeeBaristaListAdapter;

public class BaristaListActivity extends AppCompatActivity {

    private TextView baristaListName, baristaListLocation, baristaListDesc;
    private ImageView baristaListChatBtn, baristaListPic;
    private RecyclerView baristaListRecyclerView;
    private RecyclerView.Adapter coffeeListInBaristaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barista_list);

        baristaListName = findViewById(R.id.baristaListName);
        baristaListLocation = findViewById(R.id.baristaListLocation);
        baristaListDesc = findViewById(R.id.baristaListDesc);
        baristaListChatBtn = findViewById(R.id.baristaListChatBtn);
        baristaListPic = findViewById(R.id.baristaListPic);

        baristaListName.setText(Provider.getCurrentBarista().getUserName());
        baristaListLocation.setText(Provider.getCurrentBarista().getUserTaman());
        baristaListDesc.setText(Provider.getCurrentBarista().getBaristaDesc());

        //also need insert sql code here
        int drawableResourceId = this.getResources().getIdentifier(Provider.getCurrentBarista().getUserPic(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(baristaListPic);

        baristaListChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Link to chat function with barista
            }
        });

        recyclerViewCoffeeType();
    }

    private void recyclerViewCoffeeType() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        baristaListRecyclerView = findViewById(R.id.coffeeListInBaristaRecyclerView);
        baristaListRecyclerView.setLayoutManager(linearLayoutManager);

        coffeeListInBaristaAdapter = new CoffeeBaristaListAdapter(Provider.getCurrentBarista(), 'b');
        baristaListRecyclerView.setAdapter(coffeeListInBaristaAdapter);
    }
}