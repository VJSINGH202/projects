package com.cms.dao.impl;

import com.cms.dao.CommentDAO;
import com.cms.db.DBConnection;
import com.cms.entities.Comment;
import java.sql.*;
import java.util.*;

/**
 *
 * @author DELL PC
 */
public class CommentDAOImpl implements CommentDAO {

    private CallableStatement cs = null;

    @Override
    public String addComment(Comment comment) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_add_comment(?,?,?,?)}");
        cs.setString(1, comment.getCommentDescription());
        cs.setInt(2, comment.getUserId());
        cs.setInt(3, comment.getBlogId());
        cs.registerOutParameter(4, Types.VARCHAR);
        cs.execute();
        return cs.getString(4);
    }

    @Override
    public List<Comment> getComment() throws Exception {
        List<Comment> commentList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_comment()}");
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            String description = rs.getString("description");
            String status = rs.getString("cstatus");
            String blogId = rs.getString("bid");
            String userId = rs.getString("userid");
            Comment comment = new Comment(id, description, status, Integer.parseInt(blogId), Integer.parseInt(userId));
            commentList.add(comment);
        }
        return commentList;
    }

    @Override
    public String approveComment(int cid) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_update_comment_status(?,?,?)}");
        cs.setString(1, "A");
        cs.setInt(2, cid);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
        return cs.getString(3);
    }

    @Override
    public String unApprovedComment(int cid) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_update_comment_status(?,?,?)}");
        cs.setString(1, "U");
        cs.setInt(2, cid);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
        return cs.getString(3);
    }

    @Override
    public String deleteComment(int cid) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_delete_comment(?,?)}");
        cs.setInt(1, cid);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.execute();
        return cs.getString(2);
    }

    @Override
    public List<Comment> getBlogComments(int bid) throws Exception {
        List<Comment> getBlogCommentList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_blog_comments(?)}");
        cs.setInt(1, bid);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String commentDate = rs.getString("Comment_at");
            String comment = rs.getString("user_comment");
            Comment c = new Comment(comment, commentDate, username, uid);
            getBlogCommentList.add(c);
        }
        return getBlogCommentList;
    }

    @Override
    public int getUserCommentCount(int uid) throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_user_comment_count(?,?)}");
        cs.setInt(1, uid);
        cs.registerOutParameter(2, Types.INTEGER);
        cs.execute();
        return cs.getInt(2);
    }

    @Override
    public int getCommemtCount() throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_get_comments_count(?)}");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.execute();
        return cs.getInt(1);
    }

    @Override
    public List<Comment> getCommentReport() throws Exception {
        List<Comment> commentList = new ArrayList<>();
        cs = DBConnection.connect().prepareCall("{call pro_get_comment_report}");
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            String id = rs.getString("comment_id");
            String description = rs.getString("description");
            String status = rs.getString("cstatus");
            String commentDate = rs.getString("Created_at");
            String userName = rs.getString("username");
            String userMode = rs.getString("umode");
            Comment comment = new Comment(id, description, commentDate, status, userName, userMode);
            commentList.add(comment);
        }
        return commentList;
    }

}
