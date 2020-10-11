<%-- 
    Document   : feedback
    Created on : Mar 18, 2020, 6:31:37 PM
    Author     : DELL PC
--%>
<%@page import="java.util.List"%>
<%@page import="com.cms.entities.Feedback"%>
<%@page import="com.cms.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="../jspf/head.jspf" %>
    <%
         String link = (String)request.getAttribute("link");
    %>
    <body id="home">
        <%@include file="../jspf/adminheader.jspf"%>
        <div class="clearfix"></div>
        <section>
            <div class="container-fluid">
                <div class="row pt-md-3 mt-md-5">
                    <div class="col-xl-10 col-lg-9 col-md-8 ml-auto">
                        <section id="acion" class="mb-4">
                            <div class="container-fluid bg-white">
                                <div class="row">
                                    <div class="col-lg-3 col-md-4 mr-lg-auto">
                                        <a href="<%=application.getContextPath()%>/FrontController?url=dashboard" class="btn btn-outline-secondary btn-block">
                                            <i class="fa fa-arrow-left"></i> Dashboard
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </section> 
                    </div>
                </div>
                <div class="row">
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
                        <div class="row pt-md-2 mt-md-2 mb-5 justify-content-center align-items-center">
                            
                            <%
                                List<Feedback> feedList = (List<Feedback>)request.getAttribute("feedlist");
                               if(session.getAttribute("mode").equals("admin")){
                            %>
                            <div class="col-12 bg-white">
                                        <div class="text-center">
                                            <h4>Feedback Table</h4>
                                        </div>
                                        <table class="table table-hover table-responsive">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Feedback</th>
                                                    <th>Status</th>
                                                    <th>Date</th>
                                                    <th>User</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for (Feedback tempFeedback : feedList){ %>
                                                <tr class="align-items-center">
                                                    <td scope="row"><%=tempFeedback.getId()%></td>
                                                    <td><%= tempFeedback.getFeed()%></td>
                                                    <td><%= tempFeedback.getFeedbackStatus()%></td>
                                                    <td><%= tempFeedback.getFeedbackDate()%></td>
                                                    <td><a href="<%=application.getContextPath()%>/FrontController?url=authorprofile&uid="<%= tempFeedback.getUserId()%> class="btn btn-sm btn-info" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>View User</h6>"><i class="fa fa-eye"></i> user</a></td>
                                                    <%
                                                       if(tempFeedback.getFeedbackStatus().equals("Unreviewed")){
                                                    %>
                                                    <td>
                                                        <form action="<%=application.getContextPath()%>/Feedback" class="d-inline-block mr-1" method="post">
                                                            <input type="hidden" name="command" value="review">
                                                            <input type="hidden" name="fid" value="<%=tempFeedback.getId()%>">
                                                            <button type="submit" class="btn btn-outline-warning btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Click to Review</h6>"><i class="fa fa-check"></i> Reviewed</button>
                                                        </form>
                                                         <form action="<%=application.getContextPath()%>/Feedback" class="d-inline-block mr-1" method="post">
                                                            <input type="hidden" name="command" value="deletereview">
                                                            <input type="hidden" name="fid" value="<%=tempFeedback.getId()%>">
                                                            <button type="submit" class="btn btn-outline-danger btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Delete</h6>"><i class="fa fa-trash"></i></button>
                                                        </form>
                                                    </td>
                                                <%}else{%>
                                                <td>
                                                    <form action="<%=application.getContextPath()%>/Feedback" class="d-inline-block mr-1" method="post">
                                                            <input type="hidden" name="command" value="unreview">
                                                            <input type="hidden" name="fid" value="<%=tempFeedback.getId()%>">
                                                            <button type="submit" class="btn btn-outline-danger btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Click to Unreview</h6>"><i class="fa fa-check"></i> Unreviewed</button>
                                                    </form>
                                                            <form action="<%=application.getContextPath()%>/Feedback" class="d-inline-block mr-1" method="post">
                                                            <input type="hidden" name="command" value="deletereview">
                                                            <input type="hidden" name="fid" value="<%=tempFeedback.getId()%>">
                                                            <button type="submit" class="btn btn-outline-danger btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Delete</h6>"><i class="fa fa-trash"></i></button>
                                                    </form>
                                                </td>
                                                <%}%>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div>
                            <%} else{%>
                            <div class="col-xl-6 col-lg-8 col-md-10 col-sm-8 col-xs-4">
                                <div class="card">
                                    <div class="card-body">
                                        <form action="<%=application.getContextPath()%>/Feedback" method="post" id="feedback">
                                            <h4 class="card-title mb-lg-5">User Feedback</h4>
                                            <input type="hidden" name="command" value="add">
                                            <div class="form-group">  
                                                <label for="title">Write us feedback...</label>
                                                <textarea class="form-control" name="feed" id="feedback-name" rows="5"></textarea>
                                                <div id="feedback-msg"></div>
                                            </div> 
                                            <input type="submit" value="Submit" class="btn btn-block btn-outline-success">
                                        </form>
                                    </div>
                                </div>
                            </div>
                           <%}%>
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