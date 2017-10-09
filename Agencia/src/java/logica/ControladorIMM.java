/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Salvador
 */
public class ControladorIMM {
 
    private static ControladorIMM instance = null;
	
    private ControladorIMM(){}
    
    public static ControladorIMM getInstancia(){
	if(instance == null){
        	instance = new ControladorIMM();
	}
            return instance;
    }
    

    public Ticket ventaTicketIMM(Ticket tk) {         
           return ventaIMM(tk);
    }

    public String anulacionTicketTerminal(String numero, String agencia) {
         return "jeje";
    }

    private static Ticket ventaIMM(logica.Ticket arg0) {
        logica.WSB2B_Service service = new logica.WSB2B_Service();
        logica.WSB2B port = service.getWSB2BPort();
        return port.ventaIMM(arg0);
    }


    
}