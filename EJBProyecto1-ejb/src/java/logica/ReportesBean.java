/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Salvador
 */
@Stateless
public class ReportesBean {
    
    List<ItemVO> lista;

    public List<ItemVO> getLista() {
        return lista;
    }

    public void setLista(List<ItemVO> lista) {
        this.lista = lista;
    }
    
    @PersistenceContext
    EntityManager em;
      
    public void reportes(){
        
        Query query = em.createQuery("Select o From ItemVO as o WHERE o.estado = 'DONE'");
        lista = query.getResultList();
        System.out.println(lista.get(0).getShippingAddress());
        
    }
    
}
