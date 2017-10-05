<%-- 
    Document   : default
    Created on : Oct 5, 2017, 10:49:45 AM
    Author     : Salvador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3><%= request.getAttribute("mensaje") %></h3>
        <A href="index.jsp"> Volver a pantalla principal</A>
    </body>
</html>
