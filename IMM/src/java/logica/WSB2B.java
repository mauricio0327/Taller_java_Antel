/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Salvador
 */
@WebService(serviceName = "WSB2B")
public class WSB2B {

   // private Fabrica fabrica = Fabrica.getInstance();
    private ControladorIMM controlador;
    /**
     * This is a sample web service operation
     * @param tk
     * @return 
     */
    @WebMethod(operationName = "ventaIMM")
    public Ticket venta(Ticket tk) {
        //controlador = ControladorIMM.getInstancia();
        if (ControladorIMM.getInstancia().ventaTicket(tk)){
            tk.setCodigo(ControladorIMM.getInstancia().codGen());
        }
        return tk;
    }
    
    @WebMethod
    public String sayHello(String name){
        return("Hello "+name);
    }
    
}
