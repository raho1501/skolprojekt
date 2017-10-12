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
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author markus
 */
@Named(value = "customerManagedBean")
@RequestScoped
public class CustomerManagedBean
{
	@PersistenceContext(unitName="web_guitar_officialPU")
	private EntityManager entityManager;
	
	@Resource
	private UserTransaction userTransaction;
	
	public CustomerManagedBean()
	{
	}
	
	public List<Customer> getCustomers()
	{
		TypedQuery<Customer> typedQuery =
			entityManager.createNamedQuery(
			"Customer.findAll", Customer.class);
		return typedQuery.getResultList();
	}
	public void removeCustomer(Customer customer)
	{
		remove(customer);
	}
	
	public void addCustomer(Customer customer)
	{
		persist(customer);
	}
	
	private void remove(Customer customer)
	{
		try
		{
			userTransaction.begin();
			entityManager.remove(entityManager.merge(customer.getAppointmentIdFk()));
			userTransaction.commit();
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass().getName()).
				log(Level.SEVERE, "exception caught", e);
			throw new RuntimeException(e);
		}
	}

	private void persist(Customer customer)
	{
		try
		{
			userTransaction.begin();
			entityManager.persist(customer);
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
