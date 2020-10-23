package com.cms.entities;

import java.io.Serializable;

public class User implements Serializable{
   private String userId;
   private String userName;
   private String userPassword;
   private String email;
   private String umode;
   private String bio;
   private String dateOfRegister;
   private String dob;

    public User() {
        
    }

    public User(String userId, String userName, String userPassword, String email, String umode, String bio, String dateOfRegister, String dob) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.umode = umode;
        this.bio = bio;
        this.dateOfRegister = dateOfRegister;
        this.dob = dob;
    }
   

    public User(String userName, String userPassword, String email, String dob, String bio) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.dob = dob;
        this.bio = bio;
    }
   

    public User(String userId, String userName, String userPassword, String email, String dob, String bio) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.dob = dob;
        this.bio = bio;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(String dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    public String getUmode() {
        return umode;
    }

    public void setUmode(String umode) {
        this.umode = umode;
    }
    
}
