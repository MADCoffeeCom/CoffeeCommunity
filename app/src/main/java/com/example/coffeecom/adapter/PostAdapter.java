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
import com.example.coffeecom.activity.BottomNavigationActivity;
import com.example.coffeecom.fragment.PostDetailsFragment;
import com.example.coffeecom.model.PostModel;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    ArrayList<PostModel> posts;
    Context activity;

    public PostAdapter(ArrayList<PostModel> posts, Context activity) {
        this.posts = posts;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView posterNameText, postDateText, postDescText;
        ImageView postPic;
        ConstraintLayout postCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            posterNameText = itemView.findViewById(R.id.posterNameText);
            postDateText = itemView.findViewById(R.id.postDateText);
            postDescText = itemView.findViewById(R.id.postDescText);
            postPic = itemView.findViewById(R.id.postPic);
            postCard = itemView.findViewById(R.id.postCard);
        }
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_post, parent, false);
        return new PostAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.posterNameText.setText(toTitleCase(posts.get(position).getSenderName()));
        holder.postDateText.setText(convertDatetoStringDate(posts.get(position).getPostDateTime()));
        holder.postDescText.setText(posts.get(position).getPostDesc());

        String picUrl = posts.get(position).getPostPic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.postPic);

        holder.postCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("postId", posts.get(position).getPostId());
                ((BottomNavigationActivity)activity).replaceFragmentWithData(new PostDetailsFragment(), bundle);
            }
        });


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
