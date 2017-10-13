/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
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
}
