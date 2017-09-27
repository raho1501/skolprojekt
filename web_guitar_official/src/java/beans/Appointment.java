/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author markus
 */
@Entity
@Table(name = "APPOINTMENT")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")
	, @NamedQuery(name = "Appointment.findByAppointmentId", query = "SELECT a FROM Appointment a WHERE a.appointmentId = :appointmentId")
	, @NamedQuery(name = "Appointment.findByStartTime", query = "SELECT a FROM Appointment a WHERE a.startTime = :startTime")
	, @NamedQuery(name = "Appointment.findByStopTime", query = "SELECT a FROM Appointment a WHERE a.stopTime = :stopTime")
	, @NamedQuery(name = "Appointment.findByInfo", query = "SELECT a FROM Appointment a WHERE a.info = :info")
	, @NamedQuery(name = "Appointment.findByDate", query = "SELECT a FROM Appointment a WHERE a.date = :date")})
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "APPOINTMENT_ID")
	private Integer appointmentId;
	@Column(name = "START_TIME")
        @Temporal(TemporalType.TIME)
	private Date startTime;
	@Column(name = "STOP_TIME")
        @Temporal(TemporalType.TIME)
	private Date stopTime;
	@Size(max = 256)
        @Column(name = "INFO")
	private String info;
	@Column(name = "DATE")
        @Temporal(TemporalType.DATE)
	private Date date;
	@JoinColumn(name = "CUSTOMER_ID_FK", referencedColumnName = "CUSTOMER_ID")
        @ManyToOne(optional = false)
	private Customer customerIdFk;

	public Appointment() {
	}

	public Appointment(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getCustomerIdFk() {
		return customerIdFk;
	}

	public void setCustomerIdFk(Customer customerIdFk) {
		this.customerIdFk = customerIdFk;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (appointmentId != null ? appointmentId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Appointment)) {
			return false;
		}
		Appointment other = (Appointment) object;
		if ((this.appointmentId == null && other.appointmentId != null) || (this.appointmentId != null && !this.appointmentId.equals(other.appointmentId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.Appointment[ appointmentId=" + appointmentId + " ]";
	}
	
}
