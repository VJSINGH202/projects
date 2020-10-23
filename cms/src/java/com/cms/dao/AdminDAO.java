package com.cms.dao;

import com.cms.entities.*;
import java.util.List;

public interface AdminDAO {
    public String registerAdmin(Admin admin)throws Exception;
    public String loginAdmin(Admin admin)throws Exception;
    public String forgotPassword(String email)throws Exception;
    public String addCategory(Category category)throws Exception;
    public List<Category> getCategoryList()throws Exception;
    public String deleteCategory(int cid)throws Exception;
    public List<String> getCategoryName()throws Exception;
    public List<Category> getSearchCategoryName(String searchName)throws Exception;
    public int  getCategoryCount()throws Exception;
}
