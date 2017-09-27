/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		cust.setCustomerId(0);
		
		customerManagedBean.addCustomer(cust);
		
		appoint.setCustomerIdFk(cust);
		
		appoint.setInfo(info);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		Date parsedDate;
		Date parsedStartTime;
		Date parsedEndTime;
		
		try
		{
			parsedDate = dateFormat.parse(date);
			parsedStartTime = timeFormat.parse(startTime);
			parsedEndTime = timeFormat.parse(endTime);
			
			appoint.setDate(parsedDate);
			appoint.setStartTime(parsedStartTime);
			appoint.setStopTime(parsedEndTime);
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
		appointmentManagedBean.addAppointment(appoint);
		
		return "index";
	}
}