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

    // 1 - Data Source
    // Currently use dummy data

    private ArrayList<PostModel> myPostList;

    public ProfilePostHistoryAdapter (ArrayList<PostModel> myPostList){
        this.myPostList = myPostList;
    }



    // 2 - View Holder Class
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView postImage;
        public TextView postDateTime, postTitle, postLikes, postDislikes, postComments;
        public ConstraintLayout postHistoryCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.postImage = itemView.findViewById(R.id.imgPostHistory);
            this.postDateTime = itemView.findViewById(R.id.txtPostHistoryDate);
            this.postTitle = itemView.findViewById(R.id.txtPostHistoryTitle);
            this.postLikes = itemView.findViewById(R.id.txtPostTotalLikes);
            this.postDislikes = itemView.findViewById(R.id.txtPostTotalDislikes);

            this.postHistoryCard = itemView.findViewById(R.id.coffeePostHistoryCard);
//            this.postComments = itemView.findViewById(R.id.txtPostTotalComments);

//            itemView.setOnClickListener((View.OnClickListener) this);
        }

    }

    // 3 - Implementing the methods

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


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // 4 - On Click
}