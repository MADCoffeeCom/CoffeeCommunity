package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.AdminBottomNavigationActivity;
import com.example.coffeecom.adapter.HelpdeskAdapter;
import com.example.coffeecom.model.HelpdeskModel;

import java.util.ArrayList;


public class HelpdeskAdminFragment extends Fragment {


    ArrayList<HelpdeskModel> helpdesk = new ArrayList<>();
    ImageButton addHelpBtn;
    HelpdeskAdapter helpAdapter;
    RecyclerView editHelpdeskRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_helpdesk_admin, container, false);
        editHelpdeskRV = view.findViewById(R.id.editHelpdeskRV);
        addHelpBtn = view.findViewById(R.id.addHelpBtn);
        addHelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AdminBottomNavigationActivity)getActivity()).replaceFragment(new HelpdeskEditFragment());
            }
        });

        helpdeskRecycleView();
        helpdesk = Provider.getHelpdesks();
        return view;
    }

    public void helpdeskRecycleView() {
        helpAdapter = new HelpdeskAdapter(helpdesk, getActivity(), "edit");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        editHelpdeskRV.setLayoutManager(linearLayoutManager);
        editHelpdeskRV.setAdapter(helpAdapter);
    }
}