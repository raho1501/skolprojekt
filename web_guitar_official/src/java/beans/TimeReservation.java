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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author markus
 */
@Entity
@Table(name = "TIME_RESERVATION")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TimeReservation.findAll", query = "SELECT t FROM TimeReservation t")
	, @NamedQuery(name = "TimeReservation.findByTimeReservationId", query = "SELECT t FROM TimeReservation t WHERE t.timeReservationId = :timeReservationId")
	, @NamedQuery(name = "TimeReservation.findByStartTime", query = "SELECT t FROM TimeReservation t WHERE t.startTime = :startTime")
	, @NamedQuery(name = "TimeReservation.findByStopTime", query = "SELECT t FROM TimeReservation t WHERE t.stopTime = :stopTime")
	, @NamedQuery(name = "TimeReservation.findByReservationDate", query = "SELECT t FROM TimeReservation t WHERE t.reservationDate = :reservationDate")})
public class TimeReservation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "TIME_RESERVATION_ID")
	private Integer timeReservationId;
	@Column(name = "START_TIME")
        @Temporal(TemporalType.TIME)
	private Date startTime;
	@Column(name = "STOP_TIME")
        @Temporal(TemporalType.TIME)
	private Date stopTime;
	@Column(name = "RESERVATION_DATE")
        @Temporal(TemporalType.DATE)
	private Date reservationDate;

	public TimeReservation() {
	}

	public TimeReservation(Integer timeReservationId) {
		this.timeReservationId = timeReservationId;
	}

	public Integer getTimeReservationId() {
		return timeReservationId;
	}

	public void setTimeReservationId(Integer timeReservationId) {
		this.timeReservationId = timeReservationId;
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

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (timeReservationId != null ? timeReservationId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TimeReservation)) {
			return false;
		}
		TimeReservation other = (TimeReservation) object;
		if ((this.timeReservationId == null && other.timeReservationId != null) || (this.timeReservationId != null && !this.timeReservationId.equals(other.timeReservationId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.TimeReservation[ timeReservationId=" + timeReservationId + " ]";
	}
	
}
