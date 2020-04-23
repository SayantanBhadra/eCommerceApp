package org.sayantan.AccountService.entities;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;

import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
@Table(name = "user",schema = "accountdb")
@JsonFilter("DefaultUserFilter")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid",columnDefinition = "INT")
	private Long userId;
	@Column(name="firstname")
	@Size(min=3,max = 45,message="FirstName should be between 3-45 characters long")
	private String firstName;
	@Size(max = 45,message="MiddleName should be maximum 45 characters long")
	@Column(name="middlename")
	private String middleName;
	@Size(min=3,max = 45,message="LastName should be between 3-45 characters long")
	@Column(name="lastname")
	private String lastName;
	@Column(name="username")
	@Size(min=5,max = 25,message="UserName should be between 5-25 characters long")
	private String userName;
	@Column(name="mobileno")
	@Size(min=10,max = 10,message="Mobile Number should be 10 characters long")
	private String mobileNo;
	@Size(min=5,max = 50,message="Email Id should be between 5-50 characters long")
	@Email(message="Not a Valid Email Address")
	@Column(name="emailid")
	private String emailId;
	@Column(name="dob",columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@Past(message = "Date of Birth should be older than the current date")
	private Date dob;
	@Column(name="activestatus")
	@Size(min=1,max=1,message="Active Status should be Y/N")
	private String activeStatus;
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<Address> addresses;
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<Payment> payments;
	
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	public User() {
		super();
	}
	public User(String firstName, String middleName, String lastName, String userName, String mobileNo, String emailId,
			Date dob,String activeStatus) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.userName = userName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.dob = dob;
		this.activeStatus=activeStatus;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	
}
