package com.example.coffeecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfilePostHistoryAdapter extends RecyclerView.Adapter<ProfilePostHistoryAdapter.ViewHolder> {

    private ArrayList<PostModel> myPostList;

    public ProfilePostHistoryAdapter (ArrayList<PostModel> myPostList){
        this.myPostList = myPostList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView postImage;
        public TextView postTitle, postLikes;
        public ConstraintLayout postHistoryCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.postImage = itemView.findViewById(R.id.imgPostHistory);
            this.postTitle = itemView.findViewById(R.id.txtPostHistoryTitle);
            this.postLikes = itemView.findViewById(R.id.txtPostTotalLikes);
            this.postHistoryCard = itemView.findViewById(R.id.coffeePostHistoryCard);
        }

    }

    @NonNull
    @Override
    public ProfilePostHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.viewholder_profile_post_history, parent, false);    //think inflate as display
        ProfilePostHistoryAdapter.ViewHolder viewHolder = new ProfilePostHistoryAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePostHistoryAdapter.ViewHolder holder, int position) {
        String picUrl = myPostList.get(position).getPostPic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.postImage);
        holder.postTitle.setText(myPostList.get(position).getPostDesc());
        int vote = myPostList.get(position).getUpVote() - myPostList.get(position).getDownVote();
        holder.postLikes.setText("" + vote);
    }

    @Override
    public int getItemCount() {
        return myPostList.size();
    }

    // 4 - On Click
}