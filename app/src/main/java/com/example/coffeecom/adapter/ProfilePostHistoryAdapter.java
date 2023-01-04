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
import com.example.coffeecom.model.PostModel;
import com.example.coffeecom.R;

public class ProfilePostHistoryAdapter extends RecyclerView.Adapter<ProfilePostHistoryAdapter.ViewHolder> {

    // 1 - Data Source
    // Currently use dummy data

    private PostModel[] myPostList;

    public ProfilePostHistoryAdapter (PostModel[] myPostList){
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

        final PostModel myPostListData = myPostList[position];
        holder.postDateTime.setText((CharSequence) myPostList[position].getPostDateTime());
        holder.postTitle.setText(myPostList[position].getPostTitle());
        holder.postLikes.setText(String.valueOf(myPostList[position].getUpVote()));
        holder.postDislikes.setText(String.valueOf(myPostList[position].getDownVote()));
//        holder.postComments.setText(String.valueOf(myPostList[position].getPostTotalComments()));
//        holder.postImage.setImageResource(myPostList[position].getPostPic());
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(myPostList[position].getPostPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.postImage);

//        holder.postHistoryCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "HMMM STILL DOING", Toast.LENGTH_SHORT).show();
//                //input code to open details coffee order page
//                //havent complete
////                Intent intent = new Intent(holder.itemView.getContext(), BaristaListActivity.class);
////                holder.itemView.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return myPostList.length;
    }

    // 4 - On Click
}