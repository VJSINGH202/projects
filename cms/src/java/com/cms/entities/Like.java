package com.cms.entities;

import java.io.Serializable;
/**
 *
 * @author DELL PC
 */
public class Like implements Serializable{
    private String likeId;
    private String likeBlogTitle;
    private String likeBlogCategory;
    private String likeDate;
    private String likeBlogId;

    public Like(String likeId, String likeBlogTitle, String likeBlogCategory, String likeDate, String likeBlogId) {
        this.likeId = likeId;
        this.likeBlogTitle = likeBlogTitle;
        this.likeBlogCategory = likeBlogCategory;
        this.likeDate = likeDate;
        this.likeBlogId = likeBlogId;
    }

    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }

    public String getLikeBlogTitle() {
        return likeBlogTitle;
    }

    public void setLikeBlogTitle(String likeBlogTitle) {
        this.likeBlogTitle = likeBlogTitle;
    }

    public String getLikeBlogCategory() {
        return likeBlogCategory;
    }

    public void setLikeBlogCategory(String likeBlogCategory) {
        this.likeBlogCategory = likeBlogCategory;
    }

    public String getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(String likeDate) {
        this.likeDate = likeDate;
    }

    public String getLikeBlogId() {
        return likeBlogId;
    }

    public void setLikeBlogId(String likeBlogId) {
        this.likeBlogId = likeBlogId;
    }
    
}
