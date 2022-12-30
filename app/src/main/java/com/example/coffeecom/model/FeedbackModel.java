package com.example.coffeecom.model;

public class FeedbackModel {
    private String userId;
    private int appRating;
    private String feedbackDesc;

    public FeedbackModel(String userId, int appRating, String feedbackDesc) {
        this.userId = userId;
        this.appRating = appRating;
        this.feedbackDesc = feedbackDesc;
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
}
