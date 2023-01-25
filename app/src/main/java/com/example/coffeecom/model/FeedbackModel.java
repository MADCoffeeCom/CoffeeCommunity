package com.example.coffeecom.model;

public class FeedbackModel {
    private String userId;
    private int appRating;
    private String feedbackDesc;

    private String feedbackId;
    private String userName;

    public FeedbackModel(String userId, int appRating, String feedbackDesc) {
        this.userId = userId;
        this.appRating = appRating;
        this.feedbackDesc = feedbackDesc;
    }

    public FeedbackModel(String feedbackId, String userId, String userName, int appRating, String feedbackDesc) {
        this.userId = userId;
        this.appRating = appRating;
        this.feedbackDesc = feedbackDesc;
        this.feedbackId = feedbackId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAppRating() {
        return appRating;
    }

    public void setAppRating(int appRating) {
        this.appRating = appRating;
    }

    public String getFeedbackDesc() {
        return feedbackDesc;
    }

    public void setFeedbackDesc(String feedbackDesc) {
        this.feedbackDesc = feedbackDesc;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
