/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Salvador
 */
public class ControladorIMM implements IAdminIMM{
    
    private ArrayList<String> codigos;
    private ArrayList<String> numeros;
    private ArrayList<String> agencias;
    private int cont;
    
    
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

    
    public String ventaTicket(Ticket ticket) {
        String num = "";
        boolean valida = false;
        Iterator<String> it = agencias.iterator();
        while ((it.hasNext())&&(!valida)){
           if (it.equals(ticket.getAgencia())){
               valida = true;
           }
        }
        if (valida){
            num = codGen();
            this.codigos.add(num);
            
        }
        
        return num;
    }

    @Override
    public String codGen() {
        this.cont=(this.cont+1);
        int c = this.cont;
        return String.valueOf(c);
        
    }
    
}
