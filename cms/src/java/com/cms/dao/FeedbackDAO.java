package com.cms.dao;

import com.cms.entities.Feedback;
import java.util.List;

/**
 *
 * @author DELL PC
 */
public interface FeedbackDAO {
    public String addFeedback(Feedback feedback) throws Exception;
    public List<Feedback> getFeedback() throws Exception;
    public String reviewFeedback(int fid) throws Exception;
    public String unReviewFeedback(int fid) throws Exception;
    public String deleteFeeback(int fid) throws Exception;
    public int getUserFeedbackCount(int uid)throws Exception;
    public int getFeedbackCount()throws Exception;
    public List<Feedback> getFeedbackReport() throws Exception;
            
}
