package com.cms.entities;

/**
 *
 * @author DELL PC
 */
public class FavouriteBlog {
    private String favouriteId;
    private String favouriteBlogTitle;
    private String favouriteBlogCategory;
    private String favouriteDate;
    private String favouriteBlogId;

    public FavouriteBlog(String favouriteId, String favouriteBlogTitle, String favouriteBlogCategory, String favouriteDate, String favouriteBlogId) {
        this.favouriteId = favouriteId;
        this.favouriteBlogTitle = favouriteBlogTitle;
        this.favouriteBlogCategory = favouriteBlogCategory;
        this.favouriteDate = favouriteDate;
        this.favouriteBlogId = favouriteBlogId;
    }

    
    public String getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(String favouriteId) {
        this.favouriteId = favouriteId;
    }

    public String getFavouriteBlogTitle() {
        return favouriteBlogTitle;
    }

    public void setFavouriteBlogTitle(String favouriteBlogTitle) {
        this.favouriteBlogTitle = favouriteBlogTitle;
    }

    public String getFavouriteBlogCategory() {
        return favouriteBlogCategory;
    }

    public void setFavouriteBlogCategory(String favouriteBlogCategory) {
        this.favouriteBlogCategory = favouriteBlogCategory;
    }

    public String getFavouriteDate() {
        return favouriteDate;
    }

    public void setFavouriteDate(String favouriteDate) {
        this.favouriteDate = favouriteDate;
    }

    public String getFavouriteBlogId() {
        return favouriteBlogId;
    }

    public void setFavouriteBlogId(String favouriteBlogId) {
        this.favouriteBlogId = favouriteBlogId;
    }
    
}
