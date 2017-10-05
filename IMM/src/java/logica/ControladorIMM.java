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
    
    private ArrayList<String> codigos = new ArrayList<String>();
    private int cont = 0;
    private String cd; 

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }
    
    public ControladorIMM(){

        
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
