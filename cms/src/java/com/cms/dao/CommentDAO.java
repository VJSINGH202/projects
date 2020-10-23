package com.cms.dao;

import com.cms.entities.Comment;
import java.util.List;

/**
 *
 * @author DELL PC
 */
public interface CommentDAO {
    public String addComment(Comment comment)throws Exception;
    public List<Comment> getComment()throws Exception ;
    public String approveComment(int cid) throws Exception;
    public String unApprovedComment(int cid) throws Exception;
    public String deleteComment(int cid)throws Exception;
    public List<Comment> getBlogComments(int bid)throws Exception;
    public int getUserCommentCount(int uid)throws Exception;
    public int getCommemtCount()throws Exception;
    public List<Comment> getCommentReport() throws Exception;
}
