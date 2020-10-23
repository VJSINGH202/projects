<%-- 
    Document   : Category
    Created on : Apr 7, 2020, 1:11:09 PM
    Author     : DELL PC
--%>
<%@page import="com.cms.entities.Category"%>
<%@page import="com.cms.entities.Comment"%>
<%@page import="java.util.List"%>
<%
    List<Category> categoryList = (List<Category>) request.getAttribute("categorylist");
    String search = (String) request.getAttribute("search");
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
                                    <div class="col-lg-3 col-md-4">
                                        <a href="<%=application.getContextPath()%>/FrontController?url=dashboard" class="btn btn-outline-secondary btn-block">
                                            <i class="fa fa-arrow-left"></i> Dashboard
                                        </a>
                                    </div>
                                    <div class="col-lg-3 col-md-4 ml-auto">
                                        <a href="#" class="btn btn-outline-warning btn-block" data-toggle="modal" data-target="#addCategoryModal1">
                                            <i class="fa fa-plus"></i> Add Category
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </section>       
                        <section id="category-Search" class="py-1 bg-white">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-6 ml-lg-auto my-md-2 my-sm-2">
                                        <form action="<%=application.getContextPath()%>/FrontController" method="post">
                                            <div class="input-group">
                                                <input type="hidden" name="url" value="category">
                                                <%
                                                    if (search != null) {
                                                %>
                                                <input type="text" name="search" id="search" value="<%=search%>" class="form-control" placeholder="Search..." required>
                                                <%} else {%>
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
                                    <div class="col-10 bg-white mx-auto">

                                        <div class="text-center">
                                            <h4>Category Table</h4>
                                        </div>
                                        <table class="table table-hover" style="overflow-x: scroll">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Category</th>
                                                    <th>Created At</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    if (!categoryList.isEmpty()) {
                                                        for (Category tempCategory : categoryList) {
                                                %>
                                                <tr class="align-items-center">
                                                    <td scope="row"><%= tempCategory.getId()%></td>
                                                    <td><%= tempCategory.getCategoryName()%></td>
                                                    <td><%= tempCategory.getCategoryDate()%></td>
                                                    <td><a href="<%=application.getContextPath()%>/FrontController?url=authorprofile&uid=<%=tempCategory.getUserId()%>" class="btn btn-sm btn-outline-info" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>View User</h6>"><i class="fa fa-eye"></i> User</a>
                                                        <form action="<%=application.getContextPath()%>/Admin" method="post" class="d-inline-block mr-1">
                                                            <input type="hidden" name="command" value="deletecategory">
                                                            <input type="hidden" name="cid" value="<%=tempCategory.getId()%>">
                                                            <button type="submit" class="btn btn-outline-danger btn-sm"
                                                                    data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Delete</h6>"><i class="fa fa-trash-o"></i></button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <%  }
                                                } else {%>
                                                <tr><td colspan="4" class="text-info"> No Category Found</td></tr>
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
        <div class="modal fade" id="addCategoryModal1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!--action="<=application.getContextPath()%>/Admin"-->
                    <form action="<%=application.getContextPath()%>/Admin" method="post" id="admin-category">
                        <div class="modal-header text-white bg-warning">
                            <h4 class="modal-title">Add Category</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="command" value="addadmincategory">
                            <div class="form-group">
                                <label for="name">Category</label>
                                <input type="text" name="category" id="admin-category-name" class="form-control">
                                <div id="category-msg"></div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-warning">Add</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="../jspf/footer-admin.jspf" %>
        <%@include file="../jspf/modals.jspf" %>
        <%@include file="../jspf/script.jspf" %>
    </body>
</html>