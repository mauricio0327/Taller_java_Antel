<%-- 
    Document   : login
    Created on : Oct 20, 2017, 8:30:12 AM
    Author     : Salvador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Bienvenido</h1>
        <form action="ServletLogin" method="POST">
        <table>
            <tr>
		<td><label for="user">Usuario</label> </td>
		<td><input type="text" name="user"></input></td>
            </tr>
            <tr>
		<td><label for="pass">Contraseña</label> </td>
		<td><input type="password" name="pass" ></input></td>
            </tr>            
            <tr>
        </table>
            <input type="submit" value="Ingresar"></input> <br>
        </form>
        
    </body>
</html>

