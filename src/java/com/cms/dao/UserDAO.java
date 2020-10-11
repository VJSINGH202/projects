package com.cms.dao;

import com.cms.entities.User;
import java.util.List;

public interface UserDAO {
    
  public String registerUser(User user,String mode) throws Exception;
  public String loginUser(User user) throws Exception;
  public User getUser(int uid) throws Exception;
  public String updateUserProfile(User user) throws Exception;
  public String updateUserPassword(User user) throws Exception;
  public List<User> getAllUsers()throws Exception;
  public List<User> getSearchUsers(String search)throws Exception;
  public int getUserCount()throws Exception;
  public String deleteUser(int id)throws Exception;
}