package com.example.coffeecom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.coffeecom.R;
import com.example.coffeecom.fragment.HomeActivityFragment;
import com.example.coffeecom.fragment.LearnActivityFragment;
import com.example.coffeecom.fragment.NewBaristaFragment;
import com.example.coffeecom.fragment.ProfileMainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/*
This class is to let each activity and xml to copy and paste it to the corresponding activity
since we are using activity so we need to do like this
rmb to change the return true at each corresponding case
 */
public class BottomNavigationActivity extends AppCompatActivity {

    BottomNavigationView btmNavBar;
//    HomeActivity homeActivity = new HomeActivity();
//    LearnActivity learnActivity = new LearnActivity();
//    ProfileMainActivity profileMainActivity = new ProfileMainActivity();
    ProfileMainFragment profileMain = new ProfileMainFragment();
    LearnActivityFragment learnMain = new LearnActivityFragment();
    HomeActivityFragment homeMain = new HomeActivityFragment();
    NewBaristaFragment baristaMain = new NewBaristaFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        btmNavBar = findViewById(R.id.bottomNavigationView);
        btmNavBar.setSelectedItemId(R.id.nvBuyCoffeeHome);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage, homeMain).commit();


        btmNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//        btmNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nvBuyCoffeeHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,homeMain).commit();
                        item.setChecked(true);
                        break;

                    case R.id.nvBaristaHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,baristaMain).commit();
                        item.setChecked(true);
                        break;

                    case R.id.nvLearnHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,learnMain).commit();
                        item.setChecked(true);
                        break;

                    case R.id.nvProfileHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,profileMain).commit();
                        item.setChecked(true);
                        break;

                }

                return false;
            }


        });



    }
}