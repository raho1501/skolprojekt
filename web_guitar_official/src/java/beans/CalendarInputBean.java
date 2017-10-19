/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.http.Part;

/**
 *
 * @author markus
 */
@Named(value = "calendarInputBean")
@RequestScoped
public class CalendarInputBean {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	
	private String startTime;
	private String endTime;
	
	private String date;
	
	private String info;
	
	private Part imageFile;
	
	
	@Inject
	private CustomerManagedBean customerManagedBean;
	@Inject
	private AppointmentManagedBean appointmentManagedBean;
	@Inject
	private TimeReservationManagedBean timeReservationManagedBean;

	public CalendarInputBean()
	{
	}
	
	public void removeCustomer(Customer customer)
	{
		Appointment appointment =
			appointmentManagedBean.getAppointment(customer.getAppointmentIdFk());
	
		TimeReservation timeReservation =
			timeReservationManagedBean.getReservation(appointment.getTimeReservationIdFk());
		
		customerManagedBean.removeCustomer(customer);
		appointmentManagedBean.removeAppointment(appointment);
		timeReservationManagedBean.removeTimeReservation(timeReservation);
	}
	
	public TimeReservation getTimeReservation(Customer customer)
	{
		Appointment appointment = appointmentManagedBean.getAppointment(customer.getAppointmentIdFk());
		TimeReservation timeReservation = timeReservationManagedBean.getReservation(appointment.getTimeReservationIdFk());
		
		Calendar currendDate = Calendar.getInstance();
		
		Calendar timeReservationDate = Calendar.getInstance();
		timeReservationDate.setTime(timeReservation.getReservationDate());
		Calendar timeReservationEndTime = Calendar.getInstance();
		timeReservationEndTime.setTime(timeReservation.getStopTime());
		
		timeReservationDate.set(Calendar.HOUR_OF_DAY, timeReservationEndTime.get(Calendar.HOUR_OF_DAY));
		timeReservationDate.set(Calendar.MINUTE, timeReservationEndTime.get(Calendar.MINUTE));
		
		if(currendDate.compareTo(timeReservationDate) > 0)
		{
			removeCustomer(customer);
			return null;
		}
		return timeReservation;
	}
	
	private String toUTF_8(String s)
	{
		byte[] ptext = s.getBytes(ISO_8859_1);
		return new String(ptext, UTF_8);
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
	
	public void setTimeReservationManagedBean(
		TimeReservationManagedBean timeReservationManagedBean)
	{
		this.timeReservationManagedBean = timeReservationManagedBean;
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
		this.firstName = toUTF_8(firstName);
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
		this.lastName = toUTF_8(lastName);
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
		this.email = toUTF_8(email);
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
		this.info = toUTF_8(info);
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
	
	/**
	 * @return the imageFile
	 */
	public Part getImageFile() {
		return imageFile;
	}

	/**
	 * @param imageFile the imageFile to set
	 */
	public void setImageFile(Part imageFile) {
		this.imageFile = imageFile;
	}
	
	public String saveImage() throws MessagingException
	{
		String filename = imageFile.getSubmittedFileName();
		
		int indexOfExtension = filename.indexOf(".");
		if(indexOfExtension <= 0)
		{
			throw new RuntimeException("Filename or file type is not supported.");
		}
		
		String extension = filename.substring(indexOfExtension, filename.length());
		
		if(!".png".equals(extension) &&
			!".jpg".equals(extension) &&
			!".jpeg".equals(extension) &&
			!".gif".equals(extension))
		{
			throw new RuntimeException("Filename or file type is not supported.");
		}
		
		String randFileName = UUID.randomUUID().toString();
		
		filename = randFileName + extension;
		// /home/markus/NetBeansProjects/skolprojekt/web_guitar_official/web/resources/uploaded_img
		// /var/web_guitar_official/images
		File savedFile = new File(Constants.uploadPath, filename);

		try(InputStream input = imageFile.getInputStream())
		{
			Files.copy(input, savedFile.toPath());
		}
		catch(IOException e)
		{
			Logger.getLogger(getClass().getName()).
				log(Level.SEVERE, "exception caught", e);
			throw new RuntimeException(e);
		}
		
		return filename;
	}
	
	public String submit()
	{
		if(firstName.isEmpty()) return "redirect";
		if(lastName.isEmpty()) return "redirect";
		if(email.isEmpty()) return "redirect";
		if(phoneNumber.isEmpty()) return "redirect";

		if(startTime.isEmpty()) return "redirect";
		if(endTime.isEmpty()) return "redirect";
		if(date.isEmpty()) return "redirect";

		if(info.isEmpty()) return "redirect";
		
		String filename = null;
		
		if(imageFile != null)
		{
			try
			{
				filename = saveImage();
			}
			catch(MessagingException e)
			{
				Logger.getLogger(getClass().getName()).
					log(Level.SEVERE, "exception caught", e);
				throw new RuntimeException(e);
			}
		}
		
		Customer cust = new Customer();
		Appointment appoint = new Appointment();
		TimeReservation reservation = new TimeReservation();
		
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
			
			reservation.setStartTime(parsedStartTime);
			reservation.setStopTime(parsedEndTime);
			reservation.setReservationDate(parsedDate);
		}
		catch(ParseException e)
		{
			throw new RuntimeException(e);
		}
		
		timeReservationManagedBean.addTimeReservation(reservation);
		
		appoint.setTimeReservationIdFk(reservation.getTimeReservationId());
		appoint.setInfo(info);
		appoint.setImageUrl(filename);
		
		appointmentManagedBean.addAppointment(appoint);
		
		cust.setAppointmentIdFk(appoint.getAppointmentId());
		cust.setFirstName(firstName);
		cust.setLastName(lastName);
		cust.setEmail(email);
		cust.setPhoneNr(phoneNumber);
		
		customerManagedBean.addCustomer(cust);
		
		return "redirect_calendar";
	}
}
