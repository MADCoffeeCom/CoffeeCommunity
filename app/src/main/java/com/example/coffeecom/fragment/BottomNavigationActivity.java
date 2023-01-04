package com.example.coffeecom.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.coffeecom.R;
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
//                        btmNavBar.setSelectedItemId(R.id.nvBuyCoffeeHome);
                    break;
                    case R.id.nvBaristaHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,baristaMain).commit();
//                        btmNavBar.setSelectedItemId(R.id.nvBuyCoffeeHome);

                    case R.id.nvLearnHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,learnMain).commit();
//                        btmNavBar.setSelectedItemId(R.id.nvLearnHome);
                    break;
                    case R.id.nvProfileHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,profileMain).commit();
//                        btmNavBar.setSelectedItemId(R.id.nvProfileHome);
                    break;

            }
                return false;
            }
        });

    }
}