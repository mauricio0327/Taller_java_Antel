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

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "ventaTK")
    public Ticket venta(Ticket tk) {
        ControladorIMM ctrl = new ControladorIMM();
        if (ctrl.ventaTicket(tk)){
            tk.setCodigo(ctrl.getCd());
        }
        return tk;
    }
}
