<%-- 
    Document   : forgotPassword
    Created on : Apr 6, 2020, 12:17:00 PM
    Author     : DELL PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="../jspf/head.jspf" %>
    <body class="p-fix">
        <nav class="navbar navbar-expand-md navbar-light bg-white buttom-border fixed-top">
            <div class="container">
                <a class="navbar-brand" href="#">CMS</a>
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
        <header id="main-header" class="py-2 bg-danger text-white">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h2><i class="fa fa-user"></i> CMS Password Recovery </h2>
                    </div>
                </div>
            </div>
        </header>
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-md-6 mx-auto">
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
                    <div class="card my-5">
                        <div class="card-header">
                            <h4>Forgot Password</h4>
                        </div>
                        <div class="card-body">
                            <form action="<%=application.getContextPath()%>/Admin" method="post" id="forgot-form" >
                                <input type="hidden" name="command" value="forgotpassword">
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="forgot-email" placeholder="Enter your email...">
                                    <small id="forgot-email-msg"></small>
                                </div>
                                <input type="submit" class="btn btn-danger btn-block" value="Submit">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../jspf/modals.jspf" %>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>