package com.cms.dao.impl;

import com.cms.dao.UserDAO;
import com.cms.db.DBConnection;
import com.cms.entities.User;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDAOImpl implements UserDAO {

    private CallableStatement cs = null;

    @Override
    public String registerUser(User user, String mode) throws Exception {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date udate = sm.parse(user.getDob());
        Long i = udate.getTime();
        java.sql.Date sdate = new java.sql.Date(i);
        try {
            cs = DBConnection.connect().prepareCall("{call pro_add_user(?,?,?,?,?,?,?)}");
            cs.setString(1, user.getUserName());
            cs.setString(2, user.getUserPassword());
            cs.setString(3, mode);
            cs.setString(4, user.getEmail());
            cs.setString(5, user.getBio());
            cs.setDate(6, sdate);
            cs.registerOutParameter(7, Types.VARCHAR);
            cs.execute();
            return cs.getString(7);
        } finally {
            closeConnection();
        }
    }

    @Override
    public String loginUser(User user) throws Exception {
        try {
            cs = DBConnection.connect().prepareCall("{call pro_check_user_id(?,?,?)}");
            cs.setString(1, user.getUserName());
            cs.setString(2, user.getUserPassword());
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.execute();
            return cs.getString(3);
        } finally {
            closeConnection();
        }
    }

    void closeConnection() throws Exception {
        if (DBConnection.connect() != null) {
            DBConnection.connect().close();
        }
        if (cs != null) {
            cs.close();
        }
    }

    @Override
    public User getUser(int id) throws Exception {
        User user = new User();
        cs = DBConnection.connect().prepareCall("{call pro_get_user_info(?)}");
        cs.setInt(1, id);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String uid = rs.getString("id");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String bio = rs.getString("bio");
            String mode = rs.getString("umode");
            String registerDate = rs.getString("register_at");
            user.setUserId(uid);
            user.setUserName(username);
            user.setEmail(email);
            user.setBio(bio);
            user.setDateOfRegister(registerDate);
            user.setUmode(mode);
        }
        return user;
    }

    @Override
    public String updateUserProfile(User user) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_update_user_info(?,?,?,?,?)}");
        cs.setInt(1, Integer.parseInt(user.getUserId()));
        cs.setString(2, user.getUserName());
        cs.setString(3, user.getEmail());
        cs.setString(4, user.getBio());
        cs.registerOutParameter(5, Types.VARCHAR);
        cs.execute();
        return cs.getString(5);
    }

    @Override
    public String updateUserPassword(User user) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_update_user_password(?,?,?)}");
        cs.setInt(1, Integer.parseInt(user.getUserId()));
        cs.setString(2, user.getUserPassword());
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
        return cs.getString(3);
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_all_users()}");
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String username = rs.getString("username");
            String umode = rs.getString("umode");
            String email = rs.getString("email");
            String dor = rs.getString("Register_at");
            User user = new User(id, username, null, email, umode, null, dor, null);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> getSearchUsers(String search) throws Exception {
        List<User> userList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_search_users(?)}");
        cs.setString(1, search);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String userName = rs.getString("username");
            String userMode = rs.getString("umode");
            String email = rs.getString("email");
            String registerAt = rs.getString("Created_at");
            userList.add(new User(id, userName, null, email, userMode, null, registerAt, null));
        }
        return userList;
    }

    @Override
    public int getUserCount() throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_get_users_count(?)}");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.execute();
        return cs.getInt(1);
    }

    @Override
    public String deleteUser(int id) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_delete_user(?,?)}");
        cs.setInt(1, id);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.execute();
        return cs.getString(2);
    }

}
