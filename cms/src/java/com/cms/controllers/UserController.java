package com.cms.controllers;

import com.cms.dao.factory.UserDAOFactory;
import com.cms.entities.User;
import java.io.IOException;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    String msg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String command = req.getParameter("command");
            switch (command) {
                case "update":
                    msg = updateUserProfile(req, resp);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else if (msg.startsWith("Fields")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else if (msg.startsWith("Username")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else if (msg.startsWith("Email")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else if (msg.startsWith("Bio")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else {
                        pageRedirect(req, resp, "Something Went Wrong", "FrontController?url=profile");
                    }
                    break;
                case "updpass":
                    msg = updateUserPassword(req, resp);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else if (msg.startsWith("Fields")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else if (msg.startsWith("Password Not")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else if (msg.startsWith("Password")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else if (msg.startsWith("Comfirm")) {
                        pageRedirect(req, resp, msg, "FrontController?url=profile");
                    } else {
                        pageRedirect(req, resp, "Try agin something went wrong...", "FrontController?url=profile");
                    }
                    break;
                case "delete":
                    msg = deleteUser(req, resp);
                    String mode = (String)req.getSession().getAttribute("mode");
                    if(mode.equals("admin")){
                       if (msg.startsWith("Successfully")) {
                            pageRedirect(req, resp, msg, "FrontController?url=user");
                        } 
                        else {
                            pageRedirect(req, resp, msg, "FrontController?url=user");
                        }
                    }
                    else{
                        if (msg.startsWith("Successfully")) {
                            pageRedirect(req, resp, msg, "FrontController?url=home");
                        } 
                        else {
                            pageRedirect(req, resp, msg, "FrontController?url=home");
                        }
                    }
                    break;
                default:
            }
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String updateUserProfile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("name").trim();
        String email = req.getParameter("email").trim();
        String bio = req.getParameter("bio").trim();
        String uid = (String) req.getSession().getAttribute("uid");
        String validateUserProfileFieldsMsg = validateUserProfileFields(username, email, bio);
        if (validateUserProfileFieldsMsg.startsWith("Ok")) {
            User user = new User();
            user.setUserId(uid);
            user.setUserName(username);
            user.setEmail(email);
            user.setBio(bio);
            return UserDAOFactory.getInstance().updateUserProfile(user);
        } else {
            return validateUserProfileFieldsMsg;
        }
    }

    private String updateUserPassword(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pass = req.getParameter("pass").trim();
        String cpass = req.getParameter("cpass").trim();
        String uid = (String) req.getSession().getAttribute("uid");
        String validateUserPasswordFieldsMsg = validateUserPasswordFields(pass, cpass);
        if (validateUserPasswordFieldsMsg.equals("Ok")) {
            User user = new User();
            user.setUserId(uid);
            user.setUserPassword(pass);
            return UserDAOFactory.getInstance().updateUserPassword(user);
        } else {
            return validateUserPasswordFieldsMsg;
        }
    }

    private String validateUserProfileFields(String username, String email, String bio) {
        if ((username == null ||username.equals("")) && (email == null ||email.equals("")) && (bio == null ||bio.equals(""))) {
            return "Fields can't be blank...";
        } else if (username == null || username.equals("") || username.length()< 2 || username.length() >20) {
            return "Username Field must be (2 to 20) characters...";
        } else if (email == null || email.equals("") || email.length() <7 || email.length()>30 ) {
            return "Email Field almost(30) characters...";
        } else if (bio == null || bio.equals("") || bio.length() < 2 || bio.length() > 1000) {
            return "Bio Field must be (2 to 1000) characters...";
        } else {
            return "Ok";
        }
    }

    private String validateUserPasswordFields(String pass, String cpass) {
        if ((pass == null || pass.equals("")) && (cpass == null || cpass.equals(""))) {
            return "Fields can't be blank...";
        } else if (pass == null || pass.equals("") || pass.length() <2 || pass.length()>20 ) {
            return "Password Field must be (2 to 20) characters...";
        } else if (cpass == null || cpass.equals("") || cpass.length() <2 || cpass.length() > 20 ) {
            return "Comfirm Password Field must be (2 to 20) characters...";
        } else if (!pass.equals(cpass)) {
            return "Password Not match try again...";
        } else {
            return "Ok";
        }
    }

    private void pageRedirect(HttpServletRequest req, HttpServletResponse resp, String msg, String url) throws Exception {
        req.setAttribute("msg", msg);
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private String deleteUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int userid;
        String id = req.getParameter("uid").trim();
        if (id != null) {
            userid = Integer.parseInt(id);
            return UserDAOFactory.getInstance().deleteUser(userid);
        } else {
            userid = 0;
            return UserDAOFactory.getInstance().deleteUser(userid);
        }
    }
}
