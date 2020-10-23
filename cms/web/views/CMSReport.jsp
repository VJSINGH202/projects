<%-- 
    Document   : CMSReport
    Created on : May 25, 2020, 10:18:51 AM
    Author     : DELL PC
--%>
<%@page import="com.cms.entities.Comment"%>
<%@page import="com.cms.entities.Feedback"%>
<%@page import="com.cms.entities.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.cms.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<User> userReportList = (List<User>) request.getAttribute("userreport");
    List<Author> authorReportList = (List<Author>) request.getAttribute("blogreport");
    List<Feedback> feedbackReportList = (List<Feedback>) request.getAttribute("feedbackReport");
    List<Comment> commentReportList = (List<Comment>) request.getAttribute("commentReport");
%>
<html>
    <%@include file="../jspf/head.jspf"%>
    <body>
        <header id="main-header" class="py-2 bg-danger text-white mb-1">
            <div class="container-fluid">
                <div class="row">
                    <div class="col">
                        <h2 class="ml-lg-3"><i class="fa fa-user"></i> CMS REPORT</h2>
                    </div>
                </div>
            </div>
        </header>
        <section id="acion" class="mb-4 bg-white">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-2 col-md-4 mx-lg-auto">
                        <a href="<%=application.getContextPath()%>/FrontController?url=dashboard" class="btn btn-outline-secondary btn-block">
                            <i class="fa fa-arrow-left"></i> Dashboard
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-4 mx-lg-auto">
                        <a href="<%=application.getContextPath()%>/FrontController?url=cmsreport&report=user" class="btn btn-outline-danger btn-block">
                            <i class="fa fa-eye"></i> User Report
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-4 mx-lg-auto">
                        <a href="<%=application.getContextPath()%>/FrontController?url=cmsreport&report=blog" class="btn btn-outline-danger btn-block">
                            <i class="fa fa-eye"></i> Blog Report
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-4 mx-lg-auto">
                        <a href="<%=application.getContextPath()%>/FrontController?url=cmsreport&report=feedback" class="btn btn-outline-danger btn-block">
                            <i class="fa fa-eye"></i> Feedback Report
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-4 mx-lg-auto">
                        <a href="<%=application.getContextPath()%>/FrontController?url=cmsreport&report=comment" class="btn btn-outline-danger btn-block">
                            <i class="fa fa-eye"></i> Comment Report
                        </a>
                    </div>
                </div>
            </div>
        </section>
        <section id="report">
            <div class="row pt-md-3 mt-md-2 mb-5">  
                <div class="col-sm-10 bg-white mx-auto">
                    <%
                        if (userReportList != null) {
                    %>
                    <div class="text-center">
                        <h4 class="d-inline-block">User Report&nbsp;&nbsp;&nbsp;&nbsp;</h4><span class="text-danger ml-lg-5" onclick="print()"><i class="fa fa-print"></i>Print</span>
                    </div>
                    <table class="table table-hover" style="overflow-x: scroll !important">
                        <thead>
                            <tr>
                                <th>User ID</th>
                                <th>User Name</th>
                                <th>User Mode</th>
                                <th>User Email</th>
                                <th>Register_at</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (User tempUserReport : userReportList) {%>
                            <tr class="align-items-center">
                                <td><%=tempUserReport.getUserId()%></td>
                                <td><%=tempUserReport.getUserName()%></td>
                                <td><%=tempUserReport.getUmode()%></td>
                                <td><%=tempUserReport.getEmail()%></td>
                                <td><%=tempUserReport.getDateOfRegister()%></td>
                            </tr>
                            <%  }%>
                        </tbody>
                    </table>
                    <br>
                    <%}%>
                    <%
                        if (authorReportList != null) {
                    %>
                    <div class="text-center">
                        <h4 class="d-inline-block">Blog Report&nbsp;&nbsp;&nbsp;&nbsp;</h4><span class="text-danger ml-lg-5" onclick="print()"><i class="fa fa-print"></i>Print</span>
                    </div>
                    <table class="table table-hover" style="overflow-x: scroll !important">
                        <thead>
                            <tr>
                                <th>Blog ID</th>
                                <th>Author Name</th>
                                <th>Author Email</th>
                                <th>Blog Title</th>
                                <th>Blog Category</th>
                                <th>Created_at</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Author tempAuthorReport : authorReportList) {
                                    tempAuthorReport.getAuthorName();
                                    tempAuthorReport.getAuthorEmail();
                                    tempAuthorReport.getAuthorBlogId();
                                    tempAuthorReport.getAuthorBlogTitle();
                                    tempAuthorReport.getAuthorBlogCategory();
                                    tempAuthorReport.getAuthorBlogCreatedAtDate();
                            %>
                            <tr class="align-items-center">
                                <td><%=tempAuthorReport.getAuthorBlogId()%></td>
                                <td><%=tempAuthorReport.getAuthorName()%></td>
                                <td><%=tempAuthorReport.getAuthorEmail()%></td>
                                <td><%=tempAuthorReport.getAuthorBlogTitle()%></td>
                                <td><%=tempAuthorReport.getAuthorBlogCategory()%></td>
                                <td><%=tempAuthorReport.getAuthorBlogCreatedAtDate()%></td>
                            </tr>
                            <%  }%>
                        </tbody>
                    </table>
                    <br>
                    <%}%>
                    <%
                        if (feedbackReportList != null) {
                    %>
                    <div class="text-center">
                        <h4 class="d-inline-block">Feedback Report&nbsp;&nbsp;&nbsp;&nbsp;</h4><span class="text-danger ml-lg-5" onclick="print()"><i class="fa fa-print"></i>Print</span>
                    </div>
                    <table class="table table-hover" style="overflow-x: scroll !important">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>User Name</th>
                                <th>User Email</th>
                                <th>Feedback</th>
                                <th>Status</th>
                                <th>Created At</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Feedback tempFeedbackReport : feedbackReportList) {
                                    tempFeedbackReport.getId();
                                    tempFeedbackReport.getFeed();
                                    tempFeedbackReport.getFeedbackStatus();
                                    tempFeedbackReport.getFeedbackDate();
                                    tempFeedbackReport.getUserName();
                                    tempFeedbackReport.getUserMode();
                            %>
                            <tr class="align-items-center">
                                <td><%=tempFeedbackReport.getId()%></td>
                                <td><%=tempFeedbackReport.getUserName()%></td>
                                <td><%=tempFeedbackReport.getUserMode()%></td>
                                <td><%=tempFeedbackReport.getFeed()%></td>
                                <td><%=tempFeedbackReport.getFeedbackStatus()%></td>
                                <td><%=tempFeedbackReport.getFeedbackDate()%></td>
                            </tr>
                            <%  }%>
                        </tbody>
                    </table>
                    <br>
                    <%}%>
                    <%
                        if (commentReportList != null) {
                    %>
                    <div class="text-center">
                        <h4 class="d-inline-block">Comment Report&nbsp;&nbsp;&nbsp;&nbsp;</h4><span class="text-danger ml-lg-5" onclick="print()"><i class="fa fa-print"></i>Print</span>
                    </div>
                    <table class="table table-hover" style="overflow-x: scroll !important">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>User Name</th>
                                <th>User Mode</th>
                                <th>User Comment</th>
                                <th>Status</th>
                                <th>Created At</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Comment tempCommentReport : commentReportList) {%>
                            <tr class="align-items-center">
                                <td><%=tempCommentReport.getCommentId()%></td>
                                <td><%=tempCommentReport.getUserName()%></td>
                                <td><%=tempCommentReport.getUserMode()%></td>
                                <td><%=tempCommentReport.getCommentDescription()%></td>
                                <td><%=tempCommentReport.getStatus()%></td>
                                <td><%=tempCommentReport.getCommentDate()%></td>
                            </tr>
                            <%  }%>
                        </tbody>
                    </table>
                    <br>
                    <%}%>
                </div>
            </div>
        </section>                   
    </body>
</html>