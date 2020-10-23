package com.cms.dao.factory;

import com.cms.dao.BlogDAO;
import com.cms.dao.impl.BlogDAOImpl;

/**
 *
 * @author DELL PC
 */
public class BlogDAOFactory {
    public static BlogDAO getInstance(){
          return new BlogDAOImpl();
    }
}
