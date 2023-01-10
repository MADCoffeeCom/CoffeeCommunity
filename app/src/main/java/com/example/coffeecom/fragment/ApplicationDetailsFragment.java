package com.example.coffeecom.fragment;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.ApplicationModel;
import com.example.coffeecom.query.QueryApplication;

public class ApplicationDetailsFragment extends Fragment {

    TextView applicantNameText, applicantExperienceText, applicantBackgroundText;
    ImageView applicantPic;
    Button declineApplyBtn, acceptApplyBtn;
    ImageButton backBtn;

    ApplicationModel currentApplicant;
    int currentApplicantIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_details, container, false);
        initialiseView(view);

        Bundle bundle = this.getArguments();
        for (int i = 0; i < Provider.getApplication().size(); i++) {
            if(Provider.getApplication().get(i).getApplicaitonId().equals(bundle.getString("applicationId"))){
                currentApplicant = Provider.getApplication().get(i);
                currentApplicantIndex = i;
            }
        }
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());
        applicantNameText.setText(toTitleCase(currentApplicant.getAppName()));
        applicantExperienceText.setText("" + currentApplicant.getYears());
        applicantBackgroundText.setText(currentApplicant.getUserBackground());
        String picUrl = currentApplicant.getAppPic();
        int drawableResourceId = getContext().getResources().getIdentifier(picUrl, "drawable", getContext().getPackageName());
        Glide.with(getContext()).load(drawableResourceId).into(applicantPic);

        acceptApplyBtn.setOnClickListener(view12 -> {
            QueryApplication.acceptApplication(currentApplicant.getApplicaitonId(), currentApplicant.getUserId(), currentApplicant.getUserBackground(), "" + currentApplicant.getYears());
            QueryApplication.queryApplication();
            getActivity().onBackPressed();
            Toast.makeText(getContext(), "Accepted!", Toast.LENGTH_SHORT).show();
        });
        declineApplyBtn.setOnClickListener(view13 -> {
            QueryApplication.declineApplication(currentApplicant.getApplicaitonId());
            QueryApplication.queryApplication();
            getActivity().onBackPressed();
            Toast.makeText(getContext(), "Accepted!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    public void initialiseView(View view){
        applicantNameText = view.findViewById(R.id.applicantNameText);
        applicantExperienceText = view.findViewById(R.id.applicantExperienceText);
        applicantBackgroundText = view.findViewById(R.id.applicantBackgroundText);
        applicantPic = view.findViewById(R.id.applicantPic);
        acceptApplyBtn = view.findViewById(R.id.acceptApplyBtn);
        declineApplyBtn = view.findViewById(R.id.declineApplyBtn);
        backBtn = view.findViewById(R.id.backBtn);
    }
}