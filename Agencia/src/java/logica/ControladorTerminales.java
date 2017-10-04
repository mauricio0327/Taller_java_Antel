/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author e945952
 */
public class ControladorTerminales implements ITerminales{

    @Override
    public boolean loginUser(DtUsuario dtUser) {
        return true;
    }

    @Override
    public boolean ventaTicketTerminal(Ticket tk) {
           return true;
    }

    @Override
    public boolean anulacionTicketTerminal(String numero) {
         return true;
    }
    
}