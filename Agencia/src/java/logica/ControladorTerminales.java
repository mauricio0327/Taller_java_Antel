/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author e945952
 */
public class ControladorTerminales implements ITerminales{

    private static ControladorTerminales instance = null;
	
    private ControladorTerminales(){}
    
    public static ControladorTerminales getInstancia(){
	if(instance == null){
        	instance = new ControladorTerminales();
	}
            return instance;
    }
    
    @Override
    public boolean loginUser(DtUsuario dtUser) {
        return true;
    }

    @Override
    public void ventaTicketTerminal(Ticket tk) {
                
        try {

            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");
            Connection conn = ds.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ticketst (numero, codigo, terminal, "
                    + "matricula, fechaVenta, importe) VALUES (?,?,?,?,?,?)");
            ps.setString(1, tk.getNumero());
            ps.setString(2, "");
            ps.setString(3, tk.getTerminal());
            ps.setString(4, tk.getMatricula());
            ps.setTimestamp(5, new java.sql.Timestamp(tk.getFechaVenta().getTime()));
            ps.setString(6, tk.getImporteTotal());
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (SQLException ex) {
            System.out.println("Error al conectar SQL");
        } catch (NamingException ex) {
            Logger.getLogger(ControladorTerminales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public boolean controlAnulacion(String numero){
        
        boolean b = false;
        try {
            System.out.println("Antes de la base");
            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");
            Connection conn = ds.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("select * from ticketst where numero = ?"); 
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            System.out.println("A ver aca");
            while (rs.next()){
                if(rs.getString("codigo").equals("")){
                    System.out.println("Entro");
                    b = true;
                }
            }
            System.out.println(b);
            ps.close();
            conn.close();
            
        }catch (SQLException ex) {
            System.out.println("Error al conectar SQL");
        } catch (NamingException ex) {
            Logger.getLogger(ControladorTerminales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    };
    
    @Override
    public void anulacionTicketTerminal(String numero, String codigo) {
        
         try {
            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");
            Connection conn = ds.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE ticketst SET codigo = ? WHERE numero = ?");           
            ps.setString(1,codigo);
            ps.setString(2,numero);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (SQLException ex) {
            System.out.println("Error al conectar SQL");
        } catch (NamingException ex) {
            Logger.getLogger(ControladorTerminales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
