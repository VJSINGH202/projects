package com.cms.dao.impl;

import com.cms.dao.BlogReportDAO;
import com.cms.db.DBConnection;
import com.cms.entities.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author DELL PC
 */
public class BlogReportDAOImpl implements BlogReportDAO{
    private CallableStatement cs = null;
    @Override
    public String addReport(Author author) throws Exception{
        cs=DBConnection.connect().prepareCall("{call pro_add_report(?,?,?)}");
        cs.setInt(1, Integer.parseInt(author.getAuthorId()));
        cs.setInt(2, Integer.parseInt(author.getAuthorBlogId()));
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
     return cs.getString(3);  
    }

    @Override
    public String addFavouriteBlog(Author author) throws Exception {
           
        cs=DBConnection.connect().prepareCall("{call pro_add_favourite_blog(?,?,?)}");
        cs.setInt(1, Integer.parseInt(author.getAuthorId()));
        cs.setInt(2, Integer.parseInt(author.getAuthorBlogId()));
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
     return cs.getString(3);     
    }

    @Override
    public String removeFavouriteBlog(int rid) throws Exception {
       cs = DBConnection.connect().prepareCall("{call pro_delete_favouriteblog(?,?)}");
       cs.setInt(1, rid);
       cs.registerOutParameter(2, Types.VARCHAR);
       cs.execute();
       return cs.getString(2);
    }

    @Override
    public String addLike(Author author) throws Exception {
        cs=DBConnection.connect().prepareCall("{call pro_add_like(?,?,?)}");
        cs.setInt(1, Integer.parseInt(author.getAuthorId()));
        cs.setInt(2, Integer.parseInt(author.getAuthorBlogId()));
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
     return cs.getString(3); 
    }

    @Override
    public String removeLike(int lid) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_delete_like(?,?)}");
       cs.setInt(1, lid);
       cs.registerOutParameter(2, Types.VARCHAR);
       cs.execute();
       return cs.getString(2);
    }

    @Override
    public List<Report> getReportList() throws Exception {
        List<Report> reportList = new ArrayList<>();
       cs = DBConnection.connect().prepareCall("{call pro_get_report()}");
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String userId = rs.getString("user_id");
            String blogId = rs.getString("blog_id");
            String reportDate = rs.getString("report_date");
            String reportStatus = rs.getString("report_status");
            Report report = new Report(id, userId, blogId, reportStatus, reportDate);
            reportList.add(report);
        }
        return reportList;
    }

    @Override
    public String doneReportStatus(int rid) throws Exception {
        cs =DBConnection.connect().prepareCall("{call pro_update_report_status(?,?,?)}");
        cs.setString(1, "D");
        cs.setInt(2, rid);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
        return cs.getString(3);
    }

    @Override
    public String unDoneRepoetStatus(int rid) throws Exception {
       cs =DBConnection.connect().prepareCall("{call pro_update_report_status(?,?,?)}");
        cs.setString(1, "U");
        cs.setInt(2, rid);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
        return cs.getString(3);
    }

    @Override
    public String deleteReport(int rid) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_delete_report(?,?)}");
        cs.setInt(1, rid);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.execute();
        return cs.getString(2);
    }

    @Override
    public List<Like> getLikeBlog(int uid) throws Exception {
        List<Like> likeList = new ArrayList<>();
        cs= DBConnection.connect().prepareCall("{call pro_user_like_blog(?)}");
        cs.setInt(1, uid);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String title = rs.getString("title");
            String category = rs.getString("category");
            String LikeDate = rs.getString("Liked_at");
            String LikeId = rs.getString("lid");
            String BlogId = rs.getString("bid");
            Like like = new Like(LikeId, title, category, LikeDate, BlogId);
            likeList.add(like);
        }
        return likeList;
    }

    @Override
    public List<FavouriteBlog> getFavouriteBlog(int uid) throws Exception {
       List<FavouriteBlog> favouriteBlogList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_user_favourite_blog(?)}");
        cs.setInt(1, uid);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
           String favouriteBlogTitle = rs.getString("title");
           String favouriteBlogCategory = rs.getString("category");
           String favouriteBlogDateAdded = rs.getString("Added_at");
           String favouriteBlogId = rs.getString("fid");
           String BlogId = rs.getString("bid");
           FavouriteBlog favouriteBlog = new FavouriteBlog(favouriteBlogId, favouriteBlogTitle,
                   favouriteBlogCategory, favouriteBlogDateAdded, BlogId);
              favouriteBlogList.add(favouriteBlog);
        }
        return favouriteBlogList;
    }

    @Override
    public List<Blog> getMostLikeBlog() throws Exception {
        List<Blog> likeBlogList = new ArrayList<>();
        cs= DBConnection.connect().prepareCall("{call pro_most_like_blog()}");
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            int bid = rs.getInt("bid");
            String title = rs.getString("title");
            String category = rs.getString("category");
            String created_At = rs.getString("Created_at");
            String uid = rs.getString("uid");
            int likeCount = rs.getInt("likes");
           Blog blog = new Blog(bid, title, category, created_At, likeCount, uid);
           likeBlogList.add(blog);
        }
        return likeBlogList;
    }

    @Override
    public List<Blog> getMostReportedBlog() throws Exception {
        List<Blog> reportedBlogList = new ArrayList<>();
        cs= DBConnection.connect().prepareCall("{call pro_most_reported_blog()}");
        ResultSet rs = cs.executeQuery();
        while(rs.next())
        {
            int bid = rs.getInt("bid");
            String blogTitle = rs.getString("title");
            String blogCatagory = rs.getString("category");
            String dop = rs.getString("Created_at");
            String userid = rs.getString("uid");
            int reportCount = rs.getInt("report");
            Blog blog = new Blog(bid, blogTitle, blogCatagory, dop, reportCount, userid);
            reportedBlogList.add(blog);
        }
        return reportedBlogList;
    }

    @Override
    public int getUserLikeCount(int uid) throws Exception {
       cs =DBConnection.connect().prepareCall("{call pro_user_likes_count(?,?)}");
       cs.setInt(1, uid);
       cs.registerOutParameter(2, Types.INTEGER);
       cs.execute();
       return cs.getInt(2);
    }

}
