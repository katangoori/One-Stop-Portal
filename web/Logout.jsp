<%-- 
    Document   : Logout
    Created on : Oct 7, 2015, 3:59:16 PM
    Author     : Katangoori
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SENG_Project</title>
        <link rel="stylesheet" href="common.css">
    </head>
    <body>
        <% session.invalidate();
        response.sendRedirect("login.jsp");
        %>
    </body>
</html>
