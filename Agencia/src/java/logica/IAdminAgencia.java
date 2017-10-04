
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;

/**
 *
 * @author e945952
 */
public interface IAdminAgencia {
    
    public abstract void agregarUsuario(String user, String passwd, String nombre, String apellido, String admin) throws NamingException;
    
    public abstract boolean loginAdmin(DtUsuario dtUser);
    
    public abstract ArrayList<Ticket> ventaTotalDiaria();
    
    public abstract ArrayList<Ticket> ventaTotalPorHora(Date horaiIni, Date horaFin);
    
}
