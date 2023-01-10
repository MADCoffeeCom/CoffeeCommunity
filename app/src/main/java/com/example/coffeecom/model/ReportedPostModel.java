package com.example.coffeecom.model;

import java.util.Date;

public class ReportedPostModel extends PostModel{

    String reason;

    public ReportedPostModel(String postId, int upVote, int downVote, String posterId, String senderName, String postDesc, String postPic, Date postDateTime, String reason) {
        super(postId, upVote, downVote, posterId, senderName, postDesc, postPic, postDateTime);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
