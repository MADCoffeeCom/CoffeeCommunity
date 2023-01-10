package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.model.BaristaModel;
import com.example.coffeecom.query.QueryRating;

public class RateBaristaFragment extends Fragment {

    ImageButton backBtn;
    ImageView rateBaristaPic;
    TextView rateBaristaFeedbackText, rateBaristaName;
    RatingBar rateBaristaRatingBar;
    Button rateBaristaSubmitBtn;

    BaristaModel currentBarista;
    int currentBaristaIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rate_barista, container, false);

        backBtn = view.findViewById(R.id.backBtn);
        rateBaristaPic = view.findViewById(R.id.rateBaristaPic);
        rateBaristaName = view.findViewById(R.id.rateBaristaName);
        rateBaristaFeedbackText = view.findViewById(R.id.rateBaristaFeedbackText);
        rateBaristaRatingBar = view.findViewById(R.id.rateBaristaRatingBar);
        rateBaristaSubmitBtn = view.findViewById(R.id.rateBaristaSubmitBtn);
        backBtn.setOnClickListener(view1 -> getActivity().onBackPressed());


        for (int i = 0; i < Provider.getBaristas().size(); i++) {
            Bundle bundle = this.getArguments();
            String barista = bundle.getString("baristaId");
            if(Provider.getBaristas().get(i).getBaristaId().equals(barista)){
                currentBarista = Provider.getBaristas().get(i);
                currentBaristaIndex = i;
            }
        }

        String picUrl = currentBarista.getUserPic();
        int drawableResourceId = getContext().getResources().getIdentifier(picUrl, "drawable", getContext().getPackageName());
        Glide.with(getContext()).load(drawableResourceId).into(rateBaristaPic);
        rateBaristaName.setText(currentBarista.getUserName());

        rateBaristaSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rating = "" + rateBaristaRatingBar.getRating();
                String feedback = "" + rateBaristaFeedbackText.getText();
                QueryRating.addRating(currentBarista.getBaristaId(), Provider.getUser().getUserId(), rating, feedback);
                Toast.makeText(getContext(), "Submitted rating!", Toast.LENGTH_SHORT).show();
                ((BottomNavigationActivity)getActivity()).replaceFragment(new HomeActivityFragment());
                ((BottomNavigationActivity)getActivity()).clearBackStack();
            }
        });

        return view;
    }
}