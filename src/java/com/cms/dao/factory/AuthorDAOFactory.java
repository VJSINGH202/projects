package com.cms.dao.factory;

import com.cms.dao.impl.AuthorDAOImpl;

/**
 *
 * @author DELL PC
 */
public class AuthorDAOFactory {
     public static AuthorDAOImpl getInstance(){
          return new AuthorDAOImpl();
     }
}
