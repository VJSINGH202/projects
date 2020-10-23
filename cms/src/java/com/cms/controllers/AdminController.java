package com.cms.controllers;

import com.cms.dao.factory.*;
import java.security.Security;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import com.cms.entities.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "AdminController", urlPatterns = {"/Admin"})
public class AdminController extends HttpServlet {

    String msg;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String command = req.getParameter("command");
            switch(command) {
                case "register":
                    msg = register(req, resp);
                    String mode = (String) req.getSession().getAttribute("mode");

                    if (mode != null) {
                        if (msg.startsWith("Successfully")) {
                            pageRedirect(req,resp,"Successfully created","FrontController?url=user");
                        } else if (msg.startsWith("Fields")) {
                            pageRedirect(req, resp, msg, "FrontController?url=user");
                        } else if (msg.startsWith("Username")) {
                             pageRedirect(req, resp, msg, "FrontController?url=user");
                        } else if (msg.startsWith("Password")) {
                             pageRedirect(req, resp, msg, "FrontController?url=user");
                        } else if (msg.startsWith("Email")) {
                             pageRedirect(req, resp, msg, "FrontController?url=user");
                        } else if (msg.startsWith("Bio")) {
                             pageRedirect(req, resp, msg, "FrontController?url=user");
                        } else if (msg.startsWith("Date of Birth")) {
                             pageRedirect(req, resp, msg, "FrontController?url=user");
                        } else {
                             pageRedirect(req, resp, "Try Again Something went wrong....", "FrontController?url=user");
                        }
                    } else if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "FrontController");
                    } else if (msg.startsWith("Fields")) {
                        pageRedirect(req, resp, msg,"FrontController");
                    } else if (msg.startsWith("Username")) {
                        pageRedirect(req, resp, msg,"FrontController");
                    } else if (msg.startsWith("Password")) {
                        pageRedirect(req, resp, msg,"FrontController");
                    } else if (msg.startsWith("Email")) {
                        pageRedirect(req, resp, msg,"FrontController");
                    } else if (msg.startsWith("Bio")) {
                        pageRedirect(req, resp, msg,"FrontController");
                    } else if (msg.startsWith("Date of Birth")) {
                        pageRedirect(req, resp, msg,"FrontController");
                    } else {
                        pageRedirect(req, resp, "Try Again Something went wrong....","FrontController");
                    }
                    break;
                case "addcategory":
                    msg = addCategory(req, resp);
                    if (msg.startsWith("Successfully")) {
                        showMessage(resp, msg);
                    } else {
                        showMessage(resp, msg);
                    }
                    break;
                case "addadmincategory":
                    msg = addCategory(req, resp);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "FrontController?url=category");
                    } else {
                        pageRedirect(req, resp, msg, "FrontController?url=category");
                    }
                    break;
                case "deletecategory":
                    msg = deleteCategory(req, resp);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "FrontController?url=category");
                    } else {
                        pageRedirect(req, resp, msg, "FrontController?url=category");
                    }
                    break;
                case "forgotpassword":
                    forgotPassword(req, resp);
                    break;
                case "login":
                    msg = login(req, resp);
                    if (msg.startsWith("Sorry")) {
                        pageRedirect(req, resp, msg, "FrontController");
                    } else if (msg.startsWith("Username")) {
                        pageRedirect(req, resp, msg, "FrontController");
                    } else if (msg.startsWith("Password")) {
                        pageRedirect(req, resp, msg, "FrontController");
                    } else if (msg.startsWith("Both")) {
                        pageRedirect(req, resp, msg, "FrontController");
                    } else {
                        String aa[] = msg.split(":");
                        req.getSession().invalidate();
                        HttpSession newSession = req.getSession(true);
                        newSession.setAttribute("uid", aa[0]);
                        newSession.setAttribute("username", aa[1]);
                        newSession.setAttribute("mode", aa[2]);
                        pageRedirect(req, resp, "Successfully Login " + aa[1].toUpperCase(), "FrontController");
                    }
                    break;
                case "logout":
                    logout(req,resp);
                    break;
                case "authorblog":
                    Author authorBlog = getAuthorBlog(req, resp);
                    List<Comment> blogComments = getBlogComment(req, resp);
                    List<String> categoryName = getCategoryName();
                    if (authorBlog != null) {
                        req.setAttribute("authorblog", authorBlog);
                        req.setAttribute("blogcomments", blogComments);
                        req.setAttribute("categoryname", categoryName);
                        req.setAttribute("pagetitle", "Author Blog");
                        req.getRequestDispatcher("views/authorblog.jsp").forward(req, resp);
                    }
                    break;
                case "report":
                    msg = addReport(req, resp);
                    if (msg.startsWith("Successfully")) {
                        showMessage(resp, msg);
                    } else {
                        showMessage(resp, msg);
                    }
                    break;
                case "favourite":
                    msg = addFavouriteBlog(req, resp);

                    if (msg.startsWith("Successfully")) {
                        showMessage(resp, msg);
                    } else if (msg.startsWith("Please")) {
                        showMessage(resp, msg);
                    } else {
                        int rid = Integer.parseInt(msg);
                        if (rid > 0) {
                            msg = removeFavouriteBlog(rid);
                        }
                        if (msg.startsWith("Successfully")) {
                            showMessage(resp, msg);
                        }
                        if (msg.startsWith("Sorry")) {
                            showMessage(resp, msg);
                        }
                    }
                    break;
                case "like":
                    msg = addLike(req, resp);

                    if (msg.startsWith("Successfully")) {
                        showMessage(resp, msg);
                    } else if (msg.startsWith("Please")) {
                        showMessage(resp, msg);
                    } else {
                        int lid = Integer.parseInt(msg);
                        if (lid > 0) {
                            msg = removeLike(lid);
                        }
                        if (msg.startsWith("Successfully")) {
                            showMessage(resp, msg);
                        }
                        if (msg.startsWith("Sorry")) {
                            showMessage(resp, msg);
                        }
                    }
                    break;
                case "undolike":
                    String lid = req.getParameter("lid");
                    msg = removeLike(Integer.parseInt(lid));
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "FrontController?url=likeblog");
                    } else {
                        pageRedirect(req, resp, msg, "FrontController?url=likeblog");
                    }
                    break;
                case "removefavourite":
                    String fid = req.getParameter("fid");
                    msg = removeFavouriteBlog(Integer.parseInt(fid));
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "FrontController?url=favouriteblog");
                    } else {
                        pageRedirect(req, resp, msg, "FrontController?url=favouriteblog");
                    }
                    break;
                case "reportdone":
                    msg = doneReportStatus(req, resp);
                    if (msg.startsWith("Successfully")) {
                        req.setAttribute("msg", msg);
                        req.getRequestDispatcher("FrontController?url=blogreport").forward(req, resp);
                    } else {
                        req.setAttribute("msg", msg);
                        req.getRequestDispatcher("FrontController?url=blogreport").forward(req, resp);
                    }
                    break;
                case "reportundone":
                    msg = unDoneReportStatus(req, resp);
                    if (msg.startsWith("Successfully")) {
                        pageRedirect(req, resp, msg, "FrontController?url=blogreport");
                    } else {
                        pageRedirect(req, resp, msg, "FrontController?url=blogreport");
                    }
                    break;
                case "reportdelete":
                    msg = deleteReport(req, resp);
                    if (msg.startsWith("Successfully")) {
                       pageRedirect(req, resp, msg, "FrontController?url=blogreport");
                    } else {
                        pageRedirect(req, resp, msg, "FrontController?url=blogreport");
                    }
                    break;
                /*case "admin":
                     msg = loginAdmin(req,resp); 
                     if(msg.startsWith("Sorry"))
                    {
                        req.setAttribute("msg", msg);
                        req.getRequestDispatcher("FrontController").forward(req, resp);
                    }else{
                         String aa[] = msg.split(":");
                         req.getSession().invalidate();
                         HttpSession newSession = req.getSession(true);
                         newSession.setAttribute("uid", aa[0]);
                         newSession.setAttribute("username", aa[1]);
                         newSession.setAttribute("mode", "admin");
                         req.setAttribute("msg", "Successfully Login "+aa[1].toUpperCase());
                         req.getRequestDispatcher("FrontController").forward(req, resp);
                    }     
                    break;*/
                default:
                    resp.getWriter().print("<body>hello</body>");
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uname = req.getParameter("uname").trim();
        String password = req.getParameter("password").trim();
        String email = req.getParameter("email").trim();
        String mode = req.getParameter("mode");
        String bio = req.getParameter("bio").trim();
        String dob = req.getParameter("dob").trim();
        String validateRegistrationMsg = validateRegistration(uname, password, email, bio, dob);
        if (validateRegistrationMsg.equals("Ok")) {
            User user = new User(uname, password, email, dob, bio);
            String usermode;
            usermode = (String)req.getSession().getAttribute("mode");
            System.out.println(usermode);
            if (usermode == null) {
                return UserDAOFactory.getInstance().registerUser(user, "user");
            } else {
                return UserDAOFactory.getInstance().registerUser(user, mode);
            }
        } else {
            return validateRegistrationMsg;
        }
    }

    private String login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uname = req.getParameter("uname").trim();
        String password = req.getParameter("password").trim();
        if (uname.equals("") && password.equals("")) {
            return "Both Username And Password cant blank...";
        } else if (uname == null|| uname.equals("") || uname.length() < 2 || uname.length()> 20) {
            return "Username Field must be (2 to 20) characters...";
        } else if (password == null || password == null || password.equals("") || password.length() < 2 || password.length() > 20) {
            return "Password Field must be (2 to 20) characters...";
        } else {
            User user = new User();
            user.setUserName(uname);
            user.setUserPassword(password);
            return UserDAOFactory.getInstance().loginUser(user);
        }
    }

    private String loginAdmin(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String uname = req.getParameter("uname");
        String password = req.getParameter("password");
        Admin admin = new Admin();
        admin.setAdminName(uname);
        admin.setAdminPassword(password);
        return AdminDAOFactory.getInstance().loginAdmin(admin);
    }

    private Author getAuthorBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bid = req.getParameter("bid").trim();
        int bId = 0;
        if (bid != null) {
            bId = Integer.parseInt(bid);
        }
        return AuthorDAOFactory.getInstance().getAuthorBlog(bId);
    }

    private String addReport(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bid = req.getParameter("bid");
        String uid = (String) req.getSession().getAttribute("uid");
        if (uid != null) {
            Author author = new Author(uid, bid);
            return BlogReportDAOFactory.getInstance().addReport(author);
        } else {
            return "Please Login to report.";
        }
    }

    private String addFavouriteBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bid = req.getParameter("bid");
        String uid = (String) req.getSession().getAttribute("uid");
        if (uid != null) {
            Author author = new Author(uid, bid);
            return BlogReportDAOFactory.getInstance().addFavouriteBlog(author);
        } else {
            return "Please Login to Add to Favourite.";
        }
    }

    private String removeFavouriteBlog(int rid) throws Exception {
        return BlogReportDAOFactory.getInstance().removeFavouriteBlog(rid);
    }

    private String addLike(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bid = req.getParameter("bid");
        String uid = (String) req.getSession().getAttribute("uid");
        if (uid != null) {
            Author author = new Author(uid, bid);
            return BlogReportDAOFactory.getInstance().addLike(author);
        } else {
            return "Please Login to like.";
        }
    }

    private String removeLike(int lid) throws Exception {
        return BlogReportDAOFactory.getInstance().removeLike(lid);
    }

    private void showMessage(HttpServletResponse resp, String msg) throws IOException {
        resp.getWriter().print("<div class='alert alert-success alert-dismissible fade show'>"
                + "<button class='close' data-dismiss='alert' type='button'>"
                + "<span>&times;</span></button>"
                + "<strong>" + msg + "</strong>"
                + "</div>");
    }

    private String doneReportStatus(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String rid = req.getParameter("rid").trim();
        if (rid != null) {
            return BlogReportDAOFactory.getInstance().doneReportStatus(Integer.parseInt(rid));
        } else {
            return "Sorry Not found";
        }
    }

    private String unDoneReportStatus(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String rid = req.getParameter("rid").trim();
        if (rid != null) {
            return BlogReportDAOFactory.getInstance().unDoneRepoetStatus(Integer.parseInt(rid));
        } else {
            return "Sorry Not found";
        }
    }

    private String deleteReport(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String rid = req.getParameter("rid").trim();
        if (rid != null) {
            return BlogReportDAOFactory.getInstance().deleteReport(Integer.parseInt(rid));
        } else {
            return "Sorry Not found";
        }
    }

    private List<Comment> getBlogComment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bid = req.getParameter("bid").trim();
        int bId = 0;
        if (bid != null) {
            bId = Integer.parseInt(bid);
        }
        return CommentDAOFactory.getInstance().getBlogComments(bId);
    }

    private void forgotPassword(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String email = req.getParameter("email");
        String msg = AdminDAOFactory.getInstance().forgotPassword(email);
        if (msg.startsWith("Sorry")) {
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/FrontController?url=forgot").forward(req, resp);
        } else {
            String ar[] = msg.split(":");  // msg = sharma@:sharman425@gmail.com ;  ar[0] = "sharma@"; ar[1]="sharman425@gmail.com"
            String[] EMAILADDR = {"vjsingh2021995@gmail.com", email};

            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            Session ses = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("vjpatwal2021995@gmail.com", "vj202@#$"); //Please Provide The Sender Address
                }
                /*sudarshanprajapati505@gmail.com*//*9451872296*/
            });
            ses.setDebug(true);
            Message msgg = new MimeMessage(ses); // Multipurpose Internet Mail Extension 
            InternetAddress addressFrom = new InternetAddress("vjpatwal2021995@gmail.com");
            msgg.setFrom(addressFrom);
            InternetAddress[] ADDRESS_TO = new InternetAddress[EMAILADDR.length];
            for (int i = 0; i < EMAILADDR.length; i++) {
                ADDRESS_TO[i] = new InternetAddress(EMAILADDR[i]);
            }
            msgg.setRecipients(Message.RecipientType.TO, ADDRESS_TO); // TO/CC/BCC
            msgg.setSubject("Hello " + ar[0] + "ur Password is - " + ar[1]);
            msgg.setContent("Hello " + ar[0] + ", this mail send on request for password recovery & ur password is - " + ar[1], "text/html");
            Transport.send(msgg);
            req.setAttribute("msg", "ur password successfully send on ur Registered mail id. ");
            req.getRequestDispatcher("/FrontController?url=forgot").forward(req, resp);
        }
    }

    private String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String categoryName = req.getParameter("category").trim();
        String uid = (String) req.getSession().getAttribute("uid");
        if(categoryName== null || categoryName.equals("") || categoryName.length()< 2 || categoryName.length() > 30){
            return "Category Field must be (2 to 30) characters..."; 
        }else{
        Category category = new Category(categoryName, uid);
        return AdminDAOFactory.getInstance().addCategory(category);}
    }

    private String deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String cid = req.getParameter("cid");
        if (cid != null) {
            return AdminDAOFactory.getInstance().deleteCategory(Integer.parseInt(cid));
        } else {
            return "Sorry not found";
        }
    }

    private List<String> getCategoryName() throws Exception {
        return AdminDAOFactory.getInstance().getCategoryName();
    }

    private String validateRegistration(String uname, String password, String email, String bio, String dob) {
        if (uname.equals("") && password.equals("") && email.equals("") && bio.equals("") && dob.equals("")) {
            return "Fields Cant be blank";
        } else if (uname == null || uname.equals("") || uname.length() < 2 || uname.length()> 20) {
            return "Username Field must be (2 to 20) characters...";
        } else if (password == null || password.equals("") || password.length() < 2 || password.length() > 20) {
            return "Password Field must be (2 to 20) characters...";
        } else if (email == null || email.equals("") || email.length() <7 || email.length()>30 ) {
            return "Email Field atmost (20) characters...";
        } else if (bio == null || bio.equals("") || bio.length() < 2 || bio.length() > 1000) {
            return "Bio Field must be (2 to 1000) characters...";
        } else if (dob == null || dob.equals("") || dob.length() < 10) {
            return "Date of Birth choose a date";
        } else {
            return "Ok";
        }
    }

    private void pageRedirect(HttpServletRequest req, HttpServletResponse resp, String msg, String url) throws Exception {
       req.setAttribute("msg", msg);
       req.getRequestDispatcher(url).forward(req, resp);
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Object uid = req.getSession().getAttribute("uid");
        Object username = req.getSession().getAttribute("username");
        Object mode = req.getSession().getAttribute("mode");
           if(uid!=null){
             req.getSession().removeAttribute("uid");
           }
           if(username!=null){
             req.getSession().removeAttribute("username");
           }
           if(mode!=null){
             req.getSession().removeAttribute("mode");
           }
           req.getSession().invalidate();
           pageRedirect(req, resp, "Successfully Logout ...", "FrontController");
    }

}