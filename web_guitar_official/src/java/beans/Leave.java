/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package beans;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author markus
 */
@Entity
@Table(name = "LEAVE")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Leave.findAll", query = "SELECT l FROM Leave l")
	, @NamedQuery(name = "Leave.findByLeaveId", query = "SELECT l FROM Leave l WHERE l.leaveId = :leaveId")})
public class Leave implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "LEAVE_ID")
	private Integer leaveId;
	@JoinColumn(name = "TIME_RESERVATION_ID_FK", referencedColumnName = "TIME_RESERVATION_ID")
        @ManyToOne
	private TimeReservation timeReservationIdFk;

	public Leave() {
	}

	public Leave(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public TimeReservation getTimeReservationIdFk() {
		return timeReservationIdFk;
	}

	public void setTimeReservationIdFk(TimeReservation timeReservationIdFk) {
		this.timeReservationIdFk = timeReservationIdFk;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (leaveId != null ? leaveId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Leave)) {
			return false;
		}
		Leave other = (Leave) object;
		if ((this.leaveId == null && other.leaveId != null) || (this.leaveId != null && !this.leaveId.equals(other.leaveId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.Leave[ leaveId=" + leaveId + " ]";
	}
	
}
