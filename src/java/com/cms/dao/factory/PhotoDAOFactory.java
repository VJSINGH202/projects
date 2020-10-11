
package com.cms.dao.factory;

import com.cms.dao.PhotoDao;
import com.cms.dao.impl.PhotoDAOImpl;

public class PhotoDAOFactory {
    public static PhotoDao getInstance()
    {
         return new PhotoDAOImpl();
    }
}
