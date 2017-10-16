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
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author markus
 */
@Named(value = "leaveManagedBean")
@RequestScoped
public class LeaveManagedBean {

	@PersistenceContext(unitName="web_guitar_officialPU")
	private EntityManager entityManager;

	@Resource
	private javax.transaction.UserTransaction userTransaction;
	/**
	 * Creates a new instance of LeaveManagedBean
	 */
	public LeaveManagedBean() {
	}

	public List<Leave> getLeaves()
	{
		TypedQuery<Leave> typedLeave = entityManager.createNamedQuery("Leave.findAll", Leave.class);
		return typedLeave.getResultList();
	}
	
	public void removeLeave(Leave leave)
	{
		remove(leave);
	}
	
	public void addLeave(Leave leave)
	{
		persist(leave);
	}
	
	private void remove(Leave leave)
	{
		try
		{
			userTransaction.begin();
			entityManager.remove(entityManager.merge(leave));
			userTransaction.commit();
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass().getName()).
				log(Level.SEVERE, "exception caught", e);
			throw new RuntimeException(e);
		}
	}

	private void persist(Leave leave)
	{
		try
		{
			userTransaction.begin();
			entityManager.persist(leave);
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
