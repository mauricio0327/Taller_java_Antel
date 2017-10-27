/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.Stateless;

/**
 *
 * @author Salvador
 */
@Stateless
public class PlaceBidBean implements PlaceBidBeanLocal {

    @Override
    public void placeBid(Bid bid) {
        System.out.println(bid.getName());
    }
    
}
