package com.cms.dao.factory;

import com.cms.dao.CommentDAO;
import com.cms.dao.impl.CommentDAOImpl;

/**
 *
 * @author DELL PC
 */
public class CommentDAOFactory {
    public static CommentDAO getInstance(){
        return new CommentDAOImpl();
    }
}
