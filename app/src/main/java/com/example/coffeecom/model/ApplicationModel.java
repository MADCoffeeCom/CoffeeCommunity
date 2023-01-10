package com.example.coffeecom.model;

public class ApplicationModel {
    String applicaitonId, userId, userBackground, appPic, appName;
    int years;

    public ApplicationModel(String applicaitonId, String userId, String userBackground, String appPic, String appName, int years) {
        this.applicaitonId = applicaitonId;
        this.userId = userId;
        this.userBackground = userBackground;
        this.appPic = appPic;
        this.appName = appName;
        this.years = years;
    }

    public String getApplicaitonId() {
        return applicaitonId;
    }

    public void setApplicaitonId(String applicaitonId) {
        this.applicaitonId = applicaitonId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserBackground() {
        return userBackground;
    }

    public void setUserBackground(String userBackground) {
        this.userBackground = userBackground;
    }

    public String getAppPic() {
        return appPic;
    }

    public void setAppPic(String appPic) {
        this.appPic = appPic;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
}
