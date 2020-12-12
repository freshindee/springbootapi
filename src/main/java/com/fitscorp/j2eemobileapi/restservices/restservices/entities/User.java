package com.fitscorp.j2eemobileapi.restservices.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 50, nullable = false, unique = true)
	private Long userId;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "phone", length = 50, nullable = false, unique = true)
	private String phoneNo;

	@Column(name = "email_address", length = 50, nullable = false, unique = true)
	private String email;

	@Column(name = "delivery_address", length = 50, nullable = true)
	private String address;

	@Column(name = "status_id", length = 50, nullable = false)
	private int passwordStatus;

	@Column(name = "enabled", length = 50, nullable = false)
	private int status;
	
	public User() {
	}

	public User(Long userId, String name, String phoneNo, String email, String address, int passwordStatus,
			int status) {
		super();
		this.userId = userId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
		this.passwordStatus = passwordStatus;
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPasswordStatus() {
		return passwordStatus;
	}

	public void setPasswordStatus(int passwordStatus) {
		this.passwordStatus = passwordStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", phoneNo=" + phoneNo + ", email=" + email + ", address="
				+ address + ", passwordStatus=" + passwordStatus + ", status=" + status + "]";
	}

}
