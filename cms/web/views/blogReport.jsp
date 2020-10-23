<%-- 
    Document   : blogReport
    Created on : Mar 30, 2020, 9:37:01 AM
    Author     : DELL PC
--%>
<%@page import="com.cms.entities.Report"%>
<%@page import="java.util.List"%>
          <%  
                 String userid =(String)session.getAttribute("uid");
                 String link =(String)request.getAttribute("link");
                 List<Report> reportList=(List<Report>)request.getAttribute("reportList");
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
                                <div class="col-lg-3 col-md-4 ml-auto">
                                 <a href="<%=application.getContextPath()%>/FrontController?url=cmsreport" class="btn btn-outline-success btn-block">
                                   <i class="fa fa-plus"></i> CMS Report
                                 </a>
                               </div>
                              </div>
                            </div>
                          </section>       
                         <div class="row">
                    <div class="col-xl-10 mx-auto">
                       <div class="row pt-md-3 mt-md-2 mb-5">  
                           <div class="col-sm-10 bg-white mx-auto">
                        
                            <div class="text-center">
                              <h4>Blog Report Table</h4>
                            </div>
                            <table class="table table-hover table-responsive">
                              <thead>
                                <tr>
                                  <th>#</th>
                                  <th>User</th>
                                  <th>Blog</th>
                                  <th>Date</th>
                                  <th>Status</th>
                                  <th>Action</th>
                                </tr>
                              </thead>
                              <tbody>
                                  <% for(Report tempReport : reportList) { %>
                                  <tr class="align-items-center">
                                 <td scope="row"><%= tempReport.getId()%></td>
                                 <td><a href="<%=application.getContextPath()%>/FrontController?url=authorprofile&uid=<%=tempReport.getUserId()%>" class="btn btn-outline-info btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>View User</h6>"><i class="fa fa-eye"></i> User</a></td>
                                 <td><a href="<%=application.getContextPath()%>/Admin?command=authorblog&bid=<%=tempReport.getBlogId()%>" class="btn btn-outline-success btn-sm" data-toggle="tooltip" data-placement="top" data-html="true" title="<h6>View Blog</h6>"><i class="fa fa-eye"></i> Blog</a></td>
                                 <td><%=  tempReport.getReportDate()%></td>
                                 <td><%=  tempReport.getReportStatus()%></td>
                                  <% if(tempReport.getReportStatus().equals("undone")){
                                      %>
                                      <td><form action="<%=application.getContextPath()%>/Admin" method="post" class="d-inline-block mr-1">
                                              <input type="hidden" name="command" value="reportdone">
                                              <input type="hidden" name="rid" value="<%= tempReport.getId()%>">
                                              <button type="submit" class="btn btn-outline-success btn-sm"><i class="fa fa-check"></i> Done</button>
                                          </form>
                                          <form action="<%=application.getContextPath()%>/Admin" method="post" class="d-inline-block mr-1">
                                              <input type="hidden" name="command" value="reportdelete">
                                              <input type="hidden" name="rid" value="<%=tempReport.getId()%>">
                                              <button type="submit" class="btn btn-outline-danger btn-sm"><i class="fa fa-trash-o"></i></button>
                                          </form>
                                      </td>
                                  <%}else{%>
                                  <td><form action="<%=application.getContextPath()%>/Admin" method="post" class="d-inline-block mr-1">
                                              <input type="hidden" name="command" value="reportundone">
                                              <input type="hidden" name="rid" value="<%=tempReport.getId()%>">
                                              <button type="submit" class="btn btn-outline-danger btn-sm"><i class="fa fa-close"></i> UnDone</button>
                                          </form>
                                          <form action="<%=application.getContextPath()%>/Admin" method="post" class="d-inline-block mr-1">
                                              <input type="hidden" name="command" value="reportdelete">
                                              <input type="hidden" name="rid" value="<%=tempReport.getId()%>">
                                              <button type="submit" class="btn btn-outline-danger btn-sm"><i class="fa fa-trash-o"></i></button>
                                          </form>
                                  </td><%}%>
                                  </tr>
                                  <%  }%>
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