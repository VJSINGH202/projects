package com.cms.entities;

import java.io.Serializable;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author DELL PC
 */
public class Blog implements Serializable{
     private int blogId;
     private String blogTitle;
     private String blogCatagory;
     private String blogContent;
     private FileItem blogPhoto;
     private String dop;
     private int blogCount;
     private String userid;
    public Blog() {
    }

    public Blog(int blogId, String blogTitle, String blogCatagory, String dop, int blogCount, String userid) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogCatagory = blogCatagory;
        this.dop = dop;
        this.blogCount = blogCount;
        this.userid = userid;
    }

    public Blog(int blogId, String blogTitle, String blogCatagory, String blogContent, FileItem blogPhoto, String dop) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogCatagory = blogCatagory;
        this.blogContent = blogContent;
        this.blogPhoto = blogPhoto;
        this.dop = dop;
    }

    public Blog(int blogId, String blogTitle, String blogCatagory, String blogContent, String dop, String userid) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogCatagory = blogCatagory;
        this.blogContent = blogContent;
        this.dop = dop;
        this.userid = userid;
    }

    public Blog(int blogId, String blogTitle, String blogCatagory) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogCatagory = blogCatagory;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogId=" + blogId + ", blogTitle=" + blogTitle + ", blogCatagory=" + blogCatagory + ", blogContent=" + blogContent + ", dop=" + dop + ", userid=" + userid + '}';
    }
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogCatagory() {
        return blogCatagory;
    }

    public void setBlogCatagory(String blogCatagory) {
        this.blogCatagory = blogCatagory;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public FileItem getBlogPhoto() {
        return blogPhoto;
    }

    public void setBlogPhoto(FileItem blogPhoto) {
        this.blogPhoto = blogPhoto;
    }

    public String getDop() {
        return dop;
    }

    public void setDop(String dop) {
        this.dop = dop;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }
}
