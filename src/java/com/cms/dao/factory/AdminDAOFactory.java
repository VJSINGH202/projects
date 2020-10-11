
package com.cms.dao.factory;

import com.cms.dao.AdminDAO;
import com.cms.dao.impl.AdminDAOImpl;

public class AdminDAOFactory {
    public static AdminDAO getInstance()
    {
      return new AdminDAOImpl();
    }
}
