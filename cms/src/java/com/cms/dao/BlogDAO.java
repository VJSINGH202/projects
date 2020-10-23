package com.cms.dao;

import com.cms.entities.Blog;
import java.util.List;

/**
 *
 * @author DELL PC
 */
public interface BlogDAO {
    public String addBlog(Blog blog)throws Exception;
    public byte[] getblogPhoto(int bid) throws Exception;
    public List<Blog> getUserBlog(int uid) throws Exception;
    public Blog getBlog(int bid)throws Exception;
    public String updateBlog(Blog blog)throws Exception;
    public int blogCount()throws Exception;
    public int blogCountByCategory(String categoryName)throws Exception;
    public String deleteBlog(int id)throws Exception;
    public int getUserBlogCount(int uid)throws Exception;
}
