<%@page import="com.cms.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%      
    String userid = (String) session.getAttribute("uid");
    int uid = Integer.parseInt(userid);
    User user = (User) request.getAttribute("userinfo");
    int ckeckPhoto = (int)request.getAttribute("photo");
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
                                <section id="acion" class="mb-4 bg-white">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-lg-3 col-md-6 mr-xl-auto">
                                                <a href="<%=application.getContextPath()%>/FrontController?url=dashboard" class="btn btn-outline-secondary btn-block">
                                                    <i class="fa fa-arrow-left"></i> Dashboard
                                                </a>
                                            </div>
                                            <div class="col-lg-3 col-md-6">
                                                <a href="#" class="btn btn-outline-success btn-block" data-toggle="modal" data-target="#passwordModal">
                                                    <i class="fa fa-check"></i> Change Password
                                                </a>
                                            </div>
                                            <div class="col-lg-3 col-md-6">
                                               <!-- <a href="#" class="btn btn-outline-danger btn-block">
                                                    <i class="fa fa-remove"></i> Delete Account
                                                </a>-->
                                                <form action="<%=application.getContextPath()%>/UserController" method="post">
                                                            <input type="hidden" name="command" value="delete">
                                                            <input type="hidden" name="uid" value="<%=user.getUserId()%>">
                                                            <button type="submit" class="btn btn-outline-danger btn-block"><i class="fa fa-remove"></i> Delete Account</button>
                                                        </form>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                                <section id="posts">
                                    <div id="msg" class="text-center container pt-2">
                                        <%  String msg = (String) request.getAttribute("msg");
                                            if (msg != null) {
                                                out.print("<div class='alert alert-info alert-dismissible fade show'>"
                                                        + "<button class='close' data-dismiss='alert' type='button'>"
                                                        + "<span>&times;</span></button>"
                                                        + "<strong>" + msg + "</strong>"
                                                        + "</div>");
                                            } %>
                                    </div>
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-lg-9">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h4>Edit Profile</h4>
                                                    </div>
                                                    <div class="card-body">
                                                        <form action="<%=application.getContextPath()%>/UserController" method="post" id="user-profile">
                                                            <input type="hidden" name="command" value="update">
                                                            <div class="form-group">
                                                                <label for="name">Name</label>
                                                                <input type="text" name="name" id="profile-username" value="<%=user.getUserName()%>" class="form-control">
                                                                <small id="user-profile-msg"></small>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="name">Email</label>
                                                                <input type="email" name="email" id="profile-email" value="<%=user.getEmail()%>" class="form-control">
                                                                <small id="email-profile-msg"></small>
                                                            </div>
                                                            <div class="form-group">  
                                                                <label for="title">Bio</label>
                                                                <textarea class="form-control" name="bio" id="profile-bio"><%=user.getBio()%></textarea>
                                                                <small id="bio-profile-msg"></small>
                                                            </div> 
                                                            <input type="submit" value="Submit" class="btn btn-block btn-outline-success">
                                                        </form>
                                                    </div>
                                                </div>
                                            </div> 
                                            <div class="col-lg-3 my-lg-0 my-5 col-md-8 col-sm-10 mx-md-auto mx-sm-auto mx-sm-auto">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h3>My Avatar</h3>
                                                        <%  if (ckeckPhoto > 0) {%>
                                                        <img src='<%=application.getContextPath()%>/Photo?n=<%=uid%>' class="rounded d-block img-thumbnail mb-3">
                                                        <button class="btn btn-outline-danger btn-block" data-toggle="modal" data-target="#deletePhotoModal">Delete Photo</button>
                                                        <% } else {%><img src="<%=application.getContextPath()%>/images/avatar.png" alt="" class="d-block img-fluid mb-3">
                                                        <button class="btn btn-outline-primary btn-block" data-toggle="modal" data-target="#photoModal">Edit Image</button>
                                                        <%}%>      
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