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
public class PremiumBid extends Bid {
    
    private String premiumDescrip;

    public String getPremiumDescrip() {
        return premiumDescrip;
    }

    public void setPremiumDescrip(String premiumDescrip) {
        this.premiumDescrip = premiumDescrip;
    }
    
    public PremiumBid(){
        
    }
    
}
