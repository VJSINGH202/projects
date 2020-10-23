<%-- 
    Document   : AuthorProfile
    Created on : Apr 9, 2020, 11:56:14 AM
    Author     : DELL PC
--%>

<%@page import="com.cms.entities.User"%>
<%@page import="com.cms.entities.Author"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Author> authorList = (List<Author>) request.getAttribute("authorbloglist");
    int uid = Integer.parseInt((String) request.getAttribute("uid"));
    User user = (User) request.getAttribute("getuserinfo");
    int feedCount = (int) request.getAttribute("userfeedcount");
    int likeCount = (int) request.getAttribute("userlikecount");
    int blogcount = (int) request.getAttribute("userblogcount");
    int commentCount = (int) request.getAttribute("usercomments");
%>
<!DOCTYPE html>
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
                        <% if (session.getAttribute("uid") == null) { %>
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
                                <a href="#" class="dropdown-item"><i class="fa fa-user-times text-danger"></i> Logout</a>
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
            <div class="row mt-3">
                <div class="col-lg-8 col-md-8">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card mb-5">
                                <img src='<%=application.getContextPath()%>/Photo?n=<%=uid%>' class="card-img-top rounded img-thumbnail">
                                    <div class="card-body">
                                         <h4 class="text-capitalize mb-3 card-title"><%=user.getUmode()%> : <%=user.getUserName()%></h4>
                                    <h6 class="card-subtitle text-muted mb-3"><span>Email : <%=user.getEmail()%></span></h6>  
                                    <hr>
                                        <p class="card-text"><%=user.getBio()%></p>
                                    </div>
                                    <div class="card-footer text-muted">
                                   Register At <%=user.getDateOfRegister()%>
                                </div>
                        </div>
                        </div>
                    </div>
                    <%
                        if (!authorList.isEmpty()) {
                    %>
                    <h3 class="text-muted text-center mb-5">Author Blog</h3>
                    <div class="row">
                        <%
                            for (Author tempAuthor : authorList) {
                        %>
                        <div class="col-lg-4 col-md-6 mb-3">
                            <div class="card">
                                <img class="card-img-top img-thumbnail" src="<%=application.getContextPath()%>/Blog?p=<%=tempAuthor.getAuthorBlogId()%>" alt="Card image cap">
                                <div class="card-body">
                                    <h4 class="card-title text-uppercase"><%=tempAuthor.getAuthorBlogTitle()%></h4>
                                    <h6 class="card-subtitle text-muted mb-1">Category : <%=tempAuthor.getAuthorBlogCategory()%></h6>
                                    <%
                                        if (tempAuthor.getAuthorBlogContent() != null) {
                                            if (tempAuthor.getAuthorBlogContent().length() < 50) {
                                    %>
                                    <p class="card-text"><%=tempAuthor.getAuthorBlogContent().substring(0, tempAuthor.getAuthorBlogContent().length()).concat("...")%></p>
                                    <%} else {%>
                                    <p class="card-text"><%=tempAuthor.getAuthorBlogContent().substring(0, 50).concat("...")%></p>
                                    <%}
                                }%>
                                    <a href="<%=application.getContextPath()%>/Admin?command=authorblog&bid=<%=tempAuthor.getAuthorBlogId()%>" class="btn btn-sm btn-primary">Read More <i class="fa fa-chevron-circle-right"></i></a>
                                </div>
                                <div class="card-footer text-muted">
                                    <small>Posted on <%=tempAuthor.getAuthorBlogCreatedAtDate()%></small>
                                </div>
                            </div>
                        </div>
                        
                        <%}%>
                         </div>
                        <%}%>
                   
                </div>
                <div class="col-lg-4 col-md-4">
                    <div class="card my-4">
                        <h5 class="card-header">Search</h5>
                        <div class="card-body">
                            <form action="<%=application.getContextPath()%>/FrontController">
                                <div class="input-group">
                                    <input type="hidden" name="url" value="home">
                                    <input type="text" class="form-control" name="search" placeholder="Search for..." maxlength="40" required>
                                    <span class="input-group-btn">
                                        <input class="btn btn-secondary" type="submit" value="Search">
                                    </span>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card my-4">
                        <h5 class="card-header">Blog Info</h5>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-lg-6 mb-4">
                                    <div class="card bg-white card-common"> 
                                        <i class="fa fa-pencil-square-o fa-3x text-success d-block m-auto py-2"></i>
                                        <p class="card-text text-center text-capitalize mb-1">Post : <%=blogcount%></p>
                                    </div>
                                </div>
                                <div class="col-lg-6 mb-4">
                                    <div class="card bg-white card-common"> 
                                        <i class="fa fa-comments fa-3x text-info d-block m-auto py-2"></i>
                                        <p class="card-text text-center text-capitalize mb-1">Comment : <%=commentCount%></p>
                                    </div>
                                </div>
                                <div class="col-lg-6 mb-4">
                                    <div class="card bg-white card-common"> 
                                        <i class="fa fa-thumbs-up fa-3x text-primary d-block m-auto py-2"></i>
                                        <p class="card-text text-center text-capitalize mb-1">Likes : <%=likeCount%></p>
                                    </div>
                                </div>
                                <div class="col-lg-6 mb-4">
                                    <div class="card bg-white card-common"> 
                                        <i class="fa fa-feed fa-3x text-danger d-block m-auto py-2"></i>
                                        <p class="card-text text-center text-capitalize mb-1">Feed : <%=feedCount%></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../jspf/modals.jspf" %>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>

