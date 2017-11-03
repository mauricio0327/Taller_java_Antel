/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class hiloTerminal extends Thread{
    
    Socket clienteAgencia;
    
    public hiloTerminal(Socket s){
        clienteAgencia = s;
    }
    
    @Override
    public void run() {
        
        Fabrica fabrica;
        ControladorIMM controlIMM;
        
        try {
            ObjectInputStream entrada1;                  
            entrada1 = new ObjectInputStream(clienteAgencia.getInputStream());
            System.out.println("hola");
            String accion = (String) entrada1.readObject();
            System.out.println("chau");
            //System.out.println(accion);
            while (!accion.equals("salir")){
                if (accion.equals("venta")){
                    ObjectInputStream entrada2 = new ObjectInputStream(clienteAgencia.getInputStream());           
                    String[] datosTk = (String[]) entrada2.readObject();
                    Date inicioEstacionamiento = ParseFecha(datosTk[2]);
                    Ticket ticket = new Ticket("", datosTk[0], datosTk[1], new Date(), inicioEstacionamiento, datosTk[3], datosTk[4], "agencia1");
                    controlIMM = ControladorIMM.getInstancia();
                    Ticket ticket2 = controlIMM.ventaTicketIMM(ticket);
                    if (!ticket2.getNumero().equals("")){
                        fabrica = Fabrica.getInstance();
                        ITerminales ITerm = fabrica.getiControladorTerminales();
                        ITerm.ventaTicketTerminal(ticket2);
                        ObjectOutputStream respuesta = new ObjectOutputStream(clienteAgencia.getOutputStream());
                        respuesta.writeObject("Ticket "+ticket2.getNumero() +" vendido satisfactoriamente");
                    }else{
                        ObjectOutputStream respuesta = new ObjectOutputStream(clienteAgencia.getOutputStream());
                        respuesta.writeObject("Ticket rechazado");
                    }
                }
                else if (accion.equals("anulacion")){    

                    ObjectInputStream entrada2 = new ObjectInputStream(clienteAgencia.getInputStream());           
                    String[] datosTk = (String[]) entrada2.readObject();
                    fabrica = Fabrica.getInstance();
                    ITerminales ITerm = fabrica.getiControladorTerminales();

                    if (ITerm.controlAnulacion(datosTk[1])){

                        controlIMM = ControladorIMM.getInstancia();
                        String codigo = controlIMM.anulacionTicketIMM(datosTk[1], "agencia1");
                        System.out.println(codigo);
                        if (codigo.equals("NA")){
                            ObjectOutputStream respuesta = new ObjectOutputStream(clienteAgencia.getOutputStream());
                            respuesta.writeObject("No es posible anular el ticket "+datosTk[1]);
                        }else{
                            fabrica = Fabrica.getInstance();
                            ITerm = fabrica.getiControladorTerminales();
                            ITerm.anulacionTicketTerminal(datosTk[1], codigo);
                            ObjectOutputStream respuesta = new ObjectOutputStream(clienteAgencia.getOutputStream());
                            respuesta.writeObject("Ticket "+datosTk[1] +" anulado");
                        }

                    }else{
                        ObjectOutputStream respuesta = new ObjectOutputStream(clienteAgencia.getOutputStream());
                        respuesta.writeObject("No es posible anular el ticket "+datosTk[1]);
                    }               
                } else if (accion.equals("logeo")) {
                    System.out.println("Llego l1");
                    ObjectInputStream entrada3 = new ObjectInputStream(clienteAgencia.getInputStream());           
                    String[] datosU = (String[]) entrada3.readObject();
                    InitialContext initContext;
                    System.out.println("Llego l2");
                    boolean existeu=false;
                    boolean logeo=false;
                    String passbd = "";
                    String mensaje = "Usuario o password incorrectos";
                    try {
                        initContext = new InitialContext();
                        DataSource ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");
                        Connection conn = ds.getConnection(); 
                        PreparedStatement ps2 = conn.prepareStatement("select * from userterm"); 
                        ResultSet rs2 = ps2.executeQuery();
                        System.out.println("Llego l3");
                        while ((rs2.next())&&(!existeu)) {
                            if (rs2.getString("user").equals(datosU[0])){
                                existeu=true;
                                passbd=rs2.getString("pass");
                        }

                       }
                       logeo = passbd.equals(datosU[1]);
                       if (logeo){
                           mensaje="Ok";
                       }
                       ObjectOutputStream respuestal = new ObjectOutputStream(clienteAgencia.getOutputStream());
                        respuestal.writeObject(mensaje); 
                        
                    } catch (NamingException ex) {
                        Logger.getLogger(hiloTerminal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(hiloTerminal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
                ObjectInputStream entrada2 = new ObjectInputStream(clienteAgencia.getInputStream());           
                accion = (String) entrada2.readObject();
            }
            clienteAgencia.close();
    }catch (IOException ex) {
           Logger.getLogger(hiloTerminal.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(hiloTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public static Date ParseFecha(String fecha){
        {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
        }
    }
}
