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
    private int cont;
    private String cd; 
    
    private static ControladorIMM instance = null;
	
    
    public static ControladorIMM getInstancia(){
	if(instance == null){
        	instance = new ControladorIMM();
	}
            return instance;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }
    
    public ControladorIMM(){
        this.codigos= new ArrayList<String>();
        this.cont = 0;
        this.cd = "";
        
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

    
    public boolean ventaTicket(Ticket ticket) {
        boolean valida = true;
        Iterator<String> it = codigos.iterator();
        while (it.hasNext()){
           if (it.equals(ticket.getCodigo())){
               valida = false;
           }
        }
        if (valida){
            this.codigos.add(codGen());
            
        }
        
        return valida;
    }

    @Override
    public String codGen() {
        this.cont=(this.cont+1);
        int c = this.cont;
        this.cd = String.valueOf(c);
        return String.valueOf(c);
        
    }
    
}
