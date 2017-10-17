/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminales;

/**
 *
 * @author e945952
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Terminales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        try {
            // TODO code application logic here
            String nombre = "terminal1";
            boolean existeu = false;
            boolean logeo = false;
            String passbd = "";
            String url = "jdbc:mysql://localhost:3306/mysqldb";
            String user = "root";
            String pass = "roxana69";
            System.out.println("Conectando...");
            Connection conn = DriverManager.getConnection(url, user,pass);
            System.out.println("Conectando...");
            PreparedStatement ps2 = conn.prepareStatement("select * from userterm"); 
            ResultSet rs2 = ps2.executeQuery();
            while (!logeo) {   
                System.out.println("Ingrese usuario");
                BufferedReader bufUsuario = new BufferedReader(new InputStreamReader(System.in));
                String usuario = bufUsuario.readLine();
                System.out.println("Ingrese pass");
                BufferedReader bufcontra = new BufferedReader(new InputStreamReader(System.in));
                String contra = bufcontra.readLine();
                while ((rs2.next())&&(!existeu)) {
                    if (rs2.getString("user").equals(usuario)){
                        existeu=true;
                        passbd=rs2.getString("pass");
                    }

                }
                logeo = passbd.equals(contra);
                if (logeo){
                    System.out.println("Logeo existoso");
                }
            }
            Socket clienteTerminal = new Socket("localhost", 5000);
            System.out.println("Bienvenido a su terminal");
            System.out.println("Que desea hacer:");
            System.out.println("1 - Venta de ticket");
            System.out.println("2 - Anulacion de ticket");
            BufferedReader objEnt = new BufferedReader(new InputStreamReader(System.in));
            String entrada = objEnt.readLine();
            while (!entrada.equals("exit")){
                if (entrada.equals("1")){
                    ObjectOutputStream mensaje1 = new ObjectOutputStream(clienteTerminal.getOutputStream());      
                    mensaje1.writeObject("venta");
                    System.out.println("Datos del ticket");
                    System.out.print("Ingrese la matricula del vehiculo: ");
                    BufferedReader bufferMat = new BufferedReader(new InputStreamReader(System.in));
                    String matricula = bufferMat.readLine();             
                    System.out.print("Ingrese la fecha de inicio (dd/mm/aaaa hh:mm): ");
                    BufferedReader bufferInicio = new BufferedReader(new InputStreamReader(System.in));
                    String inicio = bufferInicio.readLine();
                    System.out.print("Ingrese la cantidad de minutos: ");
                    BufferedReader bufferMin = new BufferedReader(new InputStreamReader(System.in));
                    String minutos = bufferMin.readLine();
                    String importe = "";
                    String[] ticket = new String[5];
                    ticket[0] = nombre;
                    ticket[1] = matricula;
                    ticket[2] = inicio;
                    ticket[3] = minutos;
                    ticket[4] = importe;
                    ObjectOutputStream mensaje = new ObjectOutputStream(clienteTerminal.getOutputStream());      
                    mensaje.writeObject(ticket);

                    ObjectInputStream respuesta = new ObjectInputStream(clienteTerminal.getInputStream());
                    String resultado = (String) respuesta.readObject();
                    System.out.println(resultado);
                }else{
                    System.out.println("Esa opcion no esta desarrollada, dame tiempo amigo");
                }
                
            System.out.println("Que desea hacer:");
            System.out.println("1 - Venta de ticket");
            System.out.println("2 - Anulacion de ticket");
            objEnt = new BufferedReader(new InputStreamReader(System.in));
            entrada = objEnt.readLine();
            }
            
            clienteTerminal.close();
            System.out.println("Conexion cerrada");
            
        } catch (IOException ex) {
            Logger.getLogger(Terminales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Terminales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
  
    
}
