/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author markus
 */
@Named(value = "appointmentCustomerInput")
@RequestScoped
public class AppointmentCustomerInput
{
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	// TODO(markus): Kanske ska byta typen på startTime och endTime
	// till något annat.
	private String startTime;
	private String endTime;
	
	// TODO(markus): Kanske byta typen på date till något annat.
	private String date;
	
	private String info;
	
	
	@Inject
	private CustomerManagedBean customerManagedBean;
	@Inject
	private AppointmentManagedBean appointmentManagedBean;

	public AppointmentCustomerInput()
	{
	}
	
	public void setCustomerManagedBean(
		CustomerManagedBean customerManagedBean)
	{
		this.customerManagedBean = customerManagedBean;
	}
	
	public void setAppointmentManagedBean(
		AppointmentManagedBean appointmentManagedBean)
	{
		this.appointmentManagedBean = appointmentManagedBean;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	public String submit()
	{
		Customer cust = new Customer();
		Appointment appoint = new Appointment();
		
		cust.setFirstName(firstName);
		cust.setLastName(lastName);
		cust.setEmail(email);
		cust.setPhoneNr(phoneNumber);
		
		try
		{
			// NOTE(markus): Detta är bara test kod.
			// Det finns en bugg som uppstår om denna kod inte körs.
			Date test = new Date();
			appoint.setDate(test);
			appoint.setInfo(info);
			appoint.setStartTime(test);
			appoint.setStopTime(test);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		int custID = 0;
		List<Customer> customers = customerManagedBean.getCustomers();
		
		if(!customers.isEmpty())
		{
			custID = customers.get(
				customers.size() - 1).getCustomerId() + 1;
		}
		
		cust.setCustomerId(custID);
		appoint.setCustomerIdFk(cust);
		
		int appointID = 0;
		List<Appointment> appointments =
			appointmentManagedBean.getAppointments();
		
		if(!appointments.isEmpty())
		{
			appointID = appointments.get(
				appointments.size() - 1).
				getAppointmentId() + 1;
		}
		
		appoint.setAppointmentId(appointID);
		
		customerManagedBean.addCustomer(cust);
		appointmentManagedBean.addAppointment(appoint);
		
		return "index";
	}

}
