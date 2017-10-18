/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author e945952
 */
public class ServidorSocket {
    
    public static void main(String[] args){       
        
        try {          
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("servidor corriendo");
            
            while (true){
                Socket clienteAgencia = serverSocket.accept();
                hiloTerminal ht = new hiloTerminal(clienteAgencia);
                ht.start();
            }           
            
            } catch (IOException ex) {
                Logger.getLogger(ServidorSocket.class.getName()).log(Level.SEVERE, null, ex);      
            }
       
    }
}