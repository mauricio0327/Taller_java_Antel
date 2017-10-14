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
        Ticket tk2 = new Ticket();
        tk2 = tk;
        tk2.setNumero(ControladorIMM.getInstancia().ventaTicket(tk));
        tk2.setImporteTotal(ControladorIMM.getInstancia().importe(tk.getCantMin()));
        return tk2;
    }
    
    @WebMethod(operationName = "anulacionIMM")
    public String anulacion(String numero, String agencia) {
        String codigo = ControladorIMM.getInstancia().anulacionTk(numero, agencia);
        return codigo;
    }
    
    @WebMethod
    public String sayHello(String name){
        return("Hello "+name);
    }
    
}
