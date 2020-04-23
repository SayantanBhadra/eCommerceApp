package org.sayantan.DeliveryService.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="purchase_info",schema="deliverydb")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchaseid",columnDefinition = "INT")
	private Long purchaseId;
	@Column(name="userid",columnDefinition = "INT")
	@NotNull(message = "User Id should not be null")
	private Long userId;
	@Column(name="sellerid",columnDefinition = "INT")
	@NotNull(message = "Seller Id should not be null")
	private Long sellerId;
	@Column(name="productid",columnDefinition = "INT")
	@NotNull(message = "Product Id should not be null")
	private Long productId;
	@Column(name="paymentmode")
	@Size(min=5,max=15,message = "Payment Mode should be 5-15 characters long")
	private String paymentMode;
	@Column(name="purchasedate",columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent(message="Purchase date should less than or equal to current date")
	private Date purchaseDate;
	@Column(name="destination",columnDefinition = "INT")
	@NotNull(message = "Address Id should not be null")
	private Long addressId;
	@OneToMany(mappedBy = "purchase",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Delivery> deliveries;
	public Purchase() {
		super();
	}
	public Purchase(Long userId, Long sellerId, Long productId, String paymentMode, Date purchaseDate, Long addressId) {
		super();
		this.userId = userId;
		this.sellerId = sellerId;
		this.productId = productId;
		this.paymentMode = paymentMode;
		this.purchaseDate = purchaseDate;
		this.addressId = addressId;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}
	
	
	
}
