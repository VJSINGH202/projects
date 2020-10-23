package com.cms.dao.impl;

import com.cms.dao.FeedbackDAO;
import com.cms.db.DBConnection;
import com.cms.entities.Feedback;
import java.sql.*;
import java.util.*;

public class FeedbackDAOImpl implements FeedbackDAO{
  private CallableStatement cs = null;    

    @Override
    public String addFeedback(Feedback feedback) throws Exception {
       cs = DBConnection.connect().prepareCall("{call pro_add_feedback(?,?,?)}");
        cs.setString(1,feedback.getFeed());
        cs.setInt(2, Integer.parseInt(feedback.getUserId()));
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.execute();
        return cs.getString(3);
    }

    @Override
    public List<Feedback> getFeedback() throws Exception {
        List<Feedback> feedList = new ArrayList<>();
       cs = DBConnection.connect().prepareCall("{call pro_get_feedback()}");
      ResultSet rs = cs.executeQuery();
       while(rs.next()){
            String id = rs.getString("id");
            String feed = rs.getString("feed");
            String status = rs.getString("fstatus");
            String feedbackDate = rs.getString("feeddate");
            String userId = rs.getString("userid");
            Feedback feedback = new Feedback(id, feed, userId, feedbackDate, status);
            feedList.add(feedback);
       }
       return feedList;
    }

    @Override
    public String reviewFeedback(int fid) throws Exception {
         cs = DBConnection.connect().prepareCall("{call pro_update_feedback_status(?,?,?)}");
         cs.setString(1, "R");
         cs.setInt(2, fid);
         cs.registerOutParameter(3, Types.VARCHAR);
         cs.execute();
         return cs.getString(3);
    }

    @Override
    public String unReviewFeedback(int fid) throws Exception {
       cs = DBConnection.connect().prepareCall("{call pro_update_feedback_status(?,?,?)}");
         cs.setString(1, "U");
         cs.setInt(2, fid);
         cs.registerOutParameter(3, Types.VARCHAR);
         cs.execute();
         return cs.getString(3);
    }

    @Override
    public String deleteFeeback(int fid) throws Exception {
         cs = DBConnection.connect().prepareCall("{call pro_delete_feedback(?,?)}");
         cs.setInt(1,fid);
         cs.registerOutParameter(2, Types.VARCHAR);
         cs.execute();
         return cs.getString(2);
    }

    @Override
    public int getUserFeedbackCount(int uid) throws Exception {
      cs = DBConnection.connect().prepareCall("{call pro_user_feed_count(?,?)}");
      cs.setInt(1, uid);
      cs.registerOutParameter(2, Types.INTEGER);
      cs.execute();
      return cs.getInt(2);
    }

    @Override
    public int getFeedbackCount() throws Exception {
        cs = DBConnection.connect().prepareCall("{call pro_get_feed_count(?)}");
        cs.registerOutParameter(1, Types.INTEGER);
        cs.execute();
        return cs.getInt(1);
    }

    @Override
    public List<Feedback> getFeedbackReport() throws Exception {
        List<Feedback> feedList = new ArrayList<>();
       cs = DBConnection.connect().prepareCall("{call pro_get_feedback_report()}");
         ResultSet rs = cs.executeQuery();
         while(rs.next()){
            String id = rs.getString("feed_id");
            String feed = rs.getString("feed");
            String status = rs.getString("fstatus");
            String feedbackDate = rs.getString("Created_at");
            String userName = rs.getString("username");
            String userMode = rs.getString("umode");
            Feedback feedback = new Feedback(id, feed, feedbackDate, status, userName, userMode);
            feedList.add(feedback);
       }
       return feedList;
    }
    
}
