<%-- 
    Document   : index
    Created on : Mar 17, 2020, 4:28:09 PM
    Author     : DELL PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
          request.getRequestDispatcher("FrontController").forward(request, response);
        %>
    </body>
</html>
