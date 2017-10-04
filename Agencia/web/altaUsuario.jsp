<%-- 
    Document   : altaUsuario
    Created on : 28-sep-2017, 16:31:18
    Author     : e945952
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta Usuario</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="ServletUsuario" method="POST">
        <table>
            <tr>
		<td><label for="user">Nickname</label> </td>
		<td><input type="text" name="user"></input></td>
            </tr>
            <tr>
		<td><label for="pass">Contraseña:</label> </td>
		<td><input type="password" name="pass" ></input></td>
            </tr>
            <tr>
		<td><label for="nombre">Nombre</label> </td>
		<td><input type="text" name="nombre"></input></td>
            </tr>
            <tr>
		<td><label for="apellido">Apellido</label> </td>
		<td><input type="text" name="apellido"></input></td>
            </tr>
            <tr>
        </table>
            <input type="submit" value="Ingresar"></input> <br>
        </form>
        <A href="index.jsp"> Volver </A>
    </body>
</html>
