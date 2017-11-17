/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;

/**
 *
 * @author Salvador
 */
@Stateless
public class PlaceBidBean implements PlaceBidBeanLocal {
    
    int i = 0;

    @Override
    
    
    @Schedules(value = { @Schedule(second="*/5",minute="*",hour="*", persistent = false)})
    public void cronTest(Timer timer){
        i = i+1;
       // System.out.println("Probando scheduler "+i);
    }
    
    @Resource TimerService timerService;    
    public void puntual(){
        timerService.createTimer(40000, i);
    }
    
    @Timeout
    public void timerCallback(Timer timer){
        System.out.println("Llego al fin en "+i);
    }
    
    @PersistenceContext
    EntityManager em;
    
    @Id
    private int cont = 1;

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }
    
    public void placeBid(Bid bid) {
        System.out.println(bid.getName());
        bid.setId(cont);
        em.persist(bid);
        //puntual();
    }
    
    public void guardar(Bid b1){
        
        b1.setName("persisto "+ this.cont);
        b1.setId(cont);
        this.cont = this.cont + 1;
        em.persist(b1);
    }
    
    
    
    
}
