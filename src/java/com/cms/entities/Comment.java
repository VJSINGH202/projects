package com.cms.entities;
import java.io.Serializable;

/**
 *
 * @author DELL PC
 */
public class Comment implements Serializable{
    private String commentId;
    private String commentDescription;
    private String commentDate;
    private String status;
    private String userName;
    private String userMode;
    private int userId;
    private int blogId;

    public Comment(String commentDescription, String commentDate, String userName, int userId) {
        this.commentDescription = commentDescription;
        this.commentDate = commentDate;
        this.userName = userName;
        this.userId = userId;
    }

    public Comment(String commentDescription, int userId, int blogId) {
        this.commentDescription = commentDescription;
        this.userId = userId;
        this.blogId = blogId;
    }

    public Comment(String commentId, String commentDescription, String status, int userId, int blogId) {
        this.commentId = commentId;
        this.commentDescription = commentDescription;
        this.status = status;
        this.userId = userId;
        this.blogId = blogId;
    }

    public Comment(String commentId, String commentDescription, String commentDate, String status, String userName, String userMode) {
        this.commentId = commentId;
        this.commentDescription = commentDescription;
        this.commentDate = commentDate;
        this.status = status;
        this.userName = userName;
        this.userMode = userMode;
    }
    
    
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserMode() {
        return userMode;
    }

    public void setUserMode(String userMode) {
        this.userMode = userMode;
    }
    
}
