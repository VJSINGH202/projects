<%-- 
    Document   : MostReportedBlog
    Created on : Apr 5, 2020, 10:23:02 AM
    Author     : DELL PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.cms.entities.Blog"%>
<%      
     List<Blog> MostReportedBlogList = (List<Blog>) request.getAttribute("mostreportedblog");
%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="../jspf/head.jspf" %>
    <body id="home">
       <%@include file="../jspf/adminheader.jspf"%>
        <section style="padding-top: -50px;">
            <div class="container-fluid">
                <div class="row pt-md-3 mt-md-5">  
                    <div class="col-xl-10 col-lg-9 col-md-8 ml-auto">
                        <div id="msg" class="text-center container pt-2">
                            <%
                                String msg = (String) request.getAttribute("msg");
                                if (msg != null) {
                                    out.print("<div class='alert alert-info alert-dismissible fade show'>"
                                            + "<button class='close' data-dismiss='alert' type='button'>"
                                            + "<span>&times;</span></button>"
                                            + "<strong>" + msg + "</strong>"
                                            + "</div>");
                                }
                            %>
                        </div>
                        <section id="acion" class="mb-4 bg-white">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-lg-3 col-md-6 mb-md-1">
                                        <a href="<%=application.getContextPath()%>/FrontController?url=dashboard" class="btn btn-outline-secondary btn-block">
                                            <i class="fa fa-arrow-left"></i> Dashboard
                                        </a>
                                    </div>
                                    <div class="col-lg-3 col-md-6 mb-md-1">
                                        <a href="<%=application.getContextPath()%>/FrontController?url=userblog" class="btn btn-outline-info btn-block">
                                            <i class="fa fa-edit"></i> User Post
                                        </a>
                                    </div>
                                    <div class="col-lg-3 col-md-6 mb-md-1">
                                        <a href="<%=application.getContextPath()%>/FrontController?url=user" class="btn btn-outline-info btn-block">
                                            <i class="fa fa-users"></i> User
                                        </a>
                                    </div>
                                    <div class="col-lg-3 col-md-6 mb-md-1">
                                        <a href="<%=application.getContextPath()%>/FrontController?url=mostlikedblog" class="btn btn-outline-primary btn-block">
                                            <i class="fa fa-thumbs-o-up"></i> Most Liked Post
                                        </a>
                                    </div>
                                    <div class="col-lg-3 col-md-6 mb-md-1">
                                        <a href="#" class="btn btn-outline-success btn-block" data-toggle="modal" data-target="#asignup">
                                            <i class="fa fa-plus"></i> Add User
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </section>       
                        <div class="row">
                            <div class="col-xl-10 mx-auto">
                                <div class="row pt-md-3 mt-md-2 mb-5">  
                                    <div class="col-12 bg-white">
                                        <div class="text-center">
                                            <h4>Most Liked Blog Table</h4>
                                        </div>
                                        <table class="table table-hover table-responsive">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Blog Title</th>
                                                    <th>Blog Category</th>
                                                    <th>Created At</th>
                                                    <th>Total Report</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for (Blog tempBlog : MostReportedBlogList) { %>
                                                <tr class="align-items-center">
                                                    <td scope="row"><%= tempBlog.getBlogId()%></td>
                                                    <td><%= tempBlog.getBlogTitle()%></td>
                                                    <td><%= tempBlog.getBlogCatagory()%></td>
                                                    <td><%= tempBlog.getDop()%></td>
                                                    <td><%= tempBlog.getBlogCount()%></td>
                                                    <td><a href="<%=application.getContextPath()%>/FrontController?url=authorprofile&uid=<%=tempBlog.getUserid()%>" class="btn btn-sm btn-outline-success mb-sm-1" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Click to View User</h6>"><i class="fa fa-eye"></i> User</a>
                                                        <a href="<%=application.getContextPath()%>/Admin?command=authorblog&bid=<%=tempBlog.getBlogId()%>" class="btn btn-outline-info btn-sm ml-md-1 mb-sm-1" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>View Blog</h6>"><i class="fa fa-eye"></i> Blog</a>
                                                        <form action="<%=application.getContextPath()%>/FrontController" method="post" class="d-inline-block ml-md-1">
                                                                <input type="hidden" name="url" value="editpost">
                                                                <input type="hidden" name="bid" value="<%=tempBlog.getBlogId()%>">
                                                                <button type="submit" class="btn btn-outline-success btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Edit blog</h6>"><i class="fa fa-edit"></i></button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>            
                </div>
            </div>
        </section>
        <%@include file="../jspf/footer-admin.jspf" %>
        <%@include file="../jspf/modals.jspf" %>
        <%@include file="../jspf/script.jspf" %>
    </body>
</html>