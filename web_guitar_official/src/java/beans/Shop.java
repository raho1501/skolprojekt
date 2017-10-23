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
@Table(name = "SHOP")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Shop.findAll", query = "SELECT s FROM Shop s")
	, @NamedQuery(name = "Shop.findByShopId", query = "SELECT s FROM Shop s WHERE s.shopId = :shopId")
	, @NamedQuery(name = "Shop.findByTitle", query = "SELECT s FROM Shop s WHERE s.title = :title")
	, @NamedQuery(name = "Shop.findByImageUrl", query = "SELECT s FROM Shop s WHERE s.imageUrl = :imageUrl")
	, @NamedQuery(name = "Shop.findByInfo", query = "SELECT s FROM Shop s WHERE s.info = :info")
	, @NamedQuery(name = "Shop.findByPrice", query = "SELECT s FROM Shop s WHERE s.price = :price")
	, @NamedQuery(name = "Shop.findByCustomerIdFk", query = "SELECT s FROM Shop s WHERE s.customerIdFk = :customerIdFk")})
public class Shop implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "SHOP_ID")
	private Integer shopId;
	@Size(max = 64)
        @Column(name = "TITLE")
	private String title;
	@Size(max = 256)
        @Column(name = "IMAGE_URL")
	private String imageUrl;
	@Size(max = 1024)
        @Column(name = "INFO")
	private String info;
	@Column(name = "PRICE")
	private Integer price;
	@Column(name = "CUSTOMER_ID_FK")
	private Integer customerIdFk;

	public Shop() {
	}

	public Shop(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCustomerIdFk() {
		return customerIdFk;
	}

	public void setCustomerIdFk(Integer customerIdFk) {
		this.customerIdFk = customerIdFk;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (shopId != null ? shopId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Shop)) {
			return false;
		}
		Shop other = (Shop) object;
		if ((this.shopId == null && other.shopId != null) || (this.shopId != null && !this.shopId.equals(other.shopId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "beans.Shop[ shopId=" + shopId + " ]";
	}
	
}
