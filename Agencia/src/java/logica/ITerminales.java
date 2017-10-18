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
public interface ITerminales {
    
    public abstract boolean loginUser(DtUsuario dtUser);
    
    public abstract void ventaTicketTerminal(Ticket tk);
    
    public abstract void anulacionTicketTerminal(String numero, String codigo);
    
    public abstract boolean controlAnulacion(String numero);
}
