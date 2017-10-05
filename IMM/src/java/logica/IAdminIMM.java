/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author Salvador
 */
public interface IAdminIMM {
    public abstract boolean loginAdmin(DtUsuario dtUser);
    public abstract boolean ventaTicket(Ticket ticket);
    public String codGen();
    
}
