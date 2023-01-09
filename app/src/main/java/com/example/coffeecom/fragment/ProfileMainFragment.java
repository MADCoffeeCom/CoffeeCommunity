package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.FormatDateTime.convertStringtoDate;
import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.activity.LoginOrSignupActivity;
import com.example.coffeecom.adapter.ProfileBrewHistoryAdapter;
import com.example.coffeecom.adapter.ProfileOrderHistoryAdapter;
import com.example.coffeecom.adapter.ProfilePostHistoryAdapter;

public class ProfileMainFragment extends Fragment {

    private static final String TAG = "ProfileMainFragment";

    private TextView txtProfileName, txtProfileType;
    private ImageView imgBarista;
    private RecyclerView orderListRV, brewListRV, postListRV;
    private ImageButton btnEditProfile;
    private ConstraintLayout btnTerms, btnPrivacy, btnBankCard, btnHelpDesk, btnFeedback, btnLogOut;

    ProfileOrderHistoryAdapter orderAdapter;
    ProfileBrewHistoryAdapter brewAdapter;
    ProfilePostHistoryAdapter postAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile_main,container,false);
        initialiseID(view);
        brewRecycleView();
        postRecycleView();
        orderRecycleView();

        return view;
    }

    public void orderRecycleView() {
        orderAdapter = new ProfileOrderHistoryAdapter(Provider.getUser().getOrderedHistory(), getActivity());
        orderListRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        orderListRV.setAdapter(orderAdapter);
    }

    public void brewRecycleView() {
        Log.i(TAG, "brewRecycleView: " + Provider.getUser().getBrewedOrder().size());
        brewAdapter = new ProfileBrewHistoryAdapter(Provider.getUser().getBrewedOrder(), getActivity());
        brewListRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        brewListRV.setAdapter(brewAdapter);
    }

    public void postRecycleView() {
        postAdapter = new ProfilePostHistoryAdapter(Provider.getUser().getPostedPost(), getActivity());
        postListRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        postListRV.setAdapter(postAdapter);
    }

    private void initialiseID(View view){
        txtProfileName = view.findViewById(R.id.textViewProfileName);
        txtProfileType = view.findViewById(R.id.textViewProfileType);
        btnEditProfile = view.findViewById(R.id.imageButtonProfileEdit);
        btnTerms = view.findViewById(R.id.btnProfile1);
        btnPrivacy = view.findViewById(R.id.btnProfile2);
        btnBankCard = view.findViewById(R.id.btnProfile3);
        btnHelpDesk = view.findViewById(R.id.btnProfile4);
        btnFeedback = view.findViewById(R.id.btnProfile5);
        btnLogOut = view.findViewById(R.id.btnProfile6);
        imgBarista = view.findViewById(R.id.baristaPic);
        orderListRV = view.findViewById(R.id.orderListRV);
        brewListRV = view.findViewById(R.id.brewListRV);
        postListRV = view.findViewById(R.id.postListRV);

        txtProfileName.setText(toTitleCase(Provider.getUser().getUserName()));
        if(!Provider.getUser().getAdminId().isEmpty()){
            txtProfileType.setText("Admin");
        }else if(!Provider.getUser().getBaristaId().isEmpty()){
            txtProfileType.setText("Barista");
        }else{
            txtProfileType.setText("User");
        }

        initialiseBtn();
    }

    public void initialiseBtn() {
        btnEditProfile.setOnClickListener(view -> ((BottomNavigationActivity)getActivity()).replaceFragment(new ProfileEditUserFragment()));
        btnTerms.setOnClickListener(view -> ((BottomNavigationActivity)getActivity()).replaceFragment(new TermsOfUseFragment()));
        btnPrivacy.setOnClickListener(view -> ((BottomNavigationActivity)getActivity()).replaceFragment(new PrivacyPolicyFragment()));
        btnBankCard.setOnClickListener(view -> ((BottomNavigationActivity)getActivity()).replaceFragment(new BankCardFragment()));
        btnHelpDesk.setOnClickListener(view -> ((BottomNavigationActivity)getActivity()).replaceFragment(new HelpdeskFragment()));
        btnFeedback.setOnClickListener(view -> ((BottomNavigationActivity)getActivity()).replaceFragment(new FeedbackFragment()));

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(((BottomNavigationActivity)getContext()), LoginOrSignupActivity.class);
                myIntent.putExtra("isLoggedOut", "true");
                ((BottomNavigationActivity)getActivity()).startActivity(myIntent);
                ((BottomNavigationActivity)getActivity()).finishAffinity();
            }
        });
    }


}