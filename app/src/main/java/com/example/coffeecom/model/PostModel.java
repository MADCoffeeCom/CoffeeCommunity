package com.example.coffeecom.model;

import java.util.Date;

public class PostModel {
    private String postId;
    private int upVote, downVote;
    private String posterId, senderName, postDesc, postPic;
    Date postDateTime;

    public PostModel(String postId, int upVote, int downVote, String posterId, String senderName, String postDesc, String postPic, Date postDateTime) {
        this.postId = postId;
        this.upVote = upVote;
        this.downVote = downVote;
        this.posterId = posterId;
        this.senderName = senderName;
        this.postDesc = postDesc;
        this.postPic = postPic;
        this.postDateTime = postDateTime;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
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

    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
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

    public Date getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(Date postDateTime) {
        this.postDateTime = postDateTime;
    }

}
