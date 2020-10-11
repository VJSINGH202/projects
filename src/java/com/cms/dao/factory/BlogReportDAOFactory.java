package com.cms.dao.factory;

import com.cms.dao.BlogReportDAO;
import com.cms.dao.impl.BlogReportDAOImpl;

/**
 *
 * @author DELL PC
 */
public class BlogReportDAOFactory {
   public static BlogReportDAO getInstance()
   {
    return new BlogReportDAOImpl();
   }
}