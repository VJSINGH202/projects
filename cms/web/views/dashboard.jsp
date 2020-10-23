<%-- 
    Document   : dashboard
    Created on : Mar 18, 2020, 5:11:15 PM
    Author     : DELL PC
--%>
<%@page import="com.cms.entities.User"%>
<%
    int feedCount = 0, likeCount = 0, blogcount = 0, commentCount = 0, usercount = 0, blogsCount = 0, feedbackCount = 0, commentsCount = 0, categoryCount = 0;
    if (session.getAttribute("mode").equals("user")) {
        feedCount = (int) request.getAttribute("dashboardfeedcount");
        likeCount = (int) request.getAttribute("dashboardlikecount");
        blogcount = (int) request.getAttribute("dashboardblogcount");
        commentCount = (int) request.getAttribute("dashboardcomments");
        String link =(String)request.getAttribute("link");
    } else {
        String link =(String)request.getAttribute("link");
        feedCount = (int) request.getAttribute("dashboardfeedcount");
        likeCount = (int) request.getAttribute("dashboardlikecount");
        blogcount = (int) request.getAttribute("dashboardblogcount");
        commentCount = (int) request.getAttribute("dashboardcomments");
        usercount = (int) request.getAttribute("usercount");
        blogsCount = (int) request.getAttribute("blogcount");
        feedbackCount = (int) request.getAttribute("feedbackcount");
        commentsCount = (int) request.getAttribute("commentcount");
        categoryCount = (int) request.getAttribute("categorycount");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="../jspf/head.jspf" %>
    <body id="home">
        <%@include file="../jspf/adminheader.jspf"%>
        <section style="padding-top: -50px;">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-10 col-lg-9 col-md-8 ml-auto">
                        <div class="row pt-md-5 mt-md-3 mb-5">
                            <% if (session.getAttribute("mode").equals("admin")) {%>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-pencil-square-o fa-3x text-info"></i>
                                            <div class="text-right text-secondary">
                                                <h5>Total Blog</h5>
                                                <h3><%=blogsCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-comments-o fa-3x text-primary"></i>
                                            <div class="text-right text-secondary">
                                                <h5>Total Comments</h5>
                                                <h3><%=commentsCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-feed fa-3x text-danger"></i>
                                            <div class="text-right text-secondary">
                                                <h5>Total Feedback</h5>
                                                <h3><%=feedbackCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-folder fa-3x text-secondary"></i>
                                            <div class="text-right text-secondary">
                                                <h5>Total Category</h5>
                                                <h3><%=categoryCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-users fa-3x text-danger"></i>
                                            <div class="text-right text-secondary">
                                                <h5>Total Users</h5>
                                                <h3><%=usercount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-edit fa-3x text-success"></i>
                                            <div class="text-right text-secondary">
                                                <h5>My blog</h5>
                                                <h3><%=blogcount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-comments fa-3x text-info"></i>
                                            <div class="text-right text-secondary">
                                                <h5>My Blog Comments</h5>
                                                <h3><%=commentCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-feed fa-3x text-warning"></i>
                                            <div class="text-right text-secondary">
                                                <h5>My Feedback</h5>
                                                <h3><%=feedCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-thumbs-up fa-3x text-primary"></i>
                                            <div class="text-right text-secondary">
                                                <h5>My Blog Likes</h5>
                                                <h3><%=likeCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <%} else {%>          
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-edit fa-3x text-success"></i>
                                            <div class="text-right text-secondary">
                                                <h5>My blog</h5>
                                                <h3><%=blogcount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-comments fa-3x text-info"></i>
                                            <div class="text-right text-secondary">
                                                <h5>My Blog Comments</h5>
                                                <h3><%=commentCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-feed fa-3x text-warning"></i>
                                            <div class="text-right text-secondary">
                                                <h5>My Feedback</h5>
                                                <h3><%=feedCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-sm-6 p-2">
                                <div class="card card-common">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between">
                                            <i class="fa fa-thumbs-up fa-3x text-primary"></i>
                                            <div class="text-right text-secondary">
                                                <h5>My Blog Likes</h5>
                                                <h3><%=likeCount%></h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <i class="fa fa-refresh"></i>
                                        <span>Updated Now</span>
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

