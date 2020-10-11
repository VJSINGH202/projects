<%--
    Document   : newjsp
    Created on : Mar 17, 2020, 1:14:05 PM
    Author     : DELL PC
--%>
<%@page import="java.util.List"%>
<%@page import="com.cms.entities.Author"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Author> AuthorList = (List<Author>) request.getAttribute("authorbloglist");
    String category = (String) request.getAttribute("category");
    String search = (String) request.getAttribute("search");
    int blogCount = (int) request.getAttribute("blogcount");
    int count = (int) Math.ceil((double) blogCount / 5);
    int currentPage = (int) request.getAttribute("page");
%>
<html lang="en">
    <%@include file="../jspf/head.jspf" %>
    <body class="p-fix">
        <nav class="navbar navbar-expand-md navbar-light bg-white buttom-border fixed-top">
            <div class="container">
                <a class="navbar-brand" href="<%=application.getContextPath()%>/FrontController?url=home">CMS</a>
                <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarNav"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=application.getContextPath()%>/FrontController?url=home">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About</a>
                        </li>
                        <% if (session.getAttribute("uid") == null) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="modal" data-target="#signup">SignUp</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="modal" data-target="#login">Login</a>
                        </li>
                        <!--<li class="nav-item">
                          <a class="nav-link" href="#" data-toggle="modal" data-target="#adminlogin">Admin</a>
                      </li>-->
                        <%} else {%>   
                        <li class="nav-item dropdown mr-3">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-user text-primary"></i> <%=(String) session.getAttribute("username")%>
                            </a>
                            <div class="dropdown-menu">
                                <a href="<%=application.getContextPath()%>/FrontController?url=dashboard&uid=<%=(String)session.getAttribute("uid")%>" class="dropdown-item"><i class="fa fa-user-circle text-primary"></i> Dashboard</a>
                                <form action="Admin" method="post">
                                <input type="hidden" name="command" value="logout">
                                <button type="submit" class="dropdown-item"><i class="fa fa-user-times text-danger"></i> Logout</button>
                                </form>
                            </div>
                        </li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </nav>
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
                <div class="col-lg-8 col-md-8">
                    <% if (category != null) { %>
                    <h3 class="my-4">Category :
                        <small><%=category%></small>
                    </h3>
                    <%} else if (search != null) {%>
                    <h3 class="my-4">Search for : 
                        <small><%=search%></small>
                    </h3>
                    <%} else {%>
                    <h3 class="mt-4">CMS
                        <small>Recent Blog</small>
                    </h3>
                    <hr>
                    <%}%>
                    <%if (!AuthorList.isEmpty()) {
                            for (Author tempAuthor : AuthorList) {%>
                    <div class="card mb-4">
                        <img class="card-img-top img-thumbnail" src="<%=application.getContextPath()%>/Blog?p=<%=tempAuthor.getAuthorBlogId()%>" alt="Card image cap">
                        <div class="card-body">
                            <h2 class="card-title mb-2 text-uppercase"><%=tempAuthor.getAuthorBlogTitle()%></h2>
                            <h6 class="card-subtitle text-muted mb-1">Category : <%=tempAuthor.getAuthorBlogCategory()%></h6>
                            <%if (tempAuthor.getAuthorBlogContent() != null) {
                                    if (tempAuthor.getAuthorBlogContent().length() < 50) {  %>
                            <p class="card-text"><%=tempAuthor.getAuthorBlogContent().substring(0, tempAuthor.getAuthorBlogContent().length()).concat("...")%></p>
                            <%} else {%>
                            <p class="card-text"><%=tempAuthor.getAuthorBlogContent().substring(0, 50).concat("...")%></p>
                            <%}%>
                            <a href="<%=application.getContextPath()%>/Admin?command=authorblog&bid=<%=tempAuthor.getAuthorBlogId()%>" class="btn btn-sm btn-primary">Read More <i class="fa fa-chevron-circle-right"></i></a>
                        </div>
                        <div class="card-footer text-muted">
                            Posted on <%=tempAuthor.getAuthorBlogCreatedAtDate()%> by
                            <a href="<%=application.getContextPath()%>/FrontController?url=authorprofile&uid=<%=tempAuthor.getAuthorId()%>" class="text-capitalize"><%=tempAuthor.getAuthorName()%></a>
                        </div>
                    </div>
                    <% }}} else { %>
                    <div class="card">
                        <div class="card-body">
                            <h2 class="text-capitalize text-info text-center">Sorry result not found</h2>
                        </div>
                    </div>
                    <%}%>
                    <% if (category != null) { %>
                    <nav>
                        <ul class="pagination justify-content-center">
                            <%  String active = "active";
                                for (int i = 1; i <= count; i++) {
                                    if (i == currentPage) {
                            %>
                            <li class="page-item <%=active%>"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=home&category=<%=category%>&page=<%=i%>"><%=i%></a></li>
                                <%} else {%>
                            <li class="page-item"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=home&category=<%=category%>&page=<%=i%>"><%=i%></a></li>
                                <%}}%>
                        </ul>
                    </nav>
                    <%} else if (search != null) {%>
                    <nav>
                        <ul class="pagination justify-content-center">
                            <%  String active = "active";
                                for (int i = 1; i <= count; i++) {
                                    if (i == currentPage) { %>
                            <li class="page-item <%=active%>"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=home&search=<%=search%>&page=<%=i%>"><%=i%></a></li>
                                <%} else {%>
                            <li class="page-item"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=home&search=<%=search%>&page=<%=i%>"><%=i%></a></li>
                                <%} }%>
                        </ul>
                    </nav>   
                    <%} else {%>
                    <nav>
                        <ul class="pagination justify-content-center">
                            <%  String active = "active";
                                for (int i = 1; i <= count; i++) {
                                    if (i == currentPage) { %>
                            <li class="page-item <%=active%>"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=home&page=<%=i%>"><%=i%></a></li>
                                <%} else {%>
                            <li class="page-item"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=home&page=<%=i%>"><%=i%></a></li>
                                <%} }%>
                        </ul>
                    </nav>
                    <%}%>
                </div>
                <div class="col-lg-4 col-md-4">
                    <div class="card my-4">
                        <h5 class="card-header">Search</h5>
                        <div class="card-body">
                            <form action="<%=application.getContextPath()%>/FrontController">
                                <div class="input-group">
                                    <input type="hidden" name="url" value="home">
                                    <input type="text" class="form-control" name="search" placeholder="Search for Blog, User, Category..." required maxlength="40">
                                    <span class="input-group-btn">
                                        <input class="btn btn-secondary" type="submit" value="Search">
                                    </span>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card my-4">
                        <h5 class="card-header">Categories</h5>
                        <div class="card-body">
                            <div class="row">
                                <% List<String> categoryNames = (List<String>) request.getAttribute("categoryName");
                                    if (!categoryNames.isEmpty()) {
                                        for (String tempName : categoryNames) { %>
                                <a href="<%=application.getContextPath()%>/FrontController?url=home&category=<%=tempName%>" class="text-capitalize mx-2 my-1"><%=tempName%></a>
                                <%} }%>
                            </div>
                        </div>
                    </div>
                    <div class="card my-4">
                        <h5 class="card-header">Side Widget</h5>
                        <div class="card-body">
                            You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../jspf/modals.jspf" %>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>