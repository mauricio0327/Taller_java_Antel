/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.ControladorIMM;
import logica.Fabrica;
import logica.ITerminales;
import logica.Ticket;
/**
 *
 * @author e945952
 */
public class ServidorSocket {
    
    public static void main(String[] args){       
        
        try {          
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("servidor corriendo");
            Socket clienteAgencia = serverSocket.accept();
            ObjectInputStream entrada1 = new ObjectInputStream(clienteAgencia.getInputStream());           
            String accion = (String) entrada1.readObject();
            while (!accion.equals("salir")){
            if (accion.equals("venta")){
                ObjectInputStream entrada2 = new ObjectInputStream(clienteAgencia.getInputStream());           
                String[] datosTk = (String[]) entrada2.readObject();
                Date inicioEstacionamiento = ParseFecha(datosTk[2]);
                Ticket ticket = new Ticket("", datosTk[0], datosTk[1], new Date(), inicioEstacionamiento, datosTk[3], datosTk[4], "agencia1");
                ControladorIMM controlIMM = ControladorIMM.getInstancia();
                Ticket ticket2 = controlIMM.ventaTicketIMM(ticket);
                if (!ticket2.getNumero().equals("")){
                    Fabrica fabrica = Fabrica.getInstance();
                    ITerminales ITerm = fabrica.getiControladorTerminales();
                    ITerm.ventaTicketTerminal(ticket2);
                    ObjectOutputStream respuesta = new ObjectOutputStream(clienteAgencia.getOutputStream());
                    respuesta.writeObject("Ticket "+ticket2.getNumero() +" vendido satisfactoriamente");
                }else{
                    ObjectOutputStream respuesta = new ObjectOutputStream(clienteAgencia.getOutputStream());
                    respuesta.writeObject("Ticket rechazado");
                }
            }
            else{    
                
                ObjectOutputStream respuesta = new ObjectOutputStream(clienteAgencia.getOutputStream());
                respuesta.writeObject("Esa opcion no esta desarrollada, dame tiempo amigo");
                
            }
            entrada1 = new ObjectInputStream(clienteAgencia.getInputStream());           
            accion = (String) entrada1.readObject();
            }
            clienteAgencia.close();
            serverSocket.close();
            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(ServidorSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public static Date ParseFecha(String fecha)
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
