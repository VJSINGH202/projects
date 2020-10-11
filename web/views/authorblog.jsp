<%-- 
    Document   : authorblog
    Created on : Mar 22, 2020, 2:47:04 PM
    Author     : DELL PC
--%>
<%@page import="com.cms.entities.Comment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.cms.entities.Author"%>
<!DOCTYPE html>
<%
                  Author author = (Author)request.getAttribute("authorblog");
                  List<Comment> blogCommentList = (List<Comment>)request.getAttribute("blogcomments");
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
                            <a class="nav-link" href="#">Home</a>
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
                    <h1 class="text-uppercase"><%=author.getAuthorBlogTitle()%></h1>
                    <p class="lead mb-0 d-inline-block">by
                        <a href="<%=application.getContextPath()%>/FrontController?url=authorprofile&uid=<%=author.getAuthorId()%>"><%=author.getAuthorName()%></a>            
                    </p>
                    <small class="text-muted ml-3 d-inline-block"> Category : <%=author.getAuthorBlogCategory()%></small><br>
                    <p class="d-inline-block mb-0">Posted on <%=author.getAuthorBlogCreatedAtDate()%></p>
                    <form id="addlike" class="d-inline-block ml-3">
                        <input type="hidden" name="bid" value="<%=author.getAuthorBlogId()%>">
                        <input type="hidden" name="command" value="like">
                        <button type="submit" id="btn-like" class="btn btn-sm btn-outline-primary"><i class="fa fa-thumbs-o-up"></i></button>
                    </form>
                    <form id="addfavourite" class="d-inline-block">
                        <input type="hidden" name="bid" value="<%=author.getAuthorBlogId()%>">
                        <input type="hidden" name="command" value="favourite">
                        <button type="submit" id="btn-favourite" class="btn btn-sm btn-outline-warning"><i class="fa fa-heart-o"></i></button>
                    </form>
                    <form id="addreport" class="d-inline-block">
                        <input type="hidden" name="bid" value="<%=author.getAuthorBlogId()%>">
                        <input type="hidden" name="command" value="report">
                        <button type="submit" id="btn-report" class="btn btn-sm btn-outline-danger"><i class="fa fa-ban"></i></button>
                    </form>
                    <div id="amsg" class="mx-auto mt-2"></div>
                    <hr>
                    <div class="card">
                        <img class="img-thumbnail rounded" src="<%=application.getContextPath()%>/Blog?p=<%=author.getAuthorBlogId()%>" alt="<%=author.getAuthorBlogTitle()%>">
                    </div>
                    <hr>
                    <div class="content">
                        <div class="card"> 
                            <div class="card-body">
                                <%= author.getAuthorBlogContent()%>
                            </div>
                        </div> 
                        <hr>
                    </div>
                    <div class="card my-4">
                        <h5 class="card-header">Leave a Comment:</h5>
                        <div class="card-body">
                            <div id="cmsg"></div>
                            <form id="addcomment" method="post">
                                <input type="hidden" name="command" value="addcomment">
                                <input type="hidden" name="bid" value="<%=author.getAuthorBlogId()%>">
                                <div class="form-group">
                                    <textarea id="commentTxt" class="form-control" rows="3" name="comment"></textarea>
                                    <div id="commentmsg"></div>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                   <hr>                    
                   <%   if(!blogCommentList.isEmpty()){ %>
                        <h4 class="mb-4">Comments :</h4>
                        <% for(Comment comment : blogCommentList){ %>
                    <div class="media mb-4 bg-white p-3 comment-border">        
                        <img class="d-flex mr-3 rounded-circle" src='<%=application.getContextPath()%>/Photo?n=<%=comment.getUserId()%>' width="50" alt="${comment.getUserName()}">
                        <div class="media-body">
                            <h5 class="mt-0"><a href="#<%=comment.getUserId()%>"><%=comment.getUserName()%></a></h5>
                            <small class="text-muted mb-2 d-inline-block"><%=comment.getCommentDate()%></small><br>
                            <div><%=comment.getCommentDescription()%></div>
                        </div>
                    </div>
                         <%}}%>
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
                        <h5 class="card-header">Categories</h5>
                        <div class="card-body">
                            <div class="row">
                                <%  List<String> categoryNames =(List<String>)request.getAttribute("categoryname");
                                   if(!categoryNames.isEmpty()){
                                       for(String tempName : categoryNames){ %>
                                <a href="<%=application.getContextPath()%>/FrontController?url=home&category=<%=tempName%>" class="text-capitalize mx-2 my-1"><%=tempName%></a>
                                <%}}%>
                            </div>
                        </div>
                    </div>
                    <div class="card my-4">
                        <h5 class="card-header">About Author</h5>
                        <div class="card-body">
                            <img src="<%=application.getContextPath()%>/Photo?n=<%=author.getAuthorId()%>" alt="<%=author.getAuthorName()%>" class="img-thumbnail mb-2">
                            <h1>Author : <span class="text-capitalize"><%=author.getAuthorName()%></span></h1>
                            <p>Email : <%=author.getAuthorEmail()%></p>
                            <blockquote class="blockquote mt-0">
                                <p>Biblography</p>
                                <footer class="blockquote-footer"><%=author.getAuthorBiblography()%></footer>
                            </blockquote>       
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../jspf/modals.jspf" %>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
