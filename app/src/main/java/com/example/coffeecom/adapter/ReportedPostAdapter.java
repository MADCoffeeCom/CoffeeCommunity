package com.example.coffeecom.adapter;

import static com.example.coffeecom.helper.FormatDateTime.convertDatetoStringDate;
import static com.example.coffeecom.helper.ToTitleCase.toTitleCase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.R;
import com.example.coffeecom.activity.AdminBottomNavigationActivity;
import com.example.coffeecom.fragment.ReportedPostDetailsFragment;
import com.example.coffeecom.model.ReportedPostModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReportedPostAdapter extends RecyclerView.Adapter<ReportedPostAdapter.ViewHolder>{

    ArrayList<ReportedPostModel> reportedPost;
    Context activity;

    public ReportedPostAdapter(ArrayList<ReportedPostModel> reportedPostModels, Context activity) {
        this.reportedPost = reportedPostModels;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView reportedPostPic;
        TextView postOwnerNameText;
        TextView reportedPostContentText;
        TextView reportedReasonText;
        ConstraintLayout reportedPostCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            reportedPostPic = itemView.findViewById(R.id.reportedPostPic);
            postOwnerNameText = itemView.findViewById(R.id.postOwnerNameText);
            reportedPostContentText = itemView.findViewById(R.id.reportedPostContentText);
            reportedReasonText = itemView.findViewById(R.id.reportedReasonText);
            reportedPostCard = itemView.findViewById(R.id.reportedPostCard);

        }
    }
    @NonNull
    @Override
    public ReportedPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_reported_post_card, parent, false);
        return new ReportedPostAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportedPostAdapter.ViewHolder holder, int position) {
        String picUrl = reportedPost.get(position).getPostPic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.reportedPostPic);

        holder.postOwnerNameText.setText(toTitleCase(reportedPost.get(position).getSenderName()));
        holder.reportedPostContentText.setText(reportedPost.get(position).getPostDesc());
        holder.reportedReasonText.setText(reportedPost.get(position).getReason());
        holder.reportedPostCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("postId", reportedPost.get(position).getPostId());

                ((AdminBottomNavigationActivity)activity).replaceFragmentWithData(new ReportedPostDetailsFragment(), bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reportedPost.size();
    }


}
