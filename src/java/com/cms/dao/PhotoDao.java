
package com.cms.dao;

import com.cms.entities.Photo;

public interface PhotoDao {
   public String addUserPhoto(Photo photo)throws Exception;
   public byte[] getUserPhoto(int uid) throws Exception;
   public int checkUserPhoto(int uid) throws Exception;
   public String deletePhoto(int uid)throws Exception;
}
