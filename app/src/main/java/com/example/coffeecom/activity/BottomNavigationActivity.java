package com.example.coffeecom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.coffeecom.R;
import com.example.coffeecom.fragment.HomeActivityFragment;
import com.example.coffeecom.fragment.LearnActivityFragment;
import com.example.coffeecom.fragment.NewBaristaFragment;
import com.example.coffeecom.fragment.ProfileMainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

/*
This class is to let each activity and xml to copy and paste it to the corresponding activity
since we are using activity so we need to do like this
rmb to change the return true at each corresponding case
 */
public class BottomNavigationActivity extends AppCompatActivity {

    private final String TAG = "Navigation";

    BottomNavigationView btmNavBar;
    int container = R.id.containerMainPage;
    ArrayList<Integer> menuItem = new ArrayList<>();

    ProfileMainFragment profileMain = new ProfileMainFragment();
    LearnActivityFragment learnMain = new LearnActivityFragment();
    HomeActivityFragment homeMain = new HomeActivityFragment();
    NewBaristaFragment baristaMain = new NewBaristaFragment();

    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        btmNavBar = findViewById(R.id.bottomNavigationView);
        btmNavBar.setSelectedItemId(R.id.nvBuyCoffeeHome);

        menuItem.add(R.id.nvBuyCoffeeHome);
        menuItem.add(R.id.nvBaristaHome);
        menuItem.add(R.id.nvLearnHome);
        menuItem.add(R.id.nvProfileHome);

        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(container, homeMain).commit();

        btmNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nvBuyCoffeeHome:
                        replaceFragment(homeMain, item);
                        break;
                    case R.id.nvBaristaHome:
                        replaceFragment(baristaMain, item);
                        break;
                    case R.id.nvLearnHome:
                        replaceFragment(learnMain, item);
                        break;
                    case R.id.nvProfileHome:
                        replaceFragment(profileMain, item);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        int count = manager.getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void replaceFragment (Fragment fragment, MenuItem item){
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(
                    R.anim.fade_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
            );
            ft.replace(container, fragment);
            item.setChecked(true);
//            if (!isMainItem(item)){
//                ft.addToBackStack(backStateName);
//            }
            ft.commit();
        }
    }

    public void replaceFragment (Fragment fragment){
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(
                    R.anim.fade_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
            );
            ft.replace(container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    private boolean isMainItem(MenuItem item){
        //Dont add to backstack when it is main page
        for (int i = 0; i < menuItem.size(); i++) {
            if(item.getItemId() == menuItem.get(i)){
                return true;
            }
        }
        return false;
    }


}