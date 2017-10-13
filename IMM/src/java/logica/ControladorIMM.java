/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Salvador
 */
public class ControladorIMM implements IAdminIMM{
    
    private ArrayList<String> codigos;
    private ArrayList<String> numeros;
    private ArrayList<String> agencias;
    private int cont;
    
    
    private static ControladorIMM instance = null;
	
    
    public static ControladorIMM getInstancia(){
	if(instance == null){
        	instance = new ControladorIMM();
	}
            return instance;
    }

    
    
    public ControladorIMM(){
        this.codigos= new ArrayList<String>();
        this.numeros= new ArrayList<String>();
        this.agencias= new ArrayList<String>();
        this.agencias.add("agencia1");
        this.agencias.add("agencia2");
        this.agencias.add("agencia3");
        this.cont = 0;
        
        
    }

    public ArrayList<String> getCodigos() {
        return codigos;
    }

    public void setCodigos(ArrayList<String> codigos) {
        this.codigos = codigos;
    }

 
    public boolean loginAdmin(DtUsuario dtUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public String ventaTicket(Ticket ticket) {
        String num = "";
        boolean valida = false;
        Iterator<String> it = agencias.iterator();
        while ((it.hasNext())&&(!valida)){
           if (it.next().equals(ticket.getAgencia())){
               valida = true;
           }
        }
        if (valida){
            num = codGen();
            this.codigos.add(num);
            
        }
        
        return num;
    }

    @Override
    public String codGen() {
        this.cont=(this.cont+1);
        int c = this.cont;
        return String.valueOf(c);
        
    }
    
   
    @Override
    public String anulacionTk(String numero, String agencia) {
        String c = "";
        boolean agenciabool = false;
        boolean numerobool = false;
        InitialContext initContext;
        try {
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");
            
            Connection conn = ds.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("select * from tickets"); 
            ResultSet rs = ps.executeQuery();
            String a2 = "";
            while ((rs.next())&&(!numerobool)) {
                if (rs.getString("numero").equals(numero)){
                    numerobool=true;
                    a2=rs.getString("agencia");
                }
                        
            }
            if ((numerobool)&&(a2.equals(agencia))){
                c="A"+numero;
            } else {
                c="NA";
            }
        } catch (NamingException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return c;
    }
    
       public ArrayList<String> getNumeros() {
        return numeros;
    }

    public void setNumeros(ArrayList<String> numeros) {
        this.numeros = numeros;
    }

    public ArrayList<String> getAgencias() {
        return agencias;
    }

    public void setAgencias(ArrayList<String> agencias) {
        this.agencias = agencias;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }
    
}
