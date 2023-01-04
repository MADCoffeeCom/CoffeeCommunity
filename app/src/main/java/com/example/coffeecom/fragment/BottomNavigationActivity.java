package com.example.coffeecom.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.coffeecom.R;
import com.example.coffeecom.fragment.ProfileMainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        btmNavBar = findViewById(R.id.bottomNavigationView);
        btmNavBar.setSelectedItemId(R.id.nvBuyCoffeeHome);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage, homeMain).commit();


        btmNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
//                    case R.id.nvBuyCoffeeHome:
//                        //if the current page is HomeAcivity then dont need to start Activity
//                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//
//                    //please add the barista home class whenever built later
//                    case R.id.nvBaristaHome:
//                        startActivity(new Intent(getApplicationContext(),NewBaristaActivity.class ));
//                        overridePendingTransition(0,0);
//                        return true;
//
//                    case R.id.nvLearnHome:
//                        startActivity(new Intent(getApplicationContext(),LearnActivityFragment.class));
//                        overridePendingTransition(0,0);
//                        return true;
//
//                    //please add the community home class whenever done later
////                    case R.id.nvCommunityHome:
////                        startActivity(new Intent(getApplicationContext(),.class));
////                        overridePendingTransition(0,0);
////                        return true;
//
//                    case R.id.nvProfileHome:
//                        startActivity(new Intent(getApplicationContext(),ProfileMainFragment.class));
//                        overridePendingTransition(0,0);
//                        return true;

//                    case R.id.nvBuyCoffeeHome:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,testPage3).commit();
                    case R.id.nvBuyCoffeeHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,homeMain).commit();
//                        btmNavBar.setSelectedItemId(R.id.nvBuyCoffeeHome);
                        break;
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