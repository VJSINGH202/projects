<%-- 
    Document   : Users
    Created on : Apr 2, 2020, 10:22:55 AM
    Author     : DELL PC
--%>
<%@page import="java.util.List"%>
<%@page import="com.cms.entities.User"%>
<%
    String userid = (String) session.getAttribute("uid");
    List<User> userList = (List<User>) request.getAttribute("userlist");
     String link =(String)request.getAttribute("link");
    String search =(String)request.getAttribute("search");
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
                                        <a href="<%=application.getContextPath()%>/FrontController?url=userblog" class="btn btn-outline-info btn-block">
                                            <i class="fa fa-edit"></i> User Post
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
                                    <div class="col-lg-3 col-md-6">
                                        <a href="#" class="btn btn-outline-success btn-block" data-toggle="modal" data-target="#asignup">
                                            <i class="fa fa-plus"></i> Add User
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section id="userSearch" class="py-1 bg-white">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-6 ml-lg-auto my-md-2 my-sm-2">
                                        <form action="<%=application.getContextPath()%>/FrontController" method="post">
                                            <div class="input-group">
                                                <input type="hidden" name="url" value="user">
                                                <%
                                                  if(search!=null){
                                                %>
                                                <input type="text" name="search" id="search" value="<%=search%>" class="form-control" placeholder="Search..." required>
                                                <%} else{%>
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
                                            <h4>Users Table</h4>
                                        </div>
                                        <table class="table table-hover table-responsive">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>UserName</th>
                                                    <th>UserMode</th>
                                                    <th>Email</th>
                                                    <th>Register_at</th>
                                                    <th colspan="2">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody> 
                                                <% if(!userList.isEmpty()){
                                                    for (User tempUsers : userList) {%>
                                                <tr class="align-items-center">
                                                    <td scope="row"><%= tempUsers.getUserId()%></td>
                                                    <td><%= tempUsers.getUserName()%></td>
                                                    <td><%=  tempUsers.getUmode()%></td>
                                                    <td><%=  tempUsers.getEmail()%></td>
                                                    <td><%=  tempUsers.getDateOfRegister()%></td>
                                                    <td><a href="<%=application.getContextPath()%>/FrontController?url=authorprofile&uid=<%=tempUsers.getUserId()%>" class="btn btn-sm btn-outline-info" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Click to View User</h6>"><i class="fa fa-eye"></i> User</a>
                                                        <form action="<%=application.getContextPath()%>/FrontController" method="post" class="d-inline-block ml-1">
                                                            <input type="hidden" name="url" value="profile">
                                                            <input type="hidden" name="uid" value="<%=tempUsers.getUserId()%>">
                                                            <button type="submit" class="btn btn-outline-success btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Edit</h6>"><i class="fa fa-edit"></i></button>
                                                        </form>
                                                        <form action="<%=application.getContextPath()%>/UserController" method="post" class="d-inline-block ml-1">
                                                            <input type="hidden" name="command" value="delete">
                                                            <input type="hidden" name="uid" value="<%=tempUsers.getUserId()%>">
                                                            <button type="submit" class="btn btn-outline-danger btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Delete</h6>"><i class="fa fa-trash-o"></i></button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <% }} else{%>
                                                <tr><td colspan="6" class="text-info">NO users Found</td></tr>
                                                <%}%>
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