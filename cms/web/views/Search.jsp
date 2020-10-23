<%-- 
    Document   : Search
    Created on : Apr 19, 2020, 10:15:04 AM
    Author     : DELL PC
--%>
<%@page import="com.cms.entities.Author"%>
<%@page import="com.cms.entities.User"%>
<%
    List<Author> AuthorList = (List<Author>) request.getAttribute("authorbloglist");
    int blimit = (int) request.getAttribute("blimit");
    String search = (String) request.getAttribute("search");
    int blogCount = (int) request.getAttribute("blogcount");
    int count = (int) Math.ceil((double) blogCount / blimit);
    int currentPage = (int) request.getAttribute("page");
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
                        <% if (search != null) { %>
                        <h3 class="text-secondary pt-md-5 mt-md-3 px-3">Search for : <%=search%></h3>
                        <%}%>
                        <div class="row mb-2 px-3">
                            <% if (!AuthorList.isEmpty()) {
                                        for (Author tempAuthor : AuthorList) {%>
                            <div class="col-xl-3 col-lg-4 col-sm-6 p-2">
                                <div class="card mb-4">
                                    <img class="card-img-top img-thumbnail" src="<%=application.getContextPath()%>/Blog?p=<%=tempAuthor.getAuthorBlogId()%>" alt="Card image cap">
                                    <div class="card-body">
                                        <h3 class="card-title mb-2 text-uppercase"><%=tempAuthor.getAuthorBlogTitle()%></h3>
                                        <h6 class="card-subtitle text-muted mb-1">Category : <%=tempAuthor.getAuthorBlogCategory()%></h6>
                                        <%if (tempAuthor.getAuthorBlogContent() != null) {
                                                if (tempAuthor.getAuthorBlogContent().length() < 50) { %>
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
                            </div>
                            <% }}} else { %>
                            <div class="col-xl-10 col-sm-6 p-2 mx-auto">
                                <div class="card">
                                    <div class="card-body">
                                        <h2 class="text-capitalize text-info text-center">Sorry result not found</h2>
                                    </div>
                                </div>
                            </div>
                            <%}%>   
                        </div>
                        <nav>
                            <ul class="pagination justify-content-center">
                                <%  String active = "active";
                                    for (int i = 1; i <= count; i++) {
                                        if (i == currentPage) {%>
                                <li class="page-item <%=active%>"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=search&search=<%=search%>&page=<%=i%>"><%=i%></a></li>
                                    <%} else {%>
                                <li class="page-item"><a class="page-link" href="<%=application.getContextPath()%>/FrontController?url=search&search=<%=search%>&page=<%=i%>"><%=i%></a></li>
                                    <%} }%>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="../jspf/footer-admin.jspf" %>
        <%@include file="../jspf/modals.jspf" %>
        <%@include file="../jspf/script.jspf" %>
    </body>
</html>