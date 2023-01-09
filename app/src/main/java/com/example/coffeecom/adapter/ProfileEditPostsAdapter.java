//package com.example.coffeecom.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.coffeecom.R;
//import com.example.coffeecom.model.ProfileEditPostsModel;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//
//public class ProfileEditPostsAdapter extends RecyclerView.Adapter<ProfileEditPostsAdapter.ViewHolder> {
//
//
//    // 1 - Data Source
//    // Currently use dummy data
//
////    private ProfileEditPostsModel[] myPostList;
//
//
////    public ProfileEditPostsAdapter(ProfileEditPostsModel[] myPostLists){
////        this.myPostList = myPostLists;
////    }
//
//    private final Context context;
//    private final ArrayList<Integer> integerArrayList;
//
//    public ProfileEditPostsAdapter(Context context, ArrayList<Integer> integerArrayList) {
//        this.context = context;
//        this.integerArrayList = integerArrayList;
//    }
//
//
//    // 2 - View Holder Class
//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        public ImageView imagePosted;
//        public FloatingActionButton btnDeletePicture;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            this.imagePosted = itemView.findViewById(R.id.imgPostImages);
//            this.btnDeletePicture = itemView.findViewById(R.id.btnDeleteImage);
//
//        }
//
//    }
//
//
//
//    // 3 - Implementing the methods
//
//    @NonNull
//    @Override
//    public ProfileEditPostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.viewholder_edit_post_picture_template, parent, false);    //think inflate as display
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ProfileEditPostsAdapter.ViewHolder holder, int position) {
//        Glide.with(context)
//                .load(integerArrayList.get(position))
//                .into(holder.imagePosted);
//
//        holder.btnDeletePicture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //to add the delete function
//            }
//        });
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return integerArrayList.size();
//    }
//}
