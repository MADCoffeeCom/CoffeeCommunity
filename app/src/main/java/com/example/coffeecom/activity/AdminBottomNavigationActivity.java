package com.example.coffeecom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.coffeecom.R;
import com.example.coffeecom.fragment.ApplicationFragment;
import com.example.coffeecom.fragment.LearnActivityFragment;
import com.example.coffeecom.fragment.ProfileMainFragment;
import com.example.coffeecom.fragment.ReportedBaristaFragment;
import com.example.coffeecom.fragment.ReportedPostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminBottomNavigationActivity extends AppCompatActivity {

    private final String TAG = "BottomNavigationActivity";
    BottomNavigationView btmNavBar;
    int container = R.id.containerMainPage;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_bottom_navigation);

        btmNavBar = findViewById(R.id.bottomNavigationView);
        btmNavBar.setSelectedItemId(R.id.nvApplication);

        ApplicationFragment application = new ApplicationFragment();
        LearnActivityFragment learn = new LearnActivityFragment();
        ReportedBaristaFragment reportedBarista = new ReportedBaristaFragment();
        ReportedPostFragment reportedPost = new ReportedPostFragment();


        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(container, application).commit();

        btmNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nvApplication:
                        replaceMainFragment(application, item);
                        break;
                    case R.id.nvLearnHome:
                        replaceMainFragment(learn, item);
                        break;
                    case R.id.nvReportedBarista:
                        replaceMainFragment(reportedBarista, item);
                        break;
                    case R.id.nvReportedPost:
                        replaceMainFragment(reportedPost, item);
                        break;
                    case R.id.nvProfileHome:
                        Intent myIntent = new Intent(AdminBottomNavigationActivity.this, LoginOrSignupActivity.class);
                        myIntent.putExtra("isLoggedOut", "true");
                        startActivity(myIntent);
                        finishAffinity();
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
//            btmNavBar.setVisibility(View.VISIBLE);
            getSupportFragmentManager().popBackStack();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void replaceMainFragment (Fragment fragment, MenuItem item){
        replaceFragment(fragment);
        item.setChecked(true);
//        btmNavBar.setVisibility(View.VISIBLE);
    }

    public void replaceFragment (Fragment fragment){
//        btmNavBar.setVisibility(View.GONE);

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
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    public void replaceFragmentWithData (Fragment fragment, Bundle bundle){
//        btmNavBar.setVisibility(View.GONE);

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