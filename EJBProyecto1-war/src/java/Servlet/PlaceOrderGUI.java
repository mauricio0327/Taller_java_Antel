/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.PlaceOrderBeanIn;

/**
 *
 * @author Salvador
 */
@WebServlet(name = "PlaceOrderGUI", urlPatterns = {"/PlaceOrderGUI"})
public class PlaceOrderGUI extends HttpServlet {
    
    //@EJB
    PlaceOrderBeanIn placeOrderBeanIn;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void init() throws ServletException {
        super.init(); 
        }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String temp = " ";
        
        if("shippingAddress".equals(operation)){
            System.out.println("Setea shipping address");
            placeOrderBeanIn.setShippingInfo(request.getParameter("shippingAddress"));
        }
        if("billingAddress".equals(operation)){
            System.out.println("Setea billing address");
            placeOrderBeanIn.setBillingInfo(request.getParameter("billingInfo"));
        }
        if("bidId".equals(operation)){
            System.out.println("Setea bid ID");
            placeOrderBeanIn.setBidId(request.getParameter("bidId"));
        }
        if("confirm".equals(operation)){
            placeOrderBeanIn.confirmOrder();
            System.out.println(placeOrderBeanIn.datos());
            placeOrderBeanIn.sendMessage();
            temp = "Estado de la orden "+placeOrderBeanIn.getEstado();
        }
        if("init".equals(operation)){
            try {
                System.out.println("inicio");
                temp = "Estado de la orden "+placeOrderBeanIn.getEstado();
                placeOrderBeanIn = InitialContext.doLookup("java:global/EJBProyecto1/EJBProyecto1-ejb/PlaceOrderBean!logica.PlaceOrderBeanIn");
            } catch (NamingException ex) {
                Logger.getLogger(PlaceOrderGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        }
        request.setAttribute("mensaje", temp);
        response.sendRedirect("PlaceOrder.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
