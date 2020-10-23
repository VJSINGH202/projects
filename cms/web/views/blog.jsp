<%-- 
    Document   : blog
    Created on : Mar 20, 2020, 10:23:36 AM
    Author     : DELL PC
--%>
<%@page import="java.util.List"%>
<%@page import="com.cms.entities.Blog"%>
<%@page import="com.cms.entities.User"%>
<%
    List<Blog> blogList = (List<Blog>) request.getAttribute("userblog");
    String link =(String)request.getAttribute("link");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                            <a href="<%=application.getContextPath()%>/FrontController?url=likeblog" class="btn btn-outline-info btn-block">
                                                <i class="fa fa-thumbs-o-up"></i> Liked Post
                                            </a>
                                        </div>
                                        <div class="col-lg-3 col-md-6 mb-md-1">
                                            <a href="<%=application.getContextPath()%>/FrontController?url=favouriteblog" class="btn btn-outline-danger btn-block">
                                                <i class="fa fa-heart-o"></i> Favourite Post
                                            </a>
                                        </div>
                                        <div class="col-lg-3 col-md-6 mb-md-1">
                                            <a href="#" class="btn btn-outline-success btn-block" data-toggle="modal" data-target="#addPostModal">
                                                <i class="fa fa-plus"></i> Add Post
                                            </a>
                                        </div>
                                        <div class="col-lg-3 col-md-6 mb-md-1">
                                            <a href="#" class="btn btn-outline-warning btn-block" data-toggle="modal" data-target="#addCategoryModal">
                                                <i class="fa fa-plus"></i> Add Category
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
                                            <h4>Blog Table</h4>
                                        </div>
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Title</th>
                                                    <th>Catagory</th>
                                                    <th>Photo</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for (Blog tempBlog : blogList) {%>
                                                <tr class="align-items-center">
                                                    <td scope="row"><%= tempBlog.getBlogId()%></td>
                                                    <td><%= tempBlog.getBlogTitle()%></td>
                                                    <td><%= tempBlog.getBlogCatagory()%></td>
                                                    <td><img src='<%=application.getContextPath()%>/Blog?p=<%=tempBlog.getBlogId()%>' width="100" height="50" class="img-thumbnail rounded"></td>
                                                    <td><form action="<%=application.getContextPath()%>/FrontController" method="get">
                                                            <input type="hidden" name="url" value="editpost">
                                                            <input type="hidden" name="bid" value="<%=tempBlog.getBlogId()%>">
                                                            <button type="submit" class="btn btn-outline-success btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Edit</h6>"><i class="fa fa-edit"></i></button></form></td>
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
        <!--Post modal   -->
<div class="modal fade" id="addPostModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form action="<%=application.getContextPath()%>/Blog" method="post" enctype="multipart/form-data" id="blog-form">
                <div class="modal-header bg-success text-white">
                    <h5 class="modal-title">Add Post</h5>
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="command" value="addblog">
                    <div class="form-group">
                        <label for="title">Title</label>
                        <input type="text" name="title" id="blog-title" class="form-control">
                        <div id="blog-title-msg"></div>
                    </div>
                      <div class="form-group">
                        <label for="category">Category</label>
                        <select id="blog-category" name="catagory" class="form-control">
                            <option value="none" selected>Select Category</option>
                    <%
                       List<String> categoryNameList =(List<String>)request.getAttribute("categoryname");
                       if(!categoryNameList.isEmpty()){
                    %>
                    <%  for(String tempName:categoryNameList){%>
                            <option value="<%=tempName%>"><%=tempName%></option>
                     <%}}%>
                     </select>
                     <div id="blog-category-msg"></div>
                    </div>
                    <div class="form-group">
                        <label for="bphoto">Blog Image Upload</label>
                        <input type="file" id="blog-photo" class="form-control-file" name="bphoto">
                        <small class="form-text text-muted">Max Size 3mb</small>
                        <div id="blog-photo-msg"></div>
                    </div>
                    <div class="form-group">  
                        <label for="bcontent">Blog Content</label>
                        <textarea class="form-control" id="blog-content" name="bcontent"></textarea>
                        <div id="blog-content-msg"></div>
                    </div> 
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-success">Published</button>
                </div>
            </form>
        </div>
    </div>
</div>                                    
        <%@include file="../jspf/footer-admin.jspf" %>
        <%@include file="../jspf/modals.jspf" %>
        <%@include file="../jspf/script.jspf" %>
    </body>
</html>