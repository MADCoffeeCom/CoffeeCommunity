package com.example.coffeecom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.coffeecom.R;
import com.example.coffeecom.fragment.BaristaFragment;
import com.example.coffeecom.fragment.CommunityFragment;
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
    FragmentManager manager;

    ArrayList<String> mainScreen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        btmNavBar = findViewById(R.id.bottomNavigationView);
        btmNavBar.setSelectedItemId(R.id.nvBuyCoffeeHome);
        mainScreen.add("com.example.coffeecom.fragment.HomeActivityFragment");
        mainScreen.add("com.example.coffeecom.fragment.BaristaFragment");
        mainScreen.add("com.example.coffeecom.fragment.LearnActivityFragment");
        mainScreen.add("com.example.coffeecom.fragment.CommunityFragment");
        mainScreen.add("com.example.coffeecom.fragment.ProfileMainFragment");

        ProfileMainFragment profileMain = new ProfileMainFragment();
        LearnActivityFragment learnMain = new LearnActivityFragment();
        HomeActivityFragment homeMain = new HomeActivityFragment();
        NewBaristaFragment newBaristaMain = new NewBaristaFragment();
        BaristaFragment baristaMain = new BaristaFragment();
        CommunityFragment communityMain = new CommunityFragment();

        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(container, homeMain).commit();

        btmNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nvBuyCoffeeHome:
                        replaceMainFragment(homeMain, item);
                        break;
                    case R.id.nvBaristaHome:
                        replaceMainFragment(baristaMain, item);
                        break;
                    case R.id.nvLearnHome:
                        replaceMainFragment(learnMain, item);
                        break;
                    case R.id.nvCommunityHome:
                        replaceMainFragment(communityMain, item);
                        break;
                    case R.id.nvProfileHome:
                        replaceMainFragment(profileMain, item);
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
        } else if (count == 1){
            btmNavBar.setVisibility(View.VISIBLE);
            getSupportFragmentManager().popBackStack();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void replaceMainFragment (Fragment fragment, MenuItem item){
        replaceFragment(fragment);
        item.setChecked(true);
        btmNavBar.setVisibility(View.VISIBLE);
    }

    public void replaceFragment (Fragment fragment){
        btmNavBar.setVisibility(View.GONE);

        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
        Log.i(TAG, "replaceFragment: " + backStateName);
        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(
                    R.anim.fade_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.fade_out  // popExit
            );
            ft.replace(container, fragment);
            Log.i(TAG, "replaceFragment: " + backStateName);
            if(!mainScreen.contains(backStateName)){
                ft.addToBackStack(backStateName);
            }
            ft.commit();
        }
    }

    public void replaceFragmentWithData (Fragment fragment, Bundle bundle){
        btmNavBar.setVisibility(View.GONE);

        String backStateName = fragment.getClass().getName();
        fragment.setArguments(bundle);
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(
                    R.anim.fade_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.fade_out  // popExit
            );
            ft.replace(container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    public int getContainer() {
        return container;
    }

    public void setContainer(int container) {
        this.container = container;
    }
}