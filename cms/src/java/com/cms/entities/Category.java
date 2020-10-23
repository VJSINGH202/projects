package com.cms.entities;

import java.io.Serializable;

/**
 *
 * @author DELL PC
 */
public class Category implements Serializable{
    private String id;
    private String categoryName;
    private String categoryDate;
    private String userId;

    public Category(String id, String categoryName, String categoryDate, String userId) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDate = categoryDate;
        this.userId = userId;
    }

    public Category(String categoryName, String userId) {
        this.categoryName = categoryName;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDate() {
        return categoryDate;
    }

    public void setCategoryDate(String categoryDate) {
        this.categoryDate = categoryDate;
    }
}
