package com.cms.controllers;

import com.cms.dao.factory.*;
import com.cms.entities.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    String msg;
    int checkUserPhoto;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String link = req.getParameter("url");
            List<Blog> userBlog;
            List<String> categoryNames;
            if (link == null) {
                link = "home";
            }
            switch (link) {
                case "dashboard":
                    if (req.getSession().getAttribute("uid") != null) {
                        String uid = (String) req.getSession().getAttribute("uid");
                        int dashboardFeedCount = getUserFeedCount(uid);
                        int dashboardLikeCount = getUserLikeCount(uid);
                        int dashboardBlogCount = getUserBlogCount(uid);
                        int dashboardComments = getUserComments(uid);
                        req.setAttribute("dashboardcomments", dashboardComments);
                        req.setAttribute("dashboardblogcount", dashboardBlogCount);
                        req.setAttribute("dashboardlikecount", dashboardLikeCount);
                        req.setAttribute("dashboardfeedcount", dashboardFeedCount);
                    }
                    if (req.getSession().getAttribute("mode").equals("admin")) {
                        int userCount = getUserCount();
                        int blogCount = blogCount(req, resp);
                        int feedBackCount = getFeedBackCount();
                        int commentCount = getCommentCount();
                        int categoryCount = getCategoryCount();
                        req.setAttribute("usercount", userCount);
                        req.setAttribute("blogcount", blogCount);
                        req.setAttribute("feedbackcount", feedBackCount);
                        req.setAttribute("commentcount", commentCount);
                        req.setAttribute("categorycount", categoryCount);
                    }
                    req.setAttribute("pagetitle", "Dashboard");
                    req.setAttribute("link", "dashboard");
                    req.getRequestDispatcher("views/dashboard.jsp").forward(req, resp);
                    break;
                case "forgot":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    req.setAttribute("pagetitle", "Forgot Password");
                    req.getRequestDispatcher("views/forgotPassword.jsp").forward(req, resp);
                    break;
                case "cmsreport":
                    String report = req.getParameter("report");
                    if (report != null) {
                        switch (report) {
                            case "user":
                                List<User> allUsers = getAllUsers(req, resp);
                                req.setAttribute("userreport", allUsers);
                                req.setAttribute("pagetitle", "User Report :-");
                                req.getRequestDispatcher("views/CMSReport.jsp").forward(req, resp);
                                break;
                            case "blog":
                                List<Author> blogReports = getBlogReports(req, resp);
                                req.setAttribute("blogreport", blogReports);
                                req.setAttribute("pagetitle", "Blog Report :-");
                                req.getRequestDispatcher("views/CMSReport.jsp").forward(req, resp);
                                break;
                            case "feedback":
                                System.out.println("com.cms.controllers.FrontController inside"+report);
                                List<Feedback> feedbackReport = getFeedbackReport(req, resp);
                                req.setAttribute("feedbackReport", feedbackReport);
                                req.setAttribute("pagetitle", "Feedback Report :-");
                                req.getRequestDispatcher("views/CMSReport.jsp").forward(req, resp);
                                System.out.println("com.cms.controllers.FrontController inside"+report);
                                break;
                            case "comment":
                                List<Comment> commentReport = getCommentReport(req, resp);
                                req.setAttribute("commentReport", commentReport);
                                req.setAttribute("pagetitle", "Comment Report :-");
                                req.getRequestDispatcher("views/CMSReport.jsp").forward(req, resp);
                            default:
                                break;
                        }
                    } else {
                        req.setAttribute("pagetitle", "CMS Report :-");
                        req.getRequestDispatcher("views/CMSReport.jsp").forward(req, resp);
                    }
                    break;
                case "authorprofile":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    if (req.getParameter("uid") != null) {
                        String uid = req.getParameter("uid");
                        List<Author> authorProfileBlogList = getAuthorProfileBlogList(req, resp);
                        User getUserInfo = getUserInfo(req, resp);
                        int userFeedCount = getUserFeedCount(uid);
                        int userLikeCount = getUserLikeCount(uid);
                        int userBlogCount = getUserBlogCount(uid);
                        int userComments = getUserComments(uid);
                        req.setAttribute("uid", req.getParameter("uid"));
                        req.setAttribute("usercomments", userComments);
                        req.setAttribute("userblogcount", userBlogCount);
                        req.setAttribute("userlikecount", userLikeCount);
                        req.setAttribute("userfeedcount", userFeedCount);
                        req.setAttribute("getuserinfo", getUserInfo);
                        req.setAttribute("authorbloglist", authorProfileBlogList);
                    }
                    req.setAttribute("pagetitle", "Author Blog");
                    req.getRequestDispatcher("views/AuthorProfile.jsp").forward(req, resp);
                    break;
                case "category":
                    if (req.getParameter("search") != null) {
                        List<Category> searchCategory = getSearchCategory(req, resp);
                        req.setAttribute("categorylist", searchCategory);
                        req.setAttribute("search", req.getParameter("search"));
                    } else {
                        List categoryList = getCategoryList();
                        req.setAttribute("categorylist", categoryList);
                    }
                    req.setAttribute("pagetitle", "Category");
                    req.setAttribute("link", "category");
                    req.getRequestDispatcher("views/Category.jsp").forward(req, resp);
                    break;
                case "profile":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    User userInfo = getUserInfo(req, resp);
                    checkUserPhoto = checkUserPhoto(req, resp);
                    req.setAttribute("photo", checkUserPhoto);
                    req.setAttribute("userinfo", userInfo);
                    req.setAttribute("pagetitle", "Profile");
                    req.setAttribute("link", "profile");
                    req.getRequestDispatcher("views/profile.jsp").forward(req, resp);
                    break;
                case "comment":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    List<Comment> commentList = commentList(req, resp);
                    req.setAttribute("commentlist", commentList);
                    req.setAttribute("pagetitle", "Comment");
                    req.setAttribute("link", "comment");
                    req.getRequestDispatcher("views/Comment.jsp").forward(req, resp);
                    break;
                case "feedback":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    String mode = (String) req.getSession().getAttribute("mode");
                    if (mode.equals("admin")) {
                        List<Feedback> feedbackList = getFeedback(req, resp);
                        req.setAttribute("pagetitle", "User Feedback");
                        req.setAttribute("feedlist", feedbackList);
                    }
                    req.setAttribute("pagetitle", "Feedback");
                    req.setAttribute("link", "feedback");
                    req.getRequestDispatcher("views/feedback.jsp").forward(req, resp);
                    break;
                case "home":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    if (req.getParameter("category") != null) {
                        List<Author> authorByCategory = getAuthorByCategory(req, resp, 5);
                        int blogCountByCategory = blogCountByCategory(req, resp);
                        req.setAttribute("authorbloglist", authorByCategory);
                        req.setAttribute("blogcount", blogCountByCategory);
                        req.setAttribute("category", req.getParameter("category"));

                    } else if (req.getParameter("search") != null) {
                        List<Author> authorBySearch = getAuthorBySearch(req, resp, 5);
                        int blogCountBySearch = blogCountBySearch(req, resp);
                        req.setAttribute("authorbloglist", authorBySearch);
                        req.setAttribute("blogcount", blogCountBySearch);
                        req.setAttribute("search", req.getParameter("search"));
                    } else {
                        List<Author> allAuthorBlogs = getAllAuthorBlogs(req, resp, 5);
                        int blogCount = blogCount(req, resp);
                        req.setAttribute("authorbloglist", allAuthorBlogs);
                        req.setAttribute("blogcount", blogCount);
                    }
                    req.setAttribute("pagetitle", "Home");
                    categoryNames = getCategoryName();
                    req.setAttribute("categoryName", categoryNames);
                    req.getRequestDispatcher("views/home.jsp").forward(req, resp);
                    break;
                case "search":
                    if (req.getParameter("search").trim() != null) {
                        int blimit = 8;
                        List<Author> authorBySearch = getAuthorBySearch(req, resp, blimit);
                        int blogCountBySearch = blogCountBySearch(req, resp);
                        req.setAttribute("authorbloglist", authorBySearch);
                        req.setAttribute("blogcount", blogCountBySearch);
                        req.setAttribute("search", req.getParameter("search"));
                        req.setAttribute("pagetitle", "Search for - " + req.getParameter("search"));
                        req.setAttribute("blimit", blimit);
                        req.setAttribute("link", "dashboard");
                        req.getRequestDispatcher("views/Search.jsp").forward(req, resp);
                    }
                    break;
                case "blog":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    userBlog = getUserBlog(req, resp);
                    List<String> categoryName = getCategoryName();
                    req.setAttribute("categoryname", categoryName);
                    req.setAttribute("userblog", userBlog);
                    req.setAttribute("pagetitle", "Blog");
                    req.setAttribute("link", "blog");
                    req.getRequestDispatcher("views/blog.jsp").forward(req, resp);
                    break;
                case "editpost":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    Blog blog = getBlog(req, resp);
                    categoryNames = getCategoryName();
                    req.setAttribute("blog", blog);
                    req.setAttribute("categoryname", categoryNames);
                    req.setAttribute("pagetitle", "Edit Post");
                    req.setAttribute("link", "blog");
                    req.getRequestDispatcher("views/editpost.jsp").forward(req, resp);
                    break;
                case "blogreport":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    List<Report> blogReport = getBlogReport(req, resp);
                    req.setAttribute("reportList", blogReport);
                    req.setAttribute("pagetitle", "Blog Report");
                    req.setAttribute("link", "blogreport");
                    req.getRequestDispatcher("views/blogReport.jsp").forward(req, resp);
                    break;
                case "likeblog":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    List<Like> likedBlog = getLikedBlog(req, resp);
                    List<String> categoryName1 = getCategoryName();
                    req.setAttribute("categoryname", categoryName1);
                    req.setAttribute("likelist", likedBlog);
                    req.setAttribute("pagetitle", "Liked Blogs");
                    req.setAttribute("link", "blog");
                    req.getRequestDispatcher("views/likeblog.jsp").forward(req, resp);
                    break;
                case "favouriteblog":
                    msg = (String) req.getAttribute("msg");
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    List<FavouriteBlog> favouriteBlog = getFavouriteBlog(req, resp);
                    List<String> categoryName2 = getCategoryName();
                    req.setAttribute("categoryname", categoryName2);
                    req.setAttribute("favouriteblog", favouriteBlog);
                    req.setAttribute("pagetitle", "Favourite Blogs");
                    req.setAttribute("link", "blog");
                    req.getRequestDispatcher("views/favouriteblog.jsp").forward(req, resp);
                    break;
                case "user":
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    if (req.getParameter("search") != null) {
                        List<User> searchUsers = getSearchUsers(req, resp);
                        req.setAttribute("search", req.getParameter("search"));
                        req.setAttribute("userlist", searchUsers);
                    } else {
                        List<User> allUsers = getAllUsers(req, resp);
                        req.setAttribute("userlist", allUsers);
                    }
                    req.setAttribute("pagetitle", "Users");
                    req.setAttribute("link", "user");
                    req.getRequestDispatcher("views/Users.jsp").forward(req, resp);
                    break;
                case "userblog":
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    if (req.getParameter("search") != null) {
                        List<Author> authorBySearch = getAuthorBySearch(req, resp, 10);
                        req.setAttribute("authorbloglist", authorBySearch);
                        int blogCountBySearch = blogCountBySearch(req, resp);
                        req.setAttribute("blogcount", blogCountBySearch);
                        req.setAttribute("search", req.getParameter("search"));
                    } else {
                        int blogCount1 = blogCount(req, resp);
                        List<Author> allAuthorBlogs = getAllAuthorBlogs(req, resp, 10);
                        req.setAttribute("authorbloglist", allAuthorBlogs);
                        req.setAttribute("blogcount", blogCount1);
                    }
                    req.setAttribute("pagetitle", "Users Blogs");
                    req.setAttribute("link", "user");
                    req.getRequestDispatcher("views/Userblog.jsp").forward(req, resp);
                    break;
                case "mostlikedblog":
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    List<Blog> mostLikedBlog = getMostLikedBlog(req, resp);
                    req.setAttribute("mostlikedblog", mostLikedBlog);
                    req.setAttribute("pagetitle", "Most Liked Blog");
                    req.setAttribute("link", "user");
                    req.getRequestDispatcher("views/MostLikeBlog.jsp").forward(req, resp);
                    break;
                case "mostreportedblog":
                    if (msg != null) {
                        req.setAttribute("msg", msg);
                    }
                    List<Blog> mostReportedBlog = getMostReportedBlog(req, resp);
                    req.setAttribute("mostreportedblog", mostReportedBlog);
                    req.setAttribute("pagetitle", "Most Reported Blog");
                    req.setAttribute("link", "user");
                    req.getRequestDispatcher("views/MostReportedBlog.jsp").forward(req, resp);
                    break;
                default:

                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private User getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int uid;
        if (req.getParameter("uid") != null) {
            return UserDAOFactory.getInstance().getUser(Integer.parseInt(req.getParameter("uid")));
        } else {
            uid = Integer.parseInt((String) req.getSession().getAttribute("uid"));
            return UserDAOFactory.getInstance().getUser(uid);
        }
    }

    private int checkUserPhoto(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uid = (String) req.getSession().getAttribute("uid");
        int userid = Integer.parseInt(uid);
        return PhotoDAOFactory.getInstance().checkUserPhoto(userid);
    }

    private List<Blog> getUserBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uid = (String) req.getSession().getAttribute("uid");
        int userid = Integer.parseInt(uid);
        return BlogDAOFactory.getInstance().getUserBlog(userid);
    }

    private Blog getBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String blogId = req.getParameter("bid");
        int bid = Integer.parseInt(blogId);
        return BlogDAOFactory.getInstance().getBlog(bid);
    }

    private List<Author> getAllAuthorBlogs(HttpServletRequest req, HttpServletResponse resp, int blimit) throws Exception {
        String page = req.getParameter("page");
        int intPage = 0;
        if (page == null || page.trim() == null || page.equals("1")) {
            page = "0";
            intPage = Integer.parseInt(page);
            if (intPage == 0) {
                req.setAttribute("page", intPage + 1);
            }
        } else {
            intPage = Integer.parseInt(page);
            intPage = (intPage * blimit) - blimit;
            req.setAttribute("page", Integer.parseInt(page));
        }
        return AuthorDAOFactory.getInstance().getAllAuthorBlogs(intPage, blimit);
    }

    private int blogCount(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return BlogDAOFactory.getInstance().blogCount();
    }

    private List<Author> getAuthorByCategory(HttpServletRequest req, HttpServletResponse resp, int blimit) throws Exception {
        String page = req.getParameter("page");
        String category = req.getParameter("category");
        int intPage = 0;
        if (page == null || page.trim() == null || page.equals("1")) {
            page = "0";
            intPage = Integer.parseInt(page);
            if (intPage == 0) {
                req.setAttribute("page", intPage + 1);
            }
        } else {
            intPage = Integer.parseInt(page);
            intPage = (intPage * blimit) - blimit;
            req.setAttribute("page", Integer.parseInt(page));
        }
        return AuthorDAOFactory.getInstance().getAuthorByCategory(category, intPage, blimit);
    }

    private int blogCountByCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String category = req.getParameter("category");
        return BlogDAOFactory.getInstance().blogCountByCategory(category);
    }

    private List<Author> getAuthorBySearch(HttpServletRequest req, HttpServletResponse resp, int blimit) throws Exception {
        String page = req.getParameter("page");
        String search = req.getParameter("search");
        int intPage = 0;
        if (page == null || page.trim() == null || page.equals("1")) {
            page = "0";
            intPage = Integer.parseInt(page);
            if (intPage == 0) {
                req.setAttribute("page", intPage + 1);
            }
        } else {
            intPage = Integer.parseInt(page);
            intPage = (intPage * blimit) - blimit;
            req.setAttribute("page", Integer.parseInt(page));
        }
        return AuthorDAOFactory.getInstance().getAuthorBySearch(search, intPage, blimit);
    }

    private int blogCountBySearch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String search = req.getParameter("search");
        return AuthorDAOFactory.getInstance().getSearchCount(search);
    }

    private List<Comment> commentList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return CommentDAOFactory.getInstance().getComment();
    }

    private List<Feedback> getFeedback(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return FeedbackDAOFactory.getInstance().getFeedback();
    }

    private List<Report> getBlogReport(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return BlogReportDAOFactory.getInstance().getReportList();
    }

    private List<Like> getLikedBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uid = (String) req.getSession().getAttribute("uid");
        return BlogReportDAOFactory.getInstance().getLikeBlog(Integer.parseInt(uid));
    }

    private List<FavouriteBlog> getFavouriteBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uid = (String) req.getSession().getAttribute("uid");
        return BlogReportDAOFactory.getInstance().getFavouriteBlog(Integer.parseInt(uid));
    }

    private List<User> getAllUsers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return UserDAOFactory.getInstance().getAllUsers();
    }

    private List<Blog> getMostLikedBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return BlogReportDAOFactory.getInstance().getMostLikeBlog();
    }

    private List<Blog> getMostReportedBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return BlogReportDAOFactory.getInstance().getMostReportedBlog();
    }

    private List<Category> getCategoryList() throws Exception {
        return AdminDAOFactory.getInstance().getCategoryList();
    }

    private List<String> getCategoryName() throws Exception {
        return AdminDAOFactory.getInstance().getCategoryName();
    }

    private List<Author> getAuthorProfileBlogList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uid = req.getParameter("uid");
        return AuthorDAOFactory.getInstance().getAuthorProfileBlog(Integer.parseInt(uid));
    }

    private int getUserFeedCount(String uid) throws Exception {
        return FeedbackDAOFactory.getInstance().getUserFeedbackCount(Integer.parseInt(uid));
    }

    private int getUserLikeCount(String uid) throws Exception {
        return BlogReportDAOFactory.getInstance().getUserLikeCount(Integer.parseInt(uid));
    }

    private int getUserBlogCount(String uid) throws Exception {
        return BlogDAOFactory.getInstance().getUserBlogCount(Integer.parseInt(uid));
    }

    private int getUserComments(String uid) throws Exception {
        return CommentDAOFactory.getInstance().getUserCommentCount(Integer.parseInt(uid));
    }

    private int checkAuthorProfilePhoto(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uid = req.getParameter("uid");
        return PhotoDAOFactory.getInstance().checkUserPhoto(Integer.parseInt(uid));
    }

    private List<User> getSearchUsers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String search = req.getParameter("search").trim();
        return UserDAOFactory.getInstance().getSearchUsers(search);
    }

    private List<Category> getSearchCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String search = req.getParameter("search").trim();
        return AdminDAOFactory.getInstance().getSearchCategoryName(search);
    }

    private int getUserCount() throws Exception {
        return UserDAOFactory.getInstance().getUserCount();
    }

    private int getFeedBackCount() throws Exception {
        return FeedbackDAOFactory.getInstance().getFeedbackCount();
    }

    private int getCommentCount() throws Exception {
        return CommentDAOFactory.getInstance().getCommemtCount();
    }

    private int getCategoryCount() throws Exception {
        return AdminDAOFactory.getInstance().getCategoryCount();
    }

    private List<Author> getBlogReports(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return AuthorDAOFactory.getInstance().getBlogReport();
    }

    private List<Feedback> getFeedbackReport(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return FeedbackDAOFactory.getInstance().getFeedbackReport();
    }

    private List<Comment> getCommentReport(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return CommentDAOFactory.getInstance().getCommentReport();
    }

}
