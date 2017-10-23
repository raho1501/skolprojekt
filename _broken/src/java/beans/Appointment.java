/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author linus
 */
@Entity
@Table(name = "APPOINTMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")
    , @NamedQuery(name = "Appointment.findByAppointmentId", query = "SELECT a FROM Appointment a WHERE a.appointmentId = :appointmentId")
    , @NamedQuery(name = "Appointment.findByInfo", query = "SELECT a FROM Appointment a WHERE a.info = :info")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APPOINTMENT_ID")
    private Integer appointmentId;
    @Size(max = 256)
    @Column(name = "INFO")
    private String info;
    @JoinColumn(name = "TIME_RESERVATION_ID_FK", referencedColumnName = "TIME_RESERVATION_ID")
    @ManyToOne
    private TimeReservation timeReservationIdFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointmentIdFk")
    private Collection<Customer> customerCollection;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public TimeReservation getTimeReservationIdFk() {
        return timeReservationIdFk;
    }

    public void setTimeReservationIdFk(TimeReservation timeReservationIdFk) {
        this.timeReservationIdFk = timeReservationIdFk;
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
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
