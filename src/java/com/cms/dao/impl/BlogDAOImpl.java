
package com.cms.dao.impl;

import com.cms.dao.BlogDAO;
import com.cms.db.DBConnection;
import com.cms.entities.Blog;
import java.sql.*;
import java.util.*;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author DELL PC
 */
public class BlogDAOImpl implements BlogDAO{
    CallableStatement cs = null;
    @Override
    public String addBlog(Blog blog) throws Exception {
           cs= DBConnection.connect().prepareCall("{call pro_add_blog(?,?,?,?,?,?)}");
           cs.setString(1, blog.getBlogTitle());
           cs.setString(2, blog.getBlogCatagory());
           cs.setBinaryStream(3, blog.getBlogPhoto().getInputStream(),(int)blog.getBlogPhoto().getSize());
           cs.setString(4, blog.getBlogContent());
           cs.setInt(5, Integer.parseInt(blog.getUserid()));
           cs.registerOutParameter(6, Types.VARCHAR);
           cs.execute();
           return cs.getString(6);
    }

     @Override
    public String updateBlog(Blog blog) throws Exception {
         FileItem blogPhoto = null;
        cs= DBConnection.connect().prepareCall("{call pro_update_blog(?,?,?,?,?,?,?)}");
           cs.setInt(1, blog.getBlogId());
           cs.setString(2, blog.getBlogTitle());
           cs.setString(3, blog.getBlogCatagory());
                blogPhoto = blog.getBlogPhoto();
                //blogPhoto.toString().startsWith("name=, ")
           if(blogPhoto.getName().trim().equals("")){
               cs.setBinaryStream(4, null,0);
               cs.setString(6,"false");
           }else{
               cs.setBinaryStream(4, blog.getBlogPhoto().getInputStream(),(int)blog.getBlogPhoto().getSize());
               cs.setString(6,"true");
           }
           cs.setString(5, blog.getBlogContent());
           cs.registerOutParameter(7, Types.VARCHAR);
           cs.execute();
           return cs.getString(7);
    }
    
    @Override
    public byte[] getblogPhoto(int bid) throws Exception {
         byte data[] = null;
           cs = DBConnection.connect().prepareCall("{call pro_get_blog_photo(?)}");
           cs.setInt(1, bid);
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
             Blob b = rs.getBlob(1);
              data = new byte[(int)b.length()];
              data = b.getBytes(1, data.length); 
         }
         return data;
    }

    @Override
    public List<Blog> getUserBlog(int uid) throws Exception {
        List<Blog> blogList = new ArrayList<>();
          cs =DBConnection.connect().prepareCall("{call pro_get_user_blog(?)}");
          cs.setInt(1, uid);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String category = rs.getString("category");
            Blog blog = new Blog(id, title, category);
            blogList.add(blog);
        }
        return blogList;
    }

    @Override
    public Blog getBlog(int bid) throws Exception {
        Blog blog = null;
         cs=DBConnection.connect().prepareCall("{call pro_get_blog(?)}");
         cs.setInt(1, bid);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
             int id = rs.getInt("id");
             String title = rs.getString("title");
             String category = rs.getString("category");
             String bcontent = rs.getString("bcontent");
             String dop = rs.getString("dop");
             String userid = rs.getString("userid");
             blog = new Blog(bid, title, category, bcontent, dop, userid);
        }
        return blog;
    }

    @Override
    public int blogCount() throws Exception {
           cs= DBConnection.connect().prepareCall("{call pro_get_blog_count(?)}");
           cs.registerOutParameter(1, Types.INTEGER);
           cs.execute();
           return cs.getInt(1);
    }

    @Override
    public String deleteBlog(int id) throws Exception {
           cs = DBConnection.connect().prepareCall("{call pro_delete_blog(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            return cs.getString(2);
    }

    @Override
    public int blogCountByCategory(String categoryName) throws Exception {
           cs = DBConnection.connect().prepareCall("{call pro_get_blog_count_category(?,?)}");
           cs.setString(1, categoryName);
           cs.registerOutParameter(2, Types.INTEGER);
           cs.execute();
           return cs.getInt(2);
    }

    @Override
    public int getUserBlogCount(int uid) throws Exception {
       cs= DBConnection.connect().prepareCall("{call pro_user_blog_count(?,?)}");
       cs.setInt(1, uid);
           cs.registerOutParameter(2, Types.INTEGER);
           cs.execute();
           return cs.getInt(2);
    }
    
}
