package com.example.coffeecom.model;

public class PostModel {
    private int postId;
    private int upVote, downVote;
    private String senderId, senderName, postDesc, postPic, postDateTime, postTitle;

    public PostModel(int postId, int upVote, int downVote, String senderId, String senderName, String postDesc, String postPic) {
        this.postId = postId;
        this.upVote = upVote;
        this.downVote = downVote;
        this.senderId = senderId;
        this.senderName = senderName;
        this.postDesc = postDesc;
        this.postPic = postPic;
    }

    //new post
    public PostModel(int postId, String senderId, String senderName, String postDesc, String postPic) {
        this.postId = postId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.postDesc = postDesc;
        this.postPic = postPic;
    }


    //for profile page summary
    public PostModel(String postDateTime, String postPic, String postTitle, int upVote, int downVote){
        this.postDateTime = postDateTime;
        this.postPic = postPic;
        this.postTitle = postTitle;
        this.upVote = upVote;
        this.downVote = downVote;
    }

    public String getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(String postDateTime) {
        this.postDateTime = postDateTime;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getPostPic() {
        return postPic;
    }

    public void setPostPic(String postPic) {
        this.postPic = postPic;
    }
}
