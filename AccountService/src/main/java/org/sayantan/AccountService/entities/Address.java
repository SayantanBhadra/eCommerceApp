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
@Table(name = "address",schema = "accountdb")
//@JsonFilter("DefaultAddressFilter")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="addressid",columnDefinition = "INT")
	private Long addressId;
	@Column(name="buildingno")
	@Size(min=1,max = 45,message="Building No should be between 1-45 characters long")
	private String buildingNo;
	@Column(name="block")
	@Size(min=5,max = 200,message="Building No should be between 5-200 characters long")
	private String block;
	@Column(name="city")
	@Size(min=1,max = 45,message="City Name should be between 1-45 characters long")
	private String city;
	@Column(name="pincode")
	@Size(min=6,max = 6,message="Pin Name should be exactly 6 characters long")
	private String pin;
	@Column(name="addresstype")
	@Size(min=1,max = 20,message="Address Type should be between 1-20 characters long")
	private String addressType;
	@Column(name="alias")
	@Size(min=1,max = 20,message="Alias should be between 1-20 characters long")
	private String alias;
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
	public Address() {
		super();
	}
	public Address(String buildingNo, String block, String city, String pin, String addressType, String alias,
			String isDefault) {
		super();
		this.buildingNo = buildingNo;
		this.block = block;
		this.city = city;
		this.pin = pin;
		this.addressType = addressType;
		this.alias = alias;
		this.isDefault = isDefault;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", buildingNo=" + buildingNo + ", block=" + block + ", city=" + city
				+ ", pin=" + pin + ", addressType=" + addressType + ", alias=" + alias + ", isDefault=" + isDefault
				+ "]";
	}
	
}
