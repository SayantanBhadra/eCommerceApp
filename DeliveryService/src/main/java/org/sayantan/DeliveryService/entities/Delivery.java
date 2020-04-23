package org.sayantan.DeliveryService.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="delivery_tracker",schema = "deliverydb")
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="trackerid",columnDefinition = "INT")
	private Long trackerId;
	@Column(name="status")
	@Size(min=5,max=20,message = "Status should be 5-20 characters long")
	private String status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedon",columnDefinition = "DATETIME")
	@PastOrPresent(message = "Updated On should less than or equal to current date")
	private Date updatedOn;
	@ManyToOne(optional = false)
	@JoinColumn(name = "purchaseid",nullable = false)
	@JsonIgnore
	private Purchase purchase;
	public Delivery() {
		super();
	}
	public Delivery(String status, Date updatedOn) {
		super();
		this.status = status;
		this.updatedOn = updatedOn;
	}
	public Long getTrackerId() {
		return trackerId;
	}
	public void setTrackerId(Long trackerId) {
		this.trackerId = trackerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	
	
}
