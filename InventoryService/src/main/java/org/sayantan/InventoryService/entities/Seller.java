package org.sayantan.InventoryService.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
@Entity
@Table(name = "seller",schema = "inventorydb")
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sellerid",columnDefinition = "INT")
	private Long sellerId;
	@Column(name="name")
	@Size(min=3,max=45,message = "Seller Name should be 3-45 character long")
	private String sellerName;
	@Column(name="address")
	@Size(min=10,max=200,message = "Seller Address should be 10-200 character long")
	private String sellerAddress;
	@Column(name="registrationno")
	@Size(min=10,max=20,message = "Registration No should be 10-20 character long")
	private String registrationNumber;
	@Column(name="joiningdate",columnDefinition = "DATETIME")
	@PastOrPresent(message = "Joining Date should be equal to or less than curent date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date joiningDate;
	public Seller() {
		super();
	}
	public Seller(String sellerName, String sellerAddress, String registrationNumber, Date joiningDate) {
		super();
		this.sellerName = sellerName;
		this.sellerAddress = sellerAddress;
		this.registrationNumber = registrationNumber;
		this.joiningDate = joiningDate;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerAddress() {
		return sellerAddress;
	}
	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	
}
