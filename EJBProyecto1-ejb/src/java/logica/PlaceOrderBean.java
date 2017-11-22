/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Salvador
 */

@Stateful
public class PlaceOrderBean implements PlaceOrderBeanIn {
    
    ItemVO order;
    Bid bid;
    
    @PostConstruct
    @Override
    public void postConstruct(){
        order = new ItemVO();
        order.setEstado(State.IN_PROGRESS);
        em.persist(order);
        
    }

    @Override
    public void addItem(ItemVO item) {
        
    }

    @Override
    public void setShippingInfo(String shippingAddres) {
        order.setShippingAddress(shippingAddres);
        em.merge(order);
        
    }

    @Override
    public void setBillingInfo(String billingInfo) {
        order.setBillingInfo(billingInfo);
        em.merge(order);
        
    }
    
    public void setBidId(String bidId){
        int i = Integer.parseInt(bidId);
        order.setBidid(i);
        em.merge(order);
        
        
    }
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void confirmOrder() {
        bid = em.find(Bid.class, order.getBidid());
        bid.setOrder(order);
        em.merge(bid);
        order.setBid(bid);
        //em.flush();
        em.merge(order);
            
        
        
    }
    
    public String datos(){
        return (order.getShippingAddress()+" "+order.getBillingInfo()+" "+order.getId());
    }
    
    @Resource(name = "java:/ConnectionFactory")
    ConnectionFactory cf;
    
    @Resource(name = "java:jboss/exported/jms/queue/test")
    Destination queue;
    
    public void sendMessage(){
        try {
            Connection conn = cf.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            conn.start();
            MessageProducer sender = session.createProducer(queue);
            //TextMessage textMessage = session.createTextMessage();
            ObjectMessage message = session.createObjectMessage(order);
            sender.send(message);
            sender.close();
            session.close();
            conn.close();
            System.out.println("Mensaje enviado");
            order.setEstado(State.DONE);
            em.merge(order);
            
        } catch (JMSException ex) {
            Logger.getLogger(PlaceOrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public State getEstado(){
        return order.getEstado();
    }

    
    
}
