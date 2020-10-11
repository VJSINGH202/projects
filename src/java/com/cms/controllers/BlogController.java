package com.cms.controllers;

import com.cms.dao.factory.BlogDAOFactory;
import com.cms.entities.Blog;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author DELL PC
 */
@WebServlet(name = "BlogController", urlPatterns = {"/Blog"})
public class BlogController extends HttpServlet {

    String msg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("p") != null) {
            try {
                byte[] blogPhoto = BlogDAOFactory.getInstance().getblogPhoto(Integer.parseInt(req.getParameter("p")));
                ServletOutputStream out = resp.getOutputStream();
                out.write(blogPhoto);
            } catch (Exception ex) {
                Logger.getLogger(PhotoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String command = null;
            String title = null;
            String category = null;
            String bcontent = null;
            String bid = null;
            FileItem bphoto = null;
            boolean isMultipart = ServletFileUpload.isMultipartContent(req);
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            items = upload.parseRequest(req);
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    if (item.getFieldName().equals("command")) {
                        command = item.getString();
                    }
                    if (item.getFieldName().equals("title")) {
                        title = item.getString();
                    }
                    if (item.getFieldName().equals("catagory")) {
                        category = item.getString();
                    }
                    if (item.getFieldName().equals("bcontent")) {
                        bcontent = item.getString();
                    }
                    if (item.getFieldName().equals("bid")) {
                        bid = item.getString();
                    }
                } else {
                    bphoto = item;
                }
            }

            if (command == null) {
                command = "empty";
            }
            Blog blog = null;
            switch (command) {
                case "addblog":
                    String uid = (String) req.getSession().getAttribute("uid");
                    blog = new Blog();
                    blog.setBlogTitle(title);
                    blog.setBlogCatagory(category);
                    blog.setBlogContent(bcontent);
                    blog.setUserid(uid);
                    blog.setBlogPhoto(bphoto);
                    String validateBlogFields = validateBlogFields(title, category, bcontent, bphoto);
                    if (validateBlogFields.startsWith("ok")) {
                        msg = BlogDAOFactory.getInstance().addBlog(blog);
                        if (msg.startsWith("Successfully")) {
                            req.setAttribute("msg", msg);
                            req.getRequestDispatcher("FrontController?url=blog").forward(req, resp);
                        } else {
                            resp.getWriter().print("<body>inside5</body>");
                        }
                    } else {
                        req.setAttribute("msg", validateBlogFields);
                        req.getRequestDispatcher("FrontController?url=blog").forward(req, resp);
                    }

                    break;
                case "updateblog":
                    System.out.println(blog);
                    blog = new Blog();
                    blog.setBlogTitle(title);
                    blog.setBlogCatagory(category);
                    blog.setBlogContent(bcontent);
                    blog.setBlogId(Integer.parseInt(bid));
                    blog.setBlogPhoto(bphoto);
                    String validateBlogFields1 = validateBlogFields(title, category, bcontent, bphoto);
                    if (validateBlogFields1.startsWith("ok")) {
                        msg = BlogDAOFactory.getInstance().updateBlog(blog);
                        if (msg.startsWith("Successfully")) {
                            req.setAttribute("msg", msg);
                            req.getRequestDispatcher("FrontController?url=blog").forward(req, resp);
                        } else {
                            resp.getWriter().print("<body>inside5</body>");
                        }
                    } else {
                        req.setAttribute("msg", msg);
                        req.getRequestDispatcher("FrontController?url=blog").forward(req, resp);
                    }
                    break;
                case "deleteblog":
                    msg = deleteBlog(bid);
                    if (msg.startsWith("Successfully")) {
                        if (req.getSession().getAttribute("mode").equals("admin")) {
                            req.setAttribute("msg", msg);
                            req.getRequestDispatcher("FrontController?url=userblog").forward(req, resp);
                        } else {
                            req.setAttribute("msg", msg);
                            req.getRequestDispatcher("FrontController?url=blog").forward(req, resp);
                        }

                    } else if (req.getSession().getAttribute("mode").equals("admin")) {
                        req.setAttribute("msg", msg);
                        req.getRequestDispatcher("FrontController?url=userblog").forward(req, resp);
                    } else {
                        req.setAttribute("msg", msg);
                        req.getRequestDispatcher("FrontController?url=blog").forward(req, resp);
                    }
                    break;
                default:
                    resp.getWriter().print("<body>default</body>");
                    break;
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String deleteBlog(String bid) throws Exception {
        return BlogDAOFactory.getInstance().deleteBlog(Integer.parseInt(bid));
    }

    private String validateBlogFields(String title, String category, String bcontent, FileItem bphoto) {
        if (title == null || title.equals("") || title.length() < 2 || title.length() > 50) {
            return "Title Field must be (2 to 20) characters...";
        } else if (category == null || category.equals("") || category.length() < 2 || category.length() > 30) {
            return "Category Field must be (2 to 30) characters...";
        } else if (bcontent == null || bcontent.equals("") || bcontent.length() < 2 || bcontent.length() > 10000) {
            return "Blog Content Field must be (2 to 30) characters...";
        } else if (bphoto.getName().trim().equals("")) {
            return "Please choose a Photo...";
        } else {
            return "ok";
        }
    }

}
