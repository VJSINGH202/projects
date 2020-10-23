package com.cms.dao;

import com.cms.entities.Author;
import java.util.List;
/**
 *
 * @author DELL PC
 */
public interface AuthorDAO {
    public List<Author> getAllAuthorBlogs(int page,int blimit)throws Exception;
    public Author getAuthorBlog(int bid)throws Exception;
    public List<Author> getAuthorByCategory(String category,int page,int blimit)throws Exception;
    public List<Author> getAuthorBySearch(String search,int page,int blimit)throws Exception;
    public int getSearchCount(String search)throws Exception;
    public List<Author> getAuthorProfileBlog(int uid) throws Exception;
    public List<Author> getBlogReport() throws Exception;
}
