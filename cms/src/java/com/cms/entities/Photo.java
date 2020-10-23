
package com.cms.entities;

import java.io.Serializable;
import org.apache.commons.fileupload.FileItem;

public class Photo implements Serializable{
    private int photoId;
    private FileItem photo;
    private String dor;
    private int userId;

    public Photo() {
    }

    public Photo(FileItem photo, int userId) {
        this.photo = photo;
        this.userId = userId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public FileItem getPhoto() {
        return photo;
    }

    public void setPhoto(FileItem photo) {
        this.photo = photo;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
