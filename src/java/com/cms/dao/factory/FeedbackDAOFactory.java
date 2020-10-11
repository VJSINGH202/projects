
package com.cms.dao.factory;

import com.cms.dao.FeedbackDAO;
import com.cms.dao.impl.FeedbackDAOImpl;

public class FeedbackDAOFactory {
   public static FeedbackDAO getInstance()
   {
     return new FeedbackDAOImpl();
   }
}
