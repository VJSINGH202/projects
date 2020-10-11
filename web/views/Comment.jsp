<%-- 
    Document   : Comment
    Created on : Mar 27, 2020, 4:02:10 PM
    Author     : DELL PC
--%>
<%@page import="com.cms.entities.Comment"%>
<%@page import="java.util.List"%>
          <%  
                 List<Comment> commentList=(List<Comment>)request.getAttribute("commentlist");
                 String link =(String)request.getAttribute("link");
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
                               String msg =(String)request.getAttribute("msg");
                               if(msg!=null)
                               out.print("<div class='alert alert-info alert-dismissible fade show'>"
                                       + "<button class='close' data-dismiss='alert' type='button'>"
                                       + "<span>&times;</span></button>"
                                       + "<strong>"+msg+"</strong>"
                                       +"</div>");
                            %>
                             </div>
                            <section id="acion" class="mb-4 bg-white">
                            <div class="container-fluid">
                              <div class="row">
                                <div class="col-lg-3 col-md-4">
                                  <a href="<%=application.getContextPath()%>/FrontController?url=dashboard" class="btn btn-outline-secondary btn-block">
                                    <i class="fa fa-arrow-left"></i> Dashboard
                                  </a>
                                </div>
                              </div>
                            </div>
                          </section>
                         <div class="row">
                    <div class="col-xl-10 mx-auto">
                       <div class="row pt-md-3 mt-md-2 mb-5">  
                           <div class="col-md-10 bg-white mx-auto">
                            <div class="text-center">
                              <h4>Comment Table</h4>
                            </div>
                            <table class="table table-hover table-responsive">
                              <thead>
                                <tr>
                                  <th>#</th>
                                  <th>Comment</th>
                                  <th>Status</th>
                                  <th>Blog</th>
                                  <th>User</th>
                                  <th>Action</th>
                                </tr>
                              </thead>
                              <tbody>
                                  <% for(Comment tempComment : commentList) { %>
                                  <tr class="align-items-center">
                                 <td scope="row"><%= tempComment.getCommentId()%></td>
                                 <td><%= tempComment.getCommentDescription()%></td>
                                 <td><%= tempComment.getStatus()%></td>
                                  <td><a href="<%=application.getContextPath()%>/Admin?command=authorblog&bid=<%=tempComment.getBlogId()%>" class="btn btn-outline-success btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>View Blog</h6>"><i class="fa fa-eye"></i> Blog</a></td>
                                  <td><a href="<%=application.getContextPath()%>/FrontController?url=authorprofile&uid=<%=tempComment.getUserId()%>" class="btn btn-outline-success btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>View Blog</h6>"><i class="fa fa-eye"></i> User</a></td>
                                  <% if(tempComment.getStatus().equals("UnApproved")){ %>
                                      <td><form action="<%=application.getContextPath()%>/Comment" method="post" class="d-inline-block mr-1">
                                              <input type="hidden" name="command" value="approvecomment">
                                              <input type="hidden" name="cid" value="<%=tempComment.getCommentId()%>">
                                              <button type="submit" class="btn btn-outline-success btn-sm"
                                               data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Click to Approve</h6>"><i class="fa fa-check"></i> Approved</button>
                                          </form>
                                          <form action="<%=application.getContextPath()%>/Comment" method="post" class="d-inline-block mr-1">
                                              <input type="hidden" name="command" value="deletecomment">
                                              <input type="hidden" name="cid" value="<%=tempComment.getCommentId()%>">
                                              <button type="submit" class="btn btn-outline-danger btn-sm"
                                               data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Delete</h6>"><i class="fa fa-trash-o"></i></button>
                                          </form>
                                      </td>
                                  <%}else{%>
                                  <td><form action="<%=application.getContextPath()%>/Comment" method="post" class="d-inline-block mr-1">
                                              <input type="hidden" name="command" value="unapprovecomment">
                                              <input type="hidden" name="cid" value="<%=tempComment.getCommentId()%>">
                                              <button type="submit" class="btn btn-outline-danger btn-sm"
                                                data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Click to UnApprove</h6>"><i class="fa fa-close"></i> UnApproved</button>
                                          </form>
                                          <form action="<%=application.getContextPath()%>/Comment" method="post" class="d-inline-block mr-1">
                                              <input type="hidden" name="command" value="deletecomment">
                                              <input type="hidden" name="cid" value="<%=tempComment.getCommentId()%>">
                                              <button type="submit" class="btn btn-outline-danger btn-sm"
                                                      data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>Delete</h6>"><i class="fa fa-trash-o"></i></button>
                                          </form>
                                  </td><%}%>
                                  </tr> <%}%>
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