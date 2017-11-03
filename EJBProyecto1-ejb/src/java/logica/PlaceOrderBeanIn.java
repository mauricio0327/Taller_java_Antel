/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.Local;

/**
 *
 * @author Salvador
 */

@Local
public interface PlaceOrderBeanIn {
    
    public void postConstruct();
    void addItem(ItemVO item);
    void setShippingInfo(String shippingAddres);
    void setBillingInfo(String billingInfo);
    long confirmOrder();
    public String datos();
    public void sendMessage();
    
    
}
