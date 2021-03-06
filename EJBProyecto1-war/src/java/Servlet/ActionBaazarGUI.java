/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebEndpoint;
import logica.Bid;
import logica.PlaceBidBeanLocal;
import logica.PremiumBid;
import logica.SimpleBid;

/**
 *
 * @author Salvador
 */

public class ActionBaazarGUI extends HttpServlet {
    
    @EJB
    PlaceBidBeanLocal placeBidLocal;

    @Override
    public void init() throws ServletException {
        super.init(); 
        //Bid b = new Bid();
        //b.setName("Statless bean");
       // placeBidLocal.placeBid(b);
        
    }
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Llego");
        Bid bid = null;
        System.out.println(request.getParameter("select"));
        if (request.getParameter("select").equals("simple")){
            bid = new SimpleBid();
            ((SimpleBid)bid).setSimpleDescr("simple " + request.getParameter("desc"));
        } else
            if (request.getParameter("select").equals("premium")){
                bid = new PremiumBid();
            ((PremiumBid)bid).setPremiumDescrip("premium " + request.getParameter("desc"));                
            }
        placeBidLocal.guardar(bid);
        response.sendRedirect("ActianBaazar.jsp");
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
