package com.example.coffeecom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.fragment.HomeActivityFragment;
import com.example.coffeecom.fragment.LearnActivityFragment;
import com.example.coffeecom.fragment.LearnDetailsFragment;
import com.example.coffeecom.model.ArticleModel;

import java.util.ArrayList;

public class LearnArticleAdapter extends RecyclerView.Adapter<LearnArticleAdapter.ViewHolder> {

    ArrayList<ArticleModel> articles;
    Context context;


    public LearnArticleAdapter(ArrayList<ArticleModel> articles, Context context) {
        this.context = context;
        this.articles = articles;
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
    public void onBindViewHolder(@NonNull LearnArticleAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.articleTitle.setText(articles.get(position).getArticleTitle());

        //code to insert picture
        String picUrl = articles.get(position).getArticlePic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.articlePic);

        holder.articleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input code here to open learn article details page
                Provider.setCurrentArticleId(articles.get(position).getArticleId());
                LearnDetailsFragment learnDetails = new LearnDetailsFragment();
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.containerMainPage,learnDetails).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


}
