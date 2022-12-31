package com.example.coffeecom.model;

import java.sql.Time;
import java.util.Date;

public class ProfilePostHistoryModel {

    private String postDateTime;
    private int postImage;
    private String postTitle;
    private int postTotalLikes, postTotalDislikes, postTotalComments;

    public ProfilePostHistoryModel(String postDateTime, int postImage, String postTitle, int postTotalLikes, int postTotalDislikes, int postTotalComments) {
        this.postDateTime = postDateTime;
        this.postImage = postImage;
        this.postTitle = postTitle;
        this.postTotalLikes = postTotalLikes;
        this.postTotalDislikes = postTotalDislikes;
        this.postTotalComments = postTotalComments;
    }

    public String getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(String postDateTime) {
        this.postDateTime = postDateTime;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public int getPostTotalLikes() {
        return postTotalLikes;
    }

    public void setPostTotalLikes(int postTotalLikes) {
        this.postTotalLikes = postTotalLikes;
    }

    public int getPostTotalDislikes() {
        return postTotalDislikes;
    }

    public void setPostTotalDislikes(int postTotalDislikes) {
        this.postTotalDislikes = postTotalDislikes;
    }

    public int getPostTotalComments() {
        return postTotalComments;
    }

    public void setPostTotalComments(int postTotalComments) {
        this.postTotalComments = postTotalComments;
    }
}
