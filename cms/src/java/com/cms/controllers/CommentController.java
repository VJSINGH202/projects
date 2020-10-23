package com.cms.controllers;

import com.cms.dao.factory.CommentDAOFactory;
import com.cms.entities.Comment;
import java.io.IOException;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author DELL PC
 */
@WebServlet(name = "CommentController", urlPatterns = {"/Comment"})
public class CommentController extends HttpServlet {

    String msg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String command = req.getParameter("command");
            if (command == null) {
                command = "error";
            }
            switch (command) {
                case "addcomment":
                    msg = addComment(req, resp);
                    if (msg.startsWith("Successfully")) {
                        showMessage(resp, msg);
                    } else {
                        showMessage(resp, msg);
                    }
                    break;
                case "approvecomment":
                    msg = approveComment(req, resp);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "/FrontController?url=comment");
                    } else {
                        pageRedirect(req, resp, msg, "/FrontController?url=comment");
                    }
                    break;
                case "unapprovecomment":
                    msg = unApproveComment(req, resp);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "/FrontController?url=comment");
                    } else {
                        pageRedirect(req, resp, msg, "/FrontController?url=comment");
                    }
                    break;
                case "deletecomment":
                    msg = deleteComment(req, resp);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "/FrontController?url=comment");
                    } else {
                        pageRedirect(req, resp, msg, "/FrontController?url=comment");
                    }
                    break;
                default:
                    resp.getWriter().print("<body>default</body>");
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String addComment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String description = req.getParameter("comment").trim();
        String bid = req.getParameter("bid");
        String uid = (String) req.getSession().getAttribute("uid");
        if (description == null || description.equals("") || description.length() < 2  || description.length() > 100) {
            return "Comment Field must be (2 to 100) characters...";
        }
        if (uid != null) {
            Comment comment = new Comment(description, Integer.parseInt(uid), Integer.parseInt(bid));
            return CommentDAOFactory.getInstance().addComment(comment);
        } else if (description == null || description.equals("") || description.length() < 2  || description.length() > 100) {
            return "Comment Field must be (2 to 100) characters...";
        } else {
            return "Please Login to comment :-)";
        }
    }

    private void showMessage(HttpServletResponse resp, String msg) throws IOException {
        resp.getWriter().print("<div class='alert alert-success alert-dismissible fade show'>"
                + "<button class='close' data-dismiss='alert' type='button'>"
                + "<span>&times;</span></button>"
                + "<strong>" + msg + "</strong>"
                + "</div>");
    }

    private String approveComment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("cid").trim();
        if (id != null) {
            return CommentDAOFactory.getInstance().approveComment(Integer.parseInt(id));
        } else {
            return "Sorry Not found :-(";
        }
    }

    private String unApproveComment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("cid").trim();
        if (id != null) {
            return CommentDAOFactory.getInstance().unApprovedComment(Integer.parseInt(id));
        } else {
            return "Sorry Not found :-(";
        }
    }

    private String deleteComment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("cid").trim();
        if (id != null) {
            return CommentDAOFactory.getInstance().deleteComment(Integer.parseInt(id));
        } else {
            return "Sorry Not found :-(";
        }
    }

    private void pageRedirect(HttpServletRequest req, HttpServletResponse resp, String msg, String url) throws Exception {
        req.setAttribute("msg", msg);
        req.getRequestDispatcher(url).forward(req, resp);
    }

}
