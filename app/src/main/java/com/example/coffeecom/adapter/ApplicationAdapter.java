package com.example.coffeecom.adapter;

import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.AdminBottomNavigationActivity;
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.fragment.ApplicationDetailsFragment;
import com.example.coffeecom.model.ApplicationModel;

import java.util.ArrayList;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder>{

    ArrayList<ApplicationModel> application;
    Context activity;

    public ApplicationAdapter(ArrayList<ApplicationModel> application, Context activity) {
        this.application = application;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView applicantPic;
        TextView applicantNameText, applicantExperienceText;
        ConstraintLayout applicationCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            applicantPic = itemView.findViewById(R.id.applicantPic);
            applicantNameText = itemView.findViewById(R.id.applicantNameText);
            applicantExperienceText = itemView.findViewById(R.id.applicantExperienceText);
            applicationCard = itemView.findViewById(R.id.applicationCard);
        }
    }

    @NonNull
    @Override
    public ApplicationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_application_card, parent, false);
        return new ApplicationAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationAdapter.ViewHolder holder, int position) {
        String picUrl = application.get(position).getAppPic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.applicantPic);

        holder.applicantNameText.setText(toTitleCase(application.get(position).getAppName()));
        holder.applicantExperienceText.setText("" + application.get(position).getYears());

        holder.applicationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("applicationId", application.get(position).getApplicaitonId());
                ((AdminBottomNavigationActivity)activity).replaceFragmentWithData(new ApplicationDetailsFragment(), bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return application.size();
    }


}
