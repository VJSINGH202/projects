package com.cms.dao.impl;

import com.cms.dao.AdminDAO;
import com.cms.db.DBConnection;
import com.cms.entities.*;
import java.sql.*;
import java.util.*;

public class AdminDAOImpl implements AdminDAO {

    private CallableStatement cs = null;
    
    @Override
    public String registerAdmin(Admin admin) throws Exception {
        try {
            cs = DBConnection.connect().prepareCall("{call pro_add_admin(?,?,?,?)}");
            cs.setString(1, admin.getAdminName());
            cs.setString(2, admin.getAdminPassword());
            cs.setString(3, admin.getAdminEmail());
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.execute();
            return cs.getString(4);
        } finally {
            closeConnection();
        }        
    }
    
    @Override
    public String loginAdmin(Admin admin) throws Exception {
        try {
            cs = DBConnection.connect().prepareCall("{call pro_check_admin_id(?,?,?)}");
            cs.setString(1, admin.getAdminName());
            cs.setString(2, admin.getAdminPassword());
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
    public String forgotPassword(String email) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_forgot_password(?,?)}");
        cs.setString(1, email);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.execute();
        return cs.getString(2);
    }
    
    @Override
    public String addCategory(Category category) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_add_category(?,?,?)}");
        cs.setString(1, category.getCategoryName());
        cs.setInt(2, Integer.parseInt(category.getUserId()));
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
        return cs.getString(3);
    }
    
    @Override
    public List<Category> getCategoryList() throws Exception {
        List<Category> categoryList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("call pro_get_category()");
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String categoryName = rs.getString("category");
            String createdAt = rs.getString("Created_at");
            String uid = rs.getString("uid");
            Category category = new Category(id, categoryName, createdAt, uid);
            categoryList.add(category);
        }
        return categoryList;
    }
    
    @Override
    public String deleteCategory(int cid) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_delete_category(?,?)}");
        cs.setInt(1, cid);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.execute();
        return cs.getString(2);
    }
    
    @Override
    public List<String> getCategoryName() throws Exception {
        List<String> categoryNameList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_category_name()}");
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String categoryName = rs.getString("name");
            categoryNameList.add(categoryName);
        }
        return categoryNameList;
    }
    
    @Override
    public List<Category> getSearchCategoryName(String searchName) throws Exception {
        List<Category> categoryList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_search_category(?)}");
        cs.setString(1, searchName);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String categoryName = rs.getString("category");
            String createdAt = rs.getString("Created_at");
            String uid = rs.getString("uid");
            Category category = new Category(id, categoryName, createdAt, uid);
            categoryList.add(category);
        }
        return categoryList;
    }

    @Override
    public int getCategoryCount() throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_get_category_count(?)}");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.execute();
        return cs.getInt(1);
    }
}
