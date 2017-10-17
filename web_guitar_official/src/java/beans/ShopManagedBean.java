/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author markus
 */
@Named(value = "shopManagedBean")
@RequestScoped
public class ShopManagedBean {

	@PersistenceContext(unitName="web_guitar_officialPU")
	private EntityManager entityManager;

	@Resource
	private javax.transaction.UserTransaction userTransaction;
	
	/**
	 * Creates a new instance of ShopManagedBean
	 */
	
	
	public ShopManagedBean() {
	}
	
	public List<Shop> getShops()
	{
		TypedQuery<Shop> typedShop = entityManager.createNamedQuery("Shop.findAll", Shop.class);
		return typedShop.getResultList();
	}
        
        public List<Shop> getRandomShops(int amnt) {
            TypedQuery<Shop> typedShop = entityManager.createNamedQuery("Shop.findAll", Shop.class);
            List<Shop> resultList = typedShop.getResultList();
            List<Shop> outputList = new ArrayList<Shop>();
            Random rnd = new Random();
            for (int i = 0; i < amnt && 0 < resultList.size(); i++) {
                outputList.add(resultList.remove(rnd.nextInt(resultList.size())));
            }
            return outputList;
        }
	
	public void addAppointment(Appointment appointment)
	{
		presist(appointment);
	}
	
	private void presist(Appointment appointment)
	{
		try
		{
			userTransaction.begin();
			entityManager.persist(appointment);
			userTransaction.commit();
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass().getName()).
				log(Level.SEVERE, "exception caught", e);
			throw new RuntimeException(e);
		}
	}
}
