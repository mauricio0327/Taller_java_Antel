/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.hibernate.engine.internal.Cascade;

/**
 *
 * @author Salvador
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="AB_Bid")
public class Bid implements Serializable {
    
    
    
    private String name;         
    @Id
    private int id = 1;
    @OneToOne(cascade = CascadeType.ALL)
    private ItemVO order;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public ItemVO getOrder() {
        return order;
    }
    
    public void setOrder(ItemVO order) {
        this.order = order;
    }
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @PrePersist
    public void prePersist(){
        Calendar c = Calendar.getInstance(); 
        Date date = c.getTime();
        System.out.println("Va a persistir a la hora: "+date);
    }
    
    
}
