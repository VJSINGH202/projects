package com.cms.entities;

import java.io.Serializable;

public class Author implements Serializable{
    private String authorId;
    private String authorName;
    private String authorEmail;
    private String authorBiblography;
    private String authorBlogId;
    private String authorBlogTitle;
    private String authorBlogCategory;
    private String authorBlogContent;
    private String authorBlogCreatedAtDate;

    public Author(String authorId, String authorBlogId) {
        this.authorId = authorId;
        this.authorBlogId = authorBlogId;
    }

    public Author(String authorName, String authorEmail, String authorBlogId, String authorBlogTitle, String authorBlogCategory, String authorBlogCreatedAtDate) {
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorBlogId = authorBlogId;
        this.authorBlogTitle = authorBlogTitle;
        this.authorBlogCategory = authorBlogCategory;
        this.authorBlogCreatedAtDate = authorBlogCreatedAtDate;
    }
    

    public Author(String authorId, String authorName, String authorEmail, String authorBiblography, String authorBlogId, String authorBlogTitle, String authorBlogCategory, String authorBlogContent, String authorBlogCreatedAtDate) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorBiblography = authorBiblography;
        this.authorBlogId = authorBlogId;
        this.authorBlogTitle = authorBlogTitle;
        this.authorBlogCategory = authorBlogCategory;
        this.authorBlogContent = authorBlogContent;
        this.authorBlogCreatedAtDate = authorBlogCreatedAtDate;
    }
    

    public Author(String authorId, String authorName, String authorEmail, String authorBlogId, String authorBlogTitle, String authorBlogCategory, String authorBlogContent, String authorBlogCreatedAtDate) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorBlogId = authorBlogId;
        this.authorBlogTitle = authorBlogTitle;
        this.authorBlogCategory = authorBlogCategory;
        this.authorBlogContent = authorBlogContent;
        this.authorBlogCreatedAtDate = authorBlogCreatedAtDate;
    }
    
    
    
    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }


    public String getAuthorBlogId() {
        return authorBlogId;
    }

    public String getAuthorBlogTitle() {
        return authorBlogTitle;
    }

    public String getAuthorBlogCategory() {
        return authorBlogCategory;
    }

    public String getAuthorBlogContent() {
        return authorBlogContent;
    }

    public String getAuthorBlogCreatedAtDate() {
        return authorBlogCreatedAtDate;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getAuthorBiblography() {
        return authorBiblography;
    }

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", authorName=" + authorName + ", authorEmail=" + authorEmail + ", authorBiblography=" + authorBiblography + ", authorBlogId=" + authorBlogId + ", authorBlogTitle=" + authorBlogTitle + ", authorBlogCategory=" + authorBlogCategory + ", authorBlogContent=" + authorBlogContent + ", authorBlogCreatedAtDate=" + authorBlogCreatedAtDate + '}';
    }
    
}
