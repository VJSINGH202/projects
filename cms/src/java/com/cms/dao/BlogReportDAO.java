package com.cms.dao;

import com.cms.entities.*;
import java.util.List;

/**
 *
 * @author DELL PC
 */
public interface BlogReportDAO {
    String addReport(Author author)throws Exception;
    String addFavouriteBlog(Author author)throws Exception;
    String removeFavouriteBlog(int rid)throws Exception;
    String addLike(Author author)throws Exception;
    String removeLike(int lid)throws Exception;
    List<Report> getReportList() throws Exception;
    String doneReportStatus(int rid) throws Exception;
    String unDoneRepoetStatus(int rid) throws Exception;
    String deleteReport(int rid)throws Exception;
    List<Like> getLikeBlog(int uid)throws Exception;
    List<FavouriteBlog> getFavouriteBlog(int uid)throws Exception;
    List<Blog> getMostLikeBlog()throws Exception;
    List<Blog> getMostReportedBlog()throws Exception;
    int getUserLikeCount(int uid)throws Exception;
}
