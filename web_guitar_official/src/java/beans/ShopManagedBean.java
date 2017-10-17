/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
	
	public Shop getShop(Integer id)
	{
		TypedQuery<Shop> typedShop = entityManager.createNamedQuery("Shop.findByShopId", Shop.class).
			setParameter("shopId", id);
		List<Shop> resList = typedShop.getResultList();
		if(resList.isEmpty())
		{
			return new Shop();
		}
		return resList.get(0);
	}
	
	public void updateShop(Shop shop)
	{
		update(shop);
	}
	
	public void addShop(Shop shop)
	{
		presist(shop);
	}
	
	private void update(Shop shop)
	{
		try
		{
			userTransaction.begin();
			entityManager.merge(shop);
			userTransaction.commit();
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass().getName()).
				log(Level.SEVERE, "exception caught", e);
			throw new RuntimeException(e);
		}
	}
	
	private void presist(Shop shop)
	{
		try
		{
			userTransaction.begin();
			entityManager.persist(shop);
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
