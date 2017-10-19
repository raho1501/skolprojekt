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
@Named(value = "leaveInputBean")
@RequestScoped
public class LeaveInputBean {
	
	private String date;
	private String startTime;
	private String stopTime;
	
	@Inject
	private LeaveManagedBean leaveManagedBean;
	@Inject
	private TimeReservationManagedBean timeReservationManagedBean;

	/**
	 * Creates a new instance of LeaveInputBean
	 */
	public LeaveInputBean() {
	}
	
	/**
	 * @param leaveManagedBean the leaveManagedBean to set
	 */
	public void setLeaveManagedBean(LeaveManagedBean leaveManagedBean) {
		this.leaveManagedBean = leaveManagedBean;
	}
	
	/**
	 * @param timeReservationManagedBean the timeReservationManagedBean to set
	 */
	public void setTimeReservationManagedBean(TimeReservationManagedBean timeReservationManagedBean) {
		this.timeReservationManagedBean = timeReservationManagedBean;
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
	 * @return the stopTime
	 */
	public String getStopTime() {
		return stopTime;
	}

	/**
	 * @param stopTime the stopTime to set
	 */
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	
	public String submit()
	{
		if(startTime.isEmpty()) return "redirect";
		if(stopTime.isEmpty()) return "redirect";
		if(date.isEmpty()) return "redirect";
		
		Leave leave = new Leave();
		TimeReservation timeReservation = new TimeReservation();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		Date parsedDate;
		Date parsedStartTime;
		Date parsedEndTime;
		
		try
		{
			parsedDate = dateFormat.parse(date);
			parsedStartTime = timeFormat.parse(startTime);
			parsedEndTime = timeFormat.parse(stopTime);
			
			timeReservation.setStartTime(parsedStartTime);
			timeReservation.setStopTime(parsedEndTime);
			timeReservation.setReservationDate(parsedDate);
		}
		catch(ParseException e)
		{
			throw new RuntimeException(e);
		}
		
		timeReservationManagedBean.addTimeReservation(timeReservation);
		
		leave.setTimeReservationIdFk(timeReservation.getTimeReservationId());
		
		leaveManagedBean.addLeave(leave);
		
		return "testLeaveInsertPage";
	}
}
