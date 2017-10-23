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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author markus
 */
@Entity
@Table(name = "BUDGET")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Budget.findAll", query = "SELECT b FROM Budget b")
	, @NamedQuery(name = "Budget.findByBudgetId", query = "SELECT b FROM Budget b WHERE b.budgetId = :budgetId")
	, @NamedQuery(name = "Budget.findByAmount", query = "SELECT b FROM Budget b WHERE b.amount = :amount")
	, @NamedQuery(name = "Budget.findByInfo", query = "SELECT b FROM Budget b WHERE b.info = :info")
	, @NamedQuery(name = "Budget.findByDateTime", query = "SELECT b FROM Budget b WHERE b.dateTime = :dateTime")})
public class Budget implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "BUDGET_ID")
	private Integer budgetId;
	@Column(name = "AMOUNT")
	private Integer amount;
	@Size(max = 512)
        @Column(name = "INFO")
	private String info;
	@Column(name = "DATE_TIME")
        @Temporal(TemporalType.DATE)
	private Date dateTime;

	public Budget() {
	}

	public Budget(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (budgetId != null ? budgetId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Budget)) {
			return false;
		}
		Budget other = (Budget) object;
		if ((this.budgetId == null && other.budgetId != null) || (this.budgetId != null && !this.budgetId.equals(other.budgetId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.Budget[ budgetId=" + budgetId + " ]";
	}
	
}
