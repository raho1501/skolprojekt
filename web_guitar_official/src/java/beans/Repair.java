/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author markus
 */
@Entity
@Table(name = "REPAIR")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Repair.findAll", query = "SELECT r FROM Repair r")
	, @NamedQuery(name = "Repair.findByRepairId", query = "SELECT r FROM Repair r WHERE r.repairId = :repairId")
	, @NamedQuery(name = "Repair.findByInfo", query = "SELECT r FROM Repair r WHERE r.info = :info")
	, @NamedQuery(name = "Repair.findByTimeReservationIdFk", query = "SELECT r FROM Repair r WHERE r.timeReservationIdFk = :timeReservationIdFk")})
public class Repair implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "REPAIR_ID")
	private Integer repairId;
	@Size(max = 256)
        @Column(name = "INFO")
	private String info;
	@Column(name = "TIME_RESERVATION_ID_FK")
	private Integer timeReservationIdFk;

	public Repair() {
	}

	public Repair(Integer repairId) {
		this.repairId = repairId;
	}

	public Integer getRepairId() {
		return repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getTimeReservationIdFk() {
		return timeReservationIdFk;
	}

	public void setTimeReservationIdFk(Integer timeReservationIdFk) {
		this.timeReservationIdFk = timeReservationIdFk;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (repairId != null ? repairId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Repair)) {
			return false;
		}
		Repair other = (Repair) object;
		if ((this.repairId == null && other.repairId != null) || (this.repairId != null && !this.repairId.equals(other.repairId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.Repair[ repairId=" + repairId + " ]";
	}
	
}
