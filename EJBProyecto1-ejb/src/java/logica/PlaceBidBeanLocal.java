/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.Local;
import javax.ejb.Timer;

/**
 *
 * @author Salvador
 */
@Local
public interface PlaceBidBeanLocal {
    
    public void placeBid(Bid bid);
    public void cronTest(Timer timer);
    public void guardar(Bid b1);
    
}
