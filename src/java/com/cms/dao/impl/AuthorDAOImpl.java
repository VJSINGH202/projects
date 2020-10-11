package com.cms.dao.impl;

import com.cms.db.DBConnection;
import java.sql.*;
import java.util.*;
import com.cms.dao.AuthorDAO;
import com.cms.entities.Author;
import java.sql.Types;

/**
 *
 * @author DELL PC
 */
public class AuthorDAOImpl implements AuthorDAO{
    private CallableStatement cs = null;

    @Override
    public List<Author> getAllAuthorBlogs(int page,int blimit) throws Exception {
       List<Author> authorBlogList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_all_author_blogs(?,?)}");
        cs.setInt(1, page);
        cs.setInt(2, blimit);
        ResultSet rs = cs.executeQuery();
        while(rs.next())
        {
            String authorId = rs.getString("author_id");
            String authorName = rs.getString("author_name");
            String authorEmail = rs.getString("author_email");
            String authorBlogId = rs.getString("blog_id");
            String authorBlogTitle = rs.getString("blog_title");
            String authorBlogCategory = rs.getString("blog_category");
            String authorBlogContent = rs.getString("blog_content");
            String authorBlogCreatedAt = rs.getString("Created_at");
            Author author = new Author(authorId,authorName,authorEmail,authorBlogId,authorBlogTitle,
                    authorBlogCategory,authorBlogContent,authorBlogCreatedAt);
               authorBlogList.add(author);
        }
        return authorBlogList;
    }

    @Override
    public Author getAuthorBlog(int bid) throws Exception {
         Author author = null;
          cs=DBConnection.connect().prepareCall("call pro_get_author_blog(?)");
          cs.setInt(1, bid);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            String authorId = rs.getString("author_id");
            String authorName = rs.getString("author_name");
            String authorEmail = rs.getString("author_email");
            String authorBiblography = rs.getString("author_biblography");
            String authorBlogId = rs.getString("blog_id");
            String authorBlogTitle = rs.getString("blog_title");
            String authorBlogCategory = rs.getString("blog_category");
            String authorBlogContent = rs.getString("blog_content");
            String authorBlogCreatedAt = rs.getString("Created_at");
                 author=new Author(authorId, authorName, authorEmail,
                   authorBiblography, authorBlogId, authorBlogTitle, authorBlogCategory,
                   authorBlogContent, authorBlogCreatedAt);
        }
        return author;
    }

    @Override
    public List<Author> getAuthorByCategory(String category, int page, int blimit) throws Exception {
       List<Author> authorBlogList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_author_by_category(?,?,?)}");
        cs.setString(1, category);
        cs.setInt(2, page);
        cs.setInt(3, blimit);
        ResultSet rs = cs.executeQuery();
        while(rs.next())
        {
            String authorId = rs.getString("author_id");
            String authorName = rs.getString("author_name");
            String authorEmail = rs.getString("author_email");
            String authorBlogId = rs.getString("blog_id");
            String authorBlogTitle = rs.getString("blog_title");
            String authorBlogCategory = rs.getString("blog_category");
            String authorBlogContent = rs.getString("blog_content");
            String authorBlogCreatedAt = rs.getString("Created_at");
            Author author = new Author(authorId,authorName,authorEmail,authorBlogId,authorBlogTitle,
                    authorBlogCategory,authorBlogContent,authorBlogCreatedAt);
               authorBlogList.add(author);
        }
        return authorBlogList;  
    }

    @Override
    public List<Author> getAuthorBySearch(String search, int page, int blimit) throws Exception {
        List<Author> authorBlogList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_search_author(?,?,?)}");
        cs.setString(1, search);
        cs.setInt(2, page);
        cs.setInt(3, blimit);
        ResultSet rs = cs.executeQuery();
        while(rs.next())
        {
            String authorId = rs.getString("author_id");
            String authorName = rs.getString("author_name");
            String authorEmail = rs.getString("author_email");
            String authorBlogId = rs.getString("blog_id");
            String authorBlogTitle = rs.getString("blog_title");
            String authorBlogCategory = rs.getString("blog_category");
            String authorBlogContent = rs.getString("blog_content");
            String authorBlogCreatedAt = rs.getString("Created_at");
            Author author = new Author(authorId,authorName,authorEmail,authorBlogId,authorBlogTitle,
                    authorBlogCategory,authorBlogContent,authorBlogCreatedAt);
               authorBlogList.add(author);
        }
        return authorBlogList;  
    }

    @Override
    public int getSearchCount(String search) throws Exception {
           cs = DBConnection.connect().prepareCall("{call pro_get_search_count(?,?)}");
           cs.setString(1, search);
           cs.registerOutParameter(2, Types.INTEGER);
           cs.execute();
           return cs.getInt(2);
    }

    @Override
    public List<Author> getAuthorProfileBlog(int uid) throws Exception {
        List<Author> authorBlogList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_author_profile_blogs(?)}");
        cs.setInt(1, uid);
        ResultSet rs = cs.executeQuery();
        while(rs.next())
        {
            String authorId = rs.getString("author_id");
            String authorName = rs.getString("author_name");
            String authorEmail = rs.getString("author_email");
            String authorBlogId = rs.getString("blog_id");
            String authorBlogTitle = rs.getString("blog_title");
            String authorBlogCategory = rs.getString("blog_category");
            String authorBlogContent = rs.getString("blog_content");
            String authorBlogCreatedAt = rs.getString("Created_at");
            Author author = new Author(authorId,authorName,authorEmail,authorBlogId,authorBlogTitle,
                    authorBlogCategory,authorBlogContent,authorBlogCreatedAt);
               authorBlogList.add(author);
        }
        return authorBlogList;  
    }

    @Override
    public List<Author> getBlogReport() throws Exception {
        List<Author> authorBlogList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_blogs_report()}");
        ResultSet rs = cs.executeQuery();
        while(rs.next())
        {
            String authorName = rs.getString("author_name");
            String authorEmail= rs.getString("author_email");
            String authorBlogId = rs.getString("blog_id");
            String authorBlogTitle = rs.getString("blog_title");
            String authorBlogCategory = rs.getString("blog_category");
            String authorBlogCreatedAt = rs.getString("Created_at");
            Author author = new Author(authorName, authorEmail, authorBlogId, authorBlogTitle, authorBlogCategory, authorBlogCreatedAt);
               authorBlogList.add(author);
        }
        return authorBlogList;
    }
    
}
