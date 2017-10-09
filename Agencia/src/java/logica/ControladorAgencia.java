/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author e945952
 */
public class ControladorAgencia implements IAdminAgencia{

    private static ControladorAgencia instance = null;
	
    private ControladorAgencia(){}
    
    public static ControladorAgencia getInstancia(){
	if(instance == null){
        	instance = new ControladorAgencia();
	}
            return instance;
    }
    
    @Override
    public void agregarUsuario(String user, String passwd, String nombre, String apellido, String admin) throws NamingException{
        
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");
            Connection conn = ds.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (user_name, pass, nombre, apellido, admin) VALUES (?,?,?,?,?)");
            ps.setString(1, user);
            ps.setString(2, passwd);
            ps.setString(2, nombre);
            ps.setString(2, apellido);
            ps.setString(2, admin);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (SQLException ex) {
            System.out.println("Error al conectar SQL");
        }
    }
    
    @Override
    public boolean loginAdmin(DtUsuario dtUser) {
        return true;
    }

    @Override
    public ArrayList<Ticket> ventaTotalDiaria() {
        return null;
    }

    @Override
    public ArrayList<Ticket> ventaTotalPorHora(Date horaiIni, Date horaFin) {
        return null;
    }
    
}
