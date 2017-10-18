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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author Salvador
 */
@ManagedBean
@RequestScoped
public class ReportesBeans {

    /**
     * Creates a new instance of ReportesBeans
     */
    private ArrayList<Ticket> tickets;
    private String fechaInicio;
    private String fechaFin;
    public ReportesBeans() {
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    public String reportesMensuales() throws SQLException, NamingException{
        Calendar now = Calendar.getInstance();
        String pagina = "listadomensual";
        int mes = now.get(Calendar.MONTH);
        tickets = new ArrayList<Ticket>();
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");            
            Connection conn = ds.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("select * from tickets"); 
            ResultSet rs = ps.executeQuery();
            System.out.println("llego");
            
            while (rs.next()) {
                Date dat = rs.getTimestamp("fecha_venta");
                Calendar cr = Calendar.getInstance();
                cr.setTime(dat);
                if (cr.get(Calendar.MONTH)==mes) {
                    Ticket t = new Ticket();
                    t.setAgencia(rs.getString("agencia"));
                    t.setCantMin(rs.getString("cantMin"));
                    t.setCodigo(rs.getString("codigo"));
                    t.setNumero(rs.getString("numero"));
                    t.setFechaVenta(cr.getTime());
                    t.setImporteTotal(rs.getString("importe"));
                    Date dat2 = rs.getTimestamp("fecha_inicio");
                    Calendar cr2 = Calendar.getInstance();
                    cr2.setTime(dat2);
                    t.setInicioEstacionamiento(cr2.getTime());
                    t.setMatricula(rs.getString("matricula"));
                    tickets.add(t);
                }
                    
                    
                   
            }
            
                
        ps.close();
        conn.close();
        
       
        } catch (NamingException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pagina;
        
    }
    
    public String reportesPorFechas() throws SQLException, NamingException{
        Calendar now = Calendar.getInstance();
        String pagina = "listadoporfecha";
        String[] fi = this.fechaInicio.split("/");
        String d1 = fi[0];
        String m1 = fi[1];
        String a1 = fi[2];
        String[] ff = this.fechaFin.split("/");
        String d2 = ff[0];
        String m2 = ff[1];
        String a2 = ff[2];
        int mes = now.get(Calendar.MONTH);
        tickets = new ArrayList<Ticket>();
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");            
            Connection conn = ds.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("select * from tickets"); 
            ResultSet rs = ps.executeQuery();
            System.out.println("llego");
            
            while (rs.next()) {
                Date dat = rs.getTimestamp("fecha_venta");
                Calendar cr = Calendar.getInstance();
                cr.setTime(dat);
                if ((cr.get(Calendar.DAY_OF_MONTH)>=Integer.parseInt(d1))&&(cr.get(Calendar.DAY_OF_MONTH)<=Integer.parseInt(d2))
                        &&(cr.get(Calendar.MONTH)+1>=Integer.parseInt(m1))&&(cr.get(Calendar.MONTH)+1<=Integer.parseInt(m2))
                        &&(cr.get(Calendar.YEAR)>=Integer.parseInt(a1))&&(cr.get(Calendar.YEAR)<=Integer.parseInt(a2))) {
                    Ticket t = new Ticket();
                    t.setAgencia(rs.getString("agencia"));
                    t.setCantMin(rs.getString("cantMin"));
                    t.setCodigo(rs.getString("codigo"));
                    t.setNumero(rs.getString("numero"));
                    t.setFechaVenta(cr.getTime());
                    t.setImporteTotal(rs.getString("importe"));
                    Date dat2 = rs.getTimestamp("fecha_inicio");
                    Calendar cr2 = Calendar.getInstance();
                    cr2.setTime(dat2);
                    t.setInicioEstacionamiento(cr2.getTime());
                    t.setMatricula(rs.getString("matricula"));
                    tickets.add(t);
                }
                    
                    
                   
            }
            
                
        ps.close();
        conn.close();
        
       
        } catch (NamingException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pagina;
        
    }
    
    public String bienvenida(){
        DtUsuario usuario = ControladorIMM.getInstancia().getUsuario();
        return ("Bienvenido "+usuario.getNombre()+" "+usuario.getApellido());
    }
    
}
