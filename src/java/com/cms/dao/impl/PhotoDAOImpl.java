package com.cms.dao.impl;

import com.cms.dao.PhotoDao;
import com.cms.db.DBConnection;
import com.cms.entities.Photo;
import java.sql.*;

public class PhotoDAOImpl implements PhotoDao{
     private CallableStatement cs = null;
    @Override
    public String addUserPhoto(Photo photo) throws Exception {
          cs = DBConnection.connect().prepareCall("{call pro_add_user_photo(?,?,?)}");
          cs.setInt(1, photo.getUserId());
          cs.setBinaryStream(2, photo.getPhoto().getInputStream(),(int)photo.getPhoto().getSize());
          cs.registerOutParameter(3, Types.VARCHAR);
          cs.execute();
          return cs.getString(3);
    }

    @Override
    public byte[] getUserPhoto(int uid) throws Exception {
          byte data[] = null;
          cs = DBConnection.connect().prepareCall("{call pro_get_user_photo(?)}");
          cs.setInt(1, uid);
         ResultSet rs = cs.executeQuery();
         if(rs.next()){
             Blob b = rs.getBlob(1);
              data = new byte[(int)b.length()];
              data = b.getBytes(1, data.length); 
         }
         return data;
     }

    @Override
    public int checkUserPhoto(int uid) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_check_user_photo(?,?)}");
        cs.setInt(1, uid);
        cs.registerOutParameter(2, Types.INTEGER);
        cs.execute();
        return cs.getInt(2);
    }

    @Override
    public String deletePhoto(int uid) throws Exception {
       cs = DBConnection.connect().prepareCall("{call pro_delete_user_photo(?,?)}");
       cs.setInt(1, uid);
       cs.registerOutParameter(2, Types.VARCHAR);
       cs.execute();
       return cs.getString(2);
    }
}
