<%-- 
    Document   : Userblog
    Created on : Apr 3, 2020, 10:07:59 AM
    Author     : DELL PC
--%>
<%@page import="com.cms.entities.Author"%>
<%@page import="java.util.List"%>
<%
    List<Author> AuthorList = (List<Author>) request.getAttribute("authorbloglist");
    int blogCount = (int) request.getAttribute("blogcount");
    int count = (int) Math.ceil((double) blogCount / 10);
    int currentPage = (int) request.getAttribute("page");
    String search = (String) request.getAttribute("search");
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
                        <section id="acion" class="bg-white">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-lg-3 col-md-6 mb-md-1">
                                        <a href="<%=application.getContextPath()%>/FrontController?url=dashboard" class="btn btn-outline-secondary btn-block">
                                            <i class="fa fa-arrow-left"></i> Dashboard
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
                                        <a href="<%=application.getContextPath()%>/FrontController?url=mostreportedblog" class="btn btn-outline-danger btn-block">
                                            <i class="fa fa-crosshairs"></i> Most Reported Post
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
                        <section id="blog-Search" class="py-1 bg-white">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-6 ml-lg-auto my-md-2 my-sm-2">
                                        <form action="<%=application.getContextPath()%>/FrontController" method="post">
                                            <div class="input-group">
                                                <input type="hidden" name="url" value="userblog">
                                                <%
                                                    if (search != null) {
                                                %>
                                                <input type="text" name="search" id="search" value="<%=search%>" class="form-control" placeholder="Search..." required>
                                                <%} else {%>
                                                <input type="text" name="search" id="search" class="form-control" placeholder="Search..." required><%}%>
                                                <span class="input-group-btn">
                                                    <input type="submit" class="btn btn-info" value="Search">
                                                    <input type="reset" class="btn btn-secondary" value="Clear">
                                                </span>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <div class="row">
                            <div class="col-xl-10 mx-auto">
                                <div class="row pt-md-3 mt-md-2 mb-5">  
                                    <div class="col-12 bg-white">
                                        <div class="text-center">
                                            <h4>Users Blog Table</h4>
                                        </div>
                                        <table class="table table-hover table-responsive">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>UserName</th>
                                                    <th>Email</th>
                                                    <th>Blog</th>
                                                    <th>Blog Title</th>
                                                    <th>Blog Category</th>
                                                    <th>Created At</th>
                                                    <th colspan="2">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%   if (!AuthorList.isEmpty()) {
                                                        for (Author tempAuthor : AuthorList) {%>
                                                <tr class="align-items-center">
                                                    <td scope="row"><%= tempAuthor.getAuthorId()%></td>
                                                    <td><%=tempAuthor.getAuthorName()%></td>
                                                    <td><%=tempAuthor.getAuthorEmail()%></td>
                                                    <td><a href="<%=application.getContextPath()%>/Admin?command=authorblog&bid=<%=tempAuthor.getAuthorBlogId()%>" class="btn btn-outline-info btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>View Blog</h6>"><i class="fa fa-eye"></i> Blog</a></td>
                                                    <td><%=tempAuthor.getAuthorBlogTitle()%></td>
                                                    <td><%=tempAuthor.getAuthorBlogCategory()%></td>
                                                    <td><%=tempAuthor.getAuthorBlogCreatedAtDate()%></td>
                                                    <td><a href="<%=application.getContextPath()%>/FrontController?url=authorprofile&uid=<%=tempAuthor.getAuthorBlogId()%>" class="btn btn-sm btn-outline-info" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Click to View User</h6>"><i class="fa fa-eye"></i> User</a></td>
                                                    <td><form action="<%=application.getContextPath()%>/FrontController" method="post">
                                                            <input type="hidden" name="url" value="editpost">
                                                            <input type="hidden" name="bid" value="<%=tempAuthor.getAuthorBlogId()%>">
                                                            <button type="submit" class="btn btn-outline-success btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Edit blog</h6>"><i class="fa fa-edit"></i></button></form></td>
                                                </tr>
                                                <% }
                                                } else {%>
                                                <tr><td colspan="8" class="text-info">No Blog Found</td></tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                        <nav>
                                            <ul class="pagination justify-content-center">
                                                <%  String active = "active";
                                                    for (int i = 1; i <= count; i++) {
                                                        if (i == currentPage) {
                                                %>
                                                <li class="page-item <%=active%>"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=home&page=<%=i%>"><%=i%></a></li>
                                                    <%} else {%>
                                                <li class="page-item"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=home&page=<%=i%>"><%=i%></a></li>
                                                    <%}
                                                        }%>
                                            </ul>
                                        </nav>
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
