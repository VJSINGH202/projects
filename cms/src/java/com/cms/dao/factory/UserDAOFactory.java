package com.cms.dao.factory;

import com.cms.dao.UserDAO;
import com.cms.dao.impl.UserDAOImpl;

public class UserDAOFactory {
    public static UserDAO getInstance(){
        return new UserDAOImpl();
    }
}
