package com.newproject.omega;

public class RewardModel {

    private String title;
    private String expireDate;
    private String coupenBody;

    public RewardModel(String title, String expireDate, String coupenBody) {
        this.title = title;
        this.expireDate = expireDate;
        this.coupenBody = coupenBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCoupenBody() {
        return coupenBody;
    }

    public void setCoupenBody(String coupenBody) {
        this.coupenBody = coupenBody;
    }
}
