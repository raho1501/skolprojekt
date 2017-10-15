/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Named;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;
/**
 *
 * @author linus
 */
@Named(value = "appointmentManagedBean")
@RequestScoped
public class AppointmentManagedBean
{
    @PersistenceContext(unitName="web_guitar_officialPU")
    private EntityManager entityManager;
    
    @Resource
    private javax.transaction.UserTransaction userTransaction;
    
    public AppointmentManagedBean()
    {
        
    }
    
    public List<Appointment> getAppointments()
    {
        TypedQuery<Appointment>  appointmentQuery = 
            entityManager.createNamedQuery("Appointment.findAll", Appointment.class);
        return appointmentQuery.getResultList();
    }
    
    public Appointment getAppointment(Integer id)
    {
        TypedQuery<Appointment>  appointmentQuery = 
		entityManager.createNamedQuery("Appointment.findByAppointmentId", Appointment.class).
			setParameter("appointmentId", id);
	List<Appointment> resList = appointmentQuery.getResultList();
	if(resList.isEmpty())
	{
		return new Appointment();
	}
	return resList.get(0);
    }
    
    public void addAppointment(Appointment appointment)
    {
        presist(appointment);
    }
    
    public void removeAppointment(Appointment appointment)
    {
	remove(appointment);
    }
    
	private void remove(Appointment appointment)
	{
		if(appointment.getImageUrl() != null)
		{
			try
			{
				Files.deleteIfExists(new File("/home/markus/NetBeansProjects/skolprojekt/web_guitar_official/web/resources/uploaded_img", appointment.getImageUrl()).toPath());
			}
			catch (IOException ex)
			{
				Logger.getLogger(AppointmentManagedBean.class.getName()).log(Level.SEVERE, null, ex);
				throw new RuntimeException(ex);
			}
		}

		try
		{
			userTransaction.begin();
			entityManager.remove(
				entityManager.merge(appointment));
			userTransaction.commit();
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass().getName()).
			    log(Level.SEVERE, "exception caught", e);
			throw new RuntimeException(e);
		}
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
