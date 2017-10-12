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
import javax.transaction.UserTransaction;

/**
 *
 * @author markus
 */
@Named(value = "timeReservationManagedBean")
@RequestScoped
public class TimeReservationManagedBean {

	@PersistenceContext(unitName="web_guitar_officialPU")
	private EntityManager entityManager;
	
	@Resource
	private UserTransaction userTransaction;
	/**
	 * Creates a new instance of TimeReservationManagedBean
	 */
	public TimeReservationManagedBean() {
	}
	
	public List<TimeReservation> getTimeReservations()
	{
		TypedQuery<TimeReservation> query = 
			entityManager.createNamedQuery(
			"TimeReservation.findAll", TimeReservation.class);
		return query.getResultList();
	}
	public TimeReservation getReservation(Integer id)
        {
            TypedQuery<TimeReservation>  appointmentQuery = 
                entityManager.createNamedQuery("TimeReservation.findByTimeReservationId", TimeReservation.class).setParameter("timeReservationId", id);
            return appointmentQuery.getResultList().get(0);  // TODO Kanske borde se till att vi inte krashar här.
        }
	public void removeTimeReservation(TimeReservation timeReservation)
	{
		remove(timeReservation);
	}
	
	private void remove(TimeReservation timeReservation)
	{
		try
		{
			userTransaction.begin();
			entityManager.remove(entityManager.merge(timeReservation));
			userTransaction.commit();
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass().getName()).
				log(Level.SEVERE, "exception caught", e);
			throw new RuntimeException(e);
		}
	}
	
	public void addTimeReservation(TimeReservation timeReservation)
	{
		persist(timeReservation);
	}
	
	private void persist(TimeReservation timeReservation)
	{
		try
		{
			userTransaction.begin();
			entityManager.persist(timeReservation);
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
