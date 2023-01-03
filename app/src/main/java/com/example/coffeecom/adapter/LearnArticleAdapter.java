package com.example.coffeecom.adapter;

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
import com.example.coffeecom.model.ArticleModel;

import java.util.ArrayList;

public class LearnArticleAdapter extends RecyclerView.Adapter<LearnArticleAdapter.ViewHolder> {

    ArrayList<String> articleTitle;
    ArrayList<String> articlePic;

    public LearnArticleAdapter(ArrayList<String> articleTitle, ArrayList<String> articlePic) {
        this.articleTitle = articleTitle;
        this.articlePic = articlePic;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView articleTitle;
        ImageView articlePic;
        ConstraintLayout articleCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.articleTitle);
            articlePic = itemView.findViewById(R.id.articleImage);
            articleCard = itemView.findViewById(R.id.articleCard);

        }
    }

    //Create viewholder or card based on the xml file
    @Override
    public LearnArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_article, parent, false);
        return new ViewHolder(inflate);
    }

    //fill in the xml file with necessary information
    @Override
    public void onBindViewHolder(@NonNull LearnArticleAdapter.ViewHolder holder, int position) {
        holder.articleTitle.setText(articleTitle.get(position));

        //code to insert picture
        String picUrl = articlePic.get(position);
        //dummy code below
        picUrl = "coffee1";
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.articlePic);

        holder.articleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input code here to open learn article details page
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
