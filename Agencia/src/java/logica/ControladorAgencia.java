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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author e945952
 */
public class ControladorAgencia implements IAdminAgencia{

    private static ControladorAgencia instance = null;
    private static String nombre;
	
    private ControladorAgencia(){
        nombre = "agencia1";
    }
    
    public static ControladorAgencia getInstancia(){
	if(instance == null){
        	instance = new ControladorAgencia();
	}
            return instance;
    }
    
    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        ControladorAgencia.nombre = nombre;
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
    @SuppressWarnings("empty-statement")
    public ArrayList<Ticket> ventaTotalDiaria() {
        
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from ticketst");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Date fechaTicket = (Date) rs.getDate("fechaVenta");
                String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                if(fechaActual.equals(fechaTicket.toString())){
                    System.out.println("llego 9");
                    String numero = rs.getString("numero");
                    String terminal = rs.getString("terminal");
                    String matricula = rs.getString("matricula");
                    String importe = rs.getString("importe");
                    Ticket t = new Ticket();
                    t.setNumero(numero);
                    t.setMatricula(matricula);
                    t.setImporteTotal(importe);
                    t.setTerminal(terminal);
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaDate = null;
                    try {
                        fechaDate = formato.parse(fechaTicket.toString());
                    } 
                    catch (ParseException ex) 
                    {
                        System.out.println(ex);
                    }
                    //t.setFechaVenta(fechaTicket);
                    //Ticket t = new Ticket(numero, terminal, matricula, fechaTicket, new Date(), "0", importe, "agencia1");
                    System.out.println(fechaDate);
                    tickets.add(t);
                    System.out.println("llego 11");
                }
            } 
            ps.close();
            conn.close();
        }catch (SQLException ex) {
            System.out.println("Error al conectar SQL en venta total diaria");
        } catch (NamingException ex) {      
            Logger.getLogger(ControladorAgencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0; i<tickets.size(); i++){
            System.out.println(tickets.get(i).getNumero());
        };
        return tickets;
    }

    @Override
    public ArrayList<Ticket> ventaTotalPorHora(Date horaiIni, Date horaFin) {
        return null;
    }
    
}
