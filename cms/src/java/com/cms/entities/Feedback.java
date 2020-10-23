package com.cms.entities;

import java.io.Serializable;

public class Feedback implements Serializable{
    private String id;
    private String feed;
    private String userId;
    private String feedbackDate;
    private String feedbackStatus;
    private String userName;
    private String userMode;

    public Feedback() {
    }

    public Feedback(String id, String feed, String feedbackDate, String feedbackStatus, String userName, String userMode) {
        this.id = id;
        this.feed = feed;
        this.feedbackDate = feedbackDate;
        this.feedbackStatus = feedbackStatus;
        this.userName = userName;
        this.userMode = userMode;
    }

    public Feedback(String id, String feed, String userId, String feedbackDate, String feedbackStatus) {
        this.id = id;
        this.feed = feed;
        this.userId = userId;
        this.feedbackDate = feedbackDate;
        this.feedbackStatus = feedbackStatus;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMode() {
        return userMode;
    }

    public void setUserMode(String userMode) {
        this.userMode = userMode;
    }
}
