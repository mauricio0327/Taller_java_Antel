/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 *
 * @author Salvador
 */
@Stateless
public class PlaceBidBean implements PlaceBidBeanLocal {
    
    int i = 0;

    @Override
    public void placeBid(Bid bid) {
        System.out.println(bid.getName());
        puntual();
    }
    
    @Schedules(value = { @Schedule(second="*/5",minute="*",hour="*", persistent = false)})
    public void cronTest(Timer timer){
        i = i+1;
        System.out.println("Probando scheduler "+i);
    }
    
    @Resource TimerService timerService;    
    public void puntual(){
        timerService.createTimer(40000, i);
    }
    
    @Timeout
    public void timerCallback(Timer timer){
        System.out.println("Llego al fin en "+i);
    }
    
    
}
