/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author Salvador
 */
@MessageDriven(
        name="OrderBillingMDB",
        mappedName="java:jboss/exported/jms/queue/test",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/test"),
            
        })
public class OrderBillingMDB implements MessageListener {
    
    @Override
    public void onMessage(Message message){
        
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;     
            ItemVO order = (ItemVO) objectMessage.getObject();
            System.out.println("Order billing MDB");
            System.out.println("proceso el mensaje direccion" + order.getShippingAddress() + " factura " + order.getBillingInfo() + " ID "+order.getId());
        } catch (JMSException ex) {
            Logger.getLogger(OrderBillingMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
