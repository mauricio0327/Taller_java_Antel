<%-- 
    Document   : ActianBaazar
    Created on : Nov 3, 2017, 8:44:43 AM
    Author     : Salvador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Action Baazar</title>
    </head>
    <body>
        <form action="ActionBaazarGUI" method="GET">                
                <table>
                <tr>
                        <td><label for="desc">Description:</label> </td>
                        <td><input type="text" name="desc"></input></td>
                        Tipo <select name="select">
                            <option value="simple">SimpleBid</option>
                            <option value="premium">PremiumBid</option>
                        </select>
                        <td><input type="submit" value="Guardar"></input></td>
                </tr>
            </table>
        </form>
        <A href="index.jsp"> Volver </A>
    </body>
</html>
