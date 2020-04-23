package org.sayantan.AccountService.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payment",schema = "accountdb")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="paymentid",columnDefinition = "INT")
	private Long paymentId;
	@Column(name="cardtype")
	@Size(min=1,max = 6,message="Card Type should be between 6 characters long")
	private String cardType;
	@Column(name="cardno")
	@Size(min=19,max = 19,message="Card No should be 19 characters long")
	private String cardNo;
	@Column(name="expirymonth")
	@Size(min=2,max = 2,message="Expiry Month should be 2 characters long")
	private String expiryMonth;
	@Column(name="expiryyear")
	@Size(min=4,max = 4,message="Expiry Year should be 4 characters long")
	private String expiryYear;
	@Column(name="cardalias")
	@Size(min=1,max = 15,message="Card Alias should be between 1-15 characters long")
	private String cardAlias;
	@Column(name="isdefault")
	@Size(min=1,max = 1,message="isDefault should be Y/N")
	private String isDefault;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "userid",nullable = false)
	private User user;
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Payment() {
		super();
	}


	public Payment(String cardType, String cardNo, String expiryMonth, String expiryYear, String cardAlias,
			String isDefault) {
		super();
		this.cardType = cardType;
		this.cardNo = cardNo;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cardAlias = cardAlias;
		this.isDefault = isDefault;
	}


	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getCardAlias() {
		return cardAlias;
	}
	public void setCardAlias(String cardAlias) {
		this.cardAlias = cardAlias;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}


	public Long getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}



}
