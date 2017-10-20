<%-- 
    Document   : reporteDiario
    Created on : 19-oct-2017, 15:27:26
    Author     : e945952
--%>

<%@page import="logica.Ticket"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte Diario</title>
    </head>
    <body>
        <h1>Reporte diario</h1>
        <% ArrayList<Ticket> tickets = (ArrayList<Ticket>) request.getAttribute("tickets"); %>
        <table border="2" width="50%" align="center">	
            <tr>
                <td>Numero de ticket</td>
                <td>Matricula</td>
                <td>Fecha de Venta</td>
                <td>Importe Total</td>
                <td>Terminal de venta</td>
            </tr>
            
            <% for(int i=0; i<tickets.size(); i++){ %>
            <tr>
                <td><%= tickets.get(i).getNumero() %></td>
                <td><%= tickets.get(i).getMatricula() %></td>
                <td></td>
                <td><%= tickets.get(i).getImporteTotal() %></td>
                <td><%= tickets.get(i).getTerminal() %></td>
            </tr>
            <% } %>
        </table>
    </body>
</html>
