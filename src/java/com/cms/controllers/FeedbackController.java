package com.cms.controllers;

import com.cms.dao.factory.FeedbackDAOFactory;
import com.cms.entities.Feedback;
import java.io.IOException;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "FeedbackController", urlPatterns = {"/Feedback"})
public class FeedbackController extends HttpServlet {

    String msg;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String command = request.getParameter("command");
            switch (command) {
                case "add":
                    msg = addFeedback(request, response);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(request, response, msg, "/FrontController?url=feedback");
                    } else {
                        pageRedirect(request, response, msg, "/FrontController?url=feedback");
                    }
                    break;
                case "review":
                    msg = reviewFeedback(request, response);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(request, response, msg, "/FrontController?url=feedback");
                    } else {
                        pageRedirect(request, response, msg, "/FrontController?url=feedback");
                    }
                    break;
                case "unreview":
                    msg = unReviewFeedback(request, response);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(request, response, msg, "/FrontController?url=feedback");
                    } else {
                        pageRedirect(request, response, msg, "/FrontController?url=feedback");
                    }
                    break;
                case "deletereview":
                    msg = deleteFeedback(request, response);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(request, response, msg, "/FrontController?url=feedback");
                    } else {
                        pageRedirect(request, response, msg, "/FrontController?url=feedback");
                    }
                    break;
                default:
                    response.getWriter().print("<body>helllooooo</body>");
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(FeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String addFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String feed = request.getParameter("feed");
        String uid = (String) request.getSession().getAttribute("uid");
        validateFeedBack(feed);
        if (feed.trim().equals("")) {
            return "Feedback Can't Blank";
        } else {
            Feedback feedback = new Feedback();
            feedback.setFeed(feed);
            feedback.setUserId(uid);
            return FeedbackDAOFactory.getInstance().addFeedback(feedback);
        }
    }

    private String reviewFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fid = request.getParameter("fid").trim();
        return FeedbackDAOFactory.getInstance().reviewFeedback(Integer.parseInt(fid));
    }

    private String unReviewFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fid = request.getParameter("fid").trim();
        return FeedbackDAOFactory.getInstance().unReviewFeedback(Integer.parseInt(fid));
    }

    private String deleteFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fid = request.getParameter("fid").trim();
        return FeedbackDAOFactory.getInstance().deleteFeeback(Integer.parseInt(fid));
    }
    
    private void pageRedirect(HttpServletRequest req, HttpServletResponse resp, String msg, String url) throws Exception {
       req.setAttribute("msg", msg);
       req.getRequestDispatcher(url).forward(req, resp);
    }

    private String validateFeedBack(String feed) {
        if(feed == null || feed.trim().equals("") || feed.length()< 2 || feed.length() > 100){
             return "Field must be (2 to 100) characters...";
        }
        return "";
    }

}
