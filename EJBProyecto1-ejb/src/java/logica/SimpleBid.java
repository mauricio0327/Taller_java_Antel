/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Salvador
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class SimpleBid extends Bid {
    private String SimpleDescr;

    public String getSimpleDescr() {
        return SimpleDescr;
    }

    public void setSimpleDescr(String SimpleDescr) {
        this.SimpleDescr = SimpleDescr;
    }
    
    public SimpleBid(){
        
    }
    
}
