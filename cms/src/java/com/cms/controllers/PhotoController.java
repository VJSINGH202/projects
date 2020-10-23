package com.cms.controllers;

import com.cms.dao.factory.PhotoDAOFactory;
import com.cms.entities.Photo;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
@WebServlet(name = "PhotoController", urlPatterns = {"/Photo"})
public class PhotoController extends HttpServlet {

    String msg;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            if (req.getParameter("n") != null) {
                byte[] userPhoto = PhotoDAOFactory.getInstance().getUserPhoto(Integer.parseInt(req.getParameter("n")));
                ServletOutputStream out = resp.getOutputStream();
                if(userPhoto!=null){
                out.write(userPhoto);}else{
                    ServletContext context= getServletContext();
                    InputStream in = context.getResourceAsStream("/images/avatar.png");
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    OutputStream os = resp.getOutputStream();
                    while((read = in.read(bytes)) != -1){
                      os.write(bytes, 0, read);
                    }
                    os.flush();
                    os.close();
                }
            } else if (req.getParameter("comm") != null) {
                boolean equals = req.getParameter("comm").equals("deletephoto");
                if (equals) {
                    msg = deleteUserPhoto(req, resp);
                    if (msg.startsWith("Successfully")) {
                        req.setAttribute("msg", msg);
                        req.getRequestDispatcher("FrontController?url=profile").forward(req, resp);
                    } else {
                        req.setAttribute("msg", msg);
                        req.getRequestDispatcher("FrontController?url=profile").forward(req, resp);
                    }
                }
            } else {
                String command = null;
                FileItem file = null;
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
                    } else {
                        file = item;
                    }
                }
                if (command == null) {
                    command = "empty";
                }

                switch (command) {
                    case "addphoto":
                        String uid = (String) req.getSession().getAttribute("uid");
                        Photo photo = new Photo();
                        photo.setPhoto(file);
                        photo.setUserId(Integer.parseInt(uid));
                        msg = PhotoDAOFactory.getInstance().addUserPhoto(photo);
                        if (msg.startsWith("Successfully")) {
                            req.setAttribute("msg", msg);
                            req.getRequestDispatcher("FrontController?url=profile").forward(req, resp);
                        }
                        break;
                    default:
                        resp.getWriter().print("<body>default</body>");
                        break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PhotoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String deleteUserPhoto(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uid = (String) req.getSession().getAttribute("uid");
        return PhotoDAOFactory.getInstance().deletePhoto(Integer.parseInt(uid));
    }

}
