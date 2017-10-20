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
    private boolean logeo;
    private DtUsuario usuario;
    
    
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
        this.logeo = false;
        
        
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

    public boolean isLogeo() {
        return logeo;
    }

    public void setLogeo(boolean logeo) {
        this.logeo = logeo;
    }

    public DtUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(DtUsuario usuario) {
        this.usuario = usuario;
    }
    
    

    
    @Override
    public String ventaTicket(Ticket ticket) {
        String num = "0";
        String num2 = "0";
        boolean valida = false;
        InitialContext initContext;
        try {
            initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");
            System.out.println("DS");
            Connection conn = ds.getConnection(); 
            System.out.println("Connection");
            PreparedStatement ps = conn.prepareStatement("select * from agencias"); 
            ResultSet rs = ps.executeQuery();
            System.out.println("Query");
            String a2 = "";
            while ((rs.next())&&(!valida)) {
                if (rs.getString("nombre").equals(ticket.getAgencia())){
                    valida=true;                   
                }
                        
            }
            if (valida){
                PreparedStatement ps2 = conn.prepareStatement("select * from tickets"); 
                ResultSet rs2 = ps2.executeQuery();
                System.out.println("PS2");
                //num=rs2.getString("numero");
                while (rs2.next()){
                        num2=rs2.getString("numero");
                        if ((Integer.parseInt(num2))>(Integer.parseInt(num))){
                            num=num2;
                        }                     
                }
                int n2 = Integer.parseInt(num);
                n2=n2+1;
                num = String.valueOf(n2);                
                System.out.println(num);
                PreparedStatement ps3 = conn.prepareStatement("INSERT INTO tickets (numero, codigo, agencia, matricula, fecha_venta, fecha_inicio, cantMin, importe) VALUES (?,?,?,?,?,?,?,?)");
                ps3.setString(1, num);
                ps3.setString(2, "");
                ps3.setString(3, ticket.getAgencia());
                ps3.setString(4, ticket.getMatricula());
                ps3.setTimestamp(5, new java.sql.Timestamp(ticket.getFechaVenta().getTime()));
                ps3.setTimestamp(6, new java.sql.Timestamp(ticket.getInicioEstacionamiento().getTime()));
                ps3.setString(7, ticket.getCantMin());
                ps3.setString(8, importe(ticket.getCantMin()));
                ps3.executeUpdate();
                ps3.close();
                ps2.close();
                ps.close();
                conn.close();
            
        }
            
        } catch (NamingException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
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
    public String importe(String minutos) {
        int base = 50;
        int min = Integer.parseInt(minutos);
        if (min<30){
            min=min+30;
        }
        int valor = base*(min/30);
        return String.valueOf(valor);
        
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
            String c1 = "";
            while ((rs.next())&&(!numerobool)) {
                if (rs.getString("numero").equals(numero)){
                    
                    numerobool=true;
                    a2=rs.getString("agencia");
                    c1=rs.getString("codigo");
                }
                        
            }
            System.out.println((numerobool)&&(a2.equals(agencia)));
            if (!c1.equals("")){
                numerobool=false;
            }
            if ((numerobool)&&(a2.equals(agencia))){
                System.out.println("Llego antes de BD anulacion");
                c="A"+numero;
                PreparedStatement ps3 = conn.prepareStatement("UPDATE tickets SET codigo = ? WHERE numero = ?");           
                ps3.setString(1,c);
                ps3.setString(2,numero);
                ps3.executeUpdate();
                ps3.close();
                ps.close();
                conn.close();
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
