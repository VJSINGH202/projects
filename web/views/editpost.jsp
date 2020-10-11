<%-- 
    Document   : editpost
    Created on : Mar 20, 2020, 6:20:57 PM
    Author     : DELL PC
--%>
<%@page import="com.cms.entities.Blog"%>
<%@page import="com.cms.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%               
    Blog blog = (Blog) request.getAttribute("blog");
    String link =(String)request.getAttribute("link");
%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="../jspf/head.jspf"%>
    <body id="home">
        <%@include file="../jspf/adminheader.jspf"%>
        <section>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-10 col-lg-9 col-md-8 ml-auto">
                        <div class="row pt-md-5 mt-md-5 mb-5 justify-content-center align-items-center">
                            <div class="col-12">
                                <!--Actions-->
                                <section id="acion" class="mb-4 bg-white">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-lg-3 col-md-4 mr-lg-auto">
                                                <a href="<%=application.getContextPath()%>/FrontController?url=dashboard" class="btn btn-outline-secondary btn-block">
                                                    <i class="fa fa-arrow-left"></i> Dashboard
                                                </a>
                                            </div>
                                            <div class="col-lg-3 col-md-4">
                                                <button type="submit" form="updatePhoto" class="btn btn-outline-success btn-block">
                                                    <i class="fa fa-check"></i> Save Change
                                                </button>
                                            </div>
                                            <div class="col-lg-3 col-md-4">
                                                <form action="<%=application.getContextPath()%>/Blog" method="post" enctype="multipart/form-data">
                                                    <input type="hidden" name="command" value="deleteblog">
                                                    <input type="hidden" name="bid" value="<%=blog.getBlogId()%>">
                                                    <button type="submit" class="btn btn-outline-danger btn-block">
                                                        <i class="fa fa-remove"></i> Delete
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                                <section id="posts">
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
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-lg-9">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h4>Edit Blog</h4>
                                                    </div>
                                                    <div class="card-body">
                                                        <form id="updatePhoto" action="<%=application.getContextPath()%>/Blog" method="post" enctype="multipart/form-data">
                                                            <input type="hidden" name="command" value="updateblog">
                                                            <input type="hidden" name="bid" value="<%=blog.getBlogId()%>">
                                                            <div class="form-group">
                                                                <label for="title">Title</label>
                                                                <input type="text" name="title" value="<%=blog.getBlogTitle()%>" class="form-control">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="category">Category</label>
                                                                <select id="category" name="catagory" class="form-control">
                                                                    <option value="none">Select Category</option>
                                                                    <%
                                                                        List<String> categoryNameList = (List<String>) request.getAttribute("categoryname");
                                                                        if (!categoryNameList.isEmpty()) {
                                                                    %>
                                                                    <%  for (String tempName : categoryNameList) {
                                                                          if(tempName.equals(blog.getBlogCatagory())){
                                                                    %>       
                                                                    <option value="<%=tempName%>" selected><%=tempName%></option>
                                                                    <%}else{ %>
                                                                     <option value="<%=tempName%>"><%=tempName%></option>
                                                                    <%}}}%>
                                                                </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="file">Blog Image Upload</label>
                                                                <input type="file" class="form-control-file" name="bphoto">
                                                                <small class="form-text text-muted">Max Size 3mb</small>
                                                            </div>
                                                            <div class="form-group">  
                                                                <label for="title">Blog Content</label>
                                                                <textarea class="form-control" name="bcontent"><%=blog.getBlogContent()%></textarea>
                                                            </div> 
                                                        </form>
                                                    </div>
                                                </div>
                                            </div> 
                                            <div class="col-lg-3 my-lg-0 my-5 col-md-8 col-sm-10 mx-md-auto mx-sm-auto mx-sm-auto">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h3 class="mb-3">Blog Photo</h3>
                                                        <img src='<%=application.getContextPath()%>/Blog?p=<%=blog.getBlogId()%>' class="rounded d-block img-thumbnail mb-3">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>                     
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="../jspf/footer-admin.jspf"%>
        <%@include file="../jspf/modals.jspf"%>
        <%@include file="../jspf/script.jspf"%>
    </body>
</html>
