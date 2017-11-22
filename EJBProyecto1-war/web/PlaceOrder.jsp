<%-- 
    Document   : PlaceOrder
    Created on : Oct 30, 2017, 8:31:34 AM
    Author     : Salvador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
            <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Place order</title>
    </head>
    <body>
        <form action="PlaceOrderGUI" method="GET">                
                <table>
                        <tr>
                                <td><label for="shippingAddress">Shipping address:</label> </td>
                                <td><input type="text" name="shippingAddress"></input></td>
                                <td><input type="submit" value="Shipping address"></input></td>
                        <input type hidden name="operation" value="shippingAddress" >
                        </tr>
                </table>
        </form>
        <form action="PlaceOrderGUI" method="GET">
            <table>
                <tr>
                        <td><label for="billingInfo">Billing info:</label> </td>
                        <td><input type="text" name="billingInfo"></input></td>
                        <td><input type="submit" value="Billing info"></input></td>
                        <input type hidden name="operation" value="billingAddress" >
                </tr>
            </table>
        </form>
        <form action="PlaceOrderGUI" method="GET">
            <table>
                <tr>
                        <td><label for="bidId">Bid ID:</label> </td>
                        <td><input type="text" name="bidId"></input></td>
                        <td><input type="submit" value="bidId"></input></td>
                        <input type hidden name="operation" value="bidId" >
                </tr>
            </table>
        </form>
        <form action="PlaceOrderGUI" method="GET">
            <input type="submit" value="Confirmar"></input>
            <input type hidden name="operation" value="confirm" >

        </form>
        <form action="PlaceOrderGUI" method="GET">
            <input type="submit" value="Iniciar"></input>
            <input type hidden name="operation" value="init" >

        </form>
        <%= request.getAttribute("mensaje") %> 
        <%= response.getHeader("mensaje") %>
        <A href="index.jsp"> Volver </A>
    </body>
</html>
