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
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        try {
            // TODO code application logic here
            String nombre;            
            boolean logeo = false;
            /*boolean existeu = false;
            String passbd = "";
            String url = "jdbc:mysql://localhost:3306/mysqldb";
            String user = "root";
            String pass = "roxana69";
            System.out.println("Conectando...");
            Connection conn = DriverManager.getConnection(url, user,pass);
            System.out.println("Conectando...");
            PreparedStatement ps2 = conn.prepareStatement("select * from userterm"); 
            ResultSet rs2 = ps2.executeQuery();
            */

            try (Socket clienteTerminal = new Socket("localhost", 5000)) {
               while (!logeo) {
                ObjectOutputStream mensajel = new ObjectOutputStream(clienteTerminal.getOutputStream());
                mensajel.writeObject("logeo");    
                System.out.println("Ingrese usuario");
                BufferedReader bufUsuario = new BufferedReader(new InputStreamReader(System.in));
                String usuario = bufUsuario.readLine();
                System.out.println("Ingrese pass");
                BufferedReader bufcontra = new BufferedReader(new InputStreamReader(System.in));
                String contra = bufcontra.readLine();
                String[] du = new String[2];
                du[0] = usuario;
                du[1] = contra;
                System.out.println("A ver "+du[0]+" "+du[1]);
                
                ObjectOutputStream mensajel2 = new ObjectOutputStream(clienteTerminal.getOutputStream());
                mensajel2.writeObject(du);
                   
                ObjectInputStream respuestal = new ObjectInputStream(clienteTerminal.getInputStream());
                String resultadol = (String) respuestal.readObject();
                System.out.println("Respuesta");
                System.out.println(resultadol);
                
                /*while ((rs2.next())&&(!existeu)) {
                    if (rs2.getString("user").equals(usuario)){
                        existeu=true;
                        passbd=rs2.getString("pass");
                    }

                }*/
                logeo = ((String) resultadol).equals("Ok");
                if (logeo){
                    System.out.println("Logeo existoso");
                }
            }
                System.out.println("Ingrese el nombre de su terminal");
                BufferedReader nombreTerm = new BufferedReader(new InputStreamReader(System.in));
                nombre = nombreTerm.readLine();
                
                System.out.println();
                System.out.println("Que desea hacer:");
                System.out.println("1 - Venta de ticket");
                System.out.println("2 - Anulacion de ticket");
                System.out.println("3 - salir");
                BufferedReader objEnt = new BufferedReader(new InputStreamReader(System.in));
                String entrada = objEnt.readLine();
                while (!entrada.equals("3")){
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
                        
                    }else if (entrada.equals("2")){
                        
                        ObjectOutputStream mensaje1 = new ObjectOutputStream(clienteTerminal.getOutputStream());
                        mensaje1.writeObject("anulacion");
                        System.out.println("Ingrese el numero del ticket a anular");
                        BufferedReader bufferMat = new BufferedReader(new InputStreamReader(System.in));
                        String numero = bufferMat.readLine();
                        String[] ticket2 = new String[2];
                        ticket2[0] = nombre;
                        ticket2[1] = numero;
                        ObjectOutputStream mensaje = new ObjectOutputStream(clienteTerminal.getOutputStream());
                        mensaje.writeObject(ticket2);
                        
                        ObjectInputStream respuesta2 = new ObjectInputStream(clienteTerminal.getInputStream());
                        String resultado2 = (String) respuesta2.readObject();
                        System.out.println(resultado2);
                        
                    }
                    
                    System.out.println("Que desea hacer:");
                    System.out.println("1 - Venta de ticket");
                    System.out.println("2 - Anulacion de ticket");
                    System.out.println("3 - salir");
                    objEnt = new BufferedReader(new InputStreamReader(System.in));
                    entrada = objEnt.readLine();
                }
                
                ObjectOutputStream mensaje3 = new ObjectOutputStream(clienteTerminal.getOutputStream());
                mensaje3.writeObject("salir");
                System.out.println("Conexion cerrada");
            }            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Terminales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
  
    
}
