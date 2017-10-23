/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 *
 * @author Rasmus
 */
@Named(value = "customerManagedBean")
@RequestScoped
public class CustomerManagedBean {
    @PersistenceContext(unitName="WebAppPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
        public CustomerManagedBean() {
    }
    public List<Customers> getCustomers()
    {
        TypedQuery<Customers> allCustomersQuery = em.createNamedQuery("Customers.findAll",Customers.class);
        
        return allCustomersQuery.getResultList();
    }
    public void addCustomer(Customers cust)
    {
        persist(cust);
    }
    
    
    /**
     * Creates a new instance of CustomerManagedBean
     */


    private void persist(Object obj) {
        try{
            utx.begin();
            em.persist(obj);
            utx.commit();
            
        }
        catch(Exception e)
            {
                Logger.getLogger(getClass().getName()).
                        log(Level.SEVERE, "exception caught", e);
                throw new RuntimeException(e);
                    
            }
        
    }
    
}
