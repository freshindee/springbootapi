package com.fitscorp.j2eemobileapi.restservices.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Entity 
// and
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", length = 50, nullable = false, unique = true)
	private String username;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstname;

	@Column(name = "last_name", length = 50, nullable = false)
	private String lastname;

	@Column(name = "phone", length = 50, nullable = false)
	private String phone;

	@Column(name = "email_address", length = 50, nullable = false)
	private String email;

	@Column(name = "delivery_address", length = 50, nullable = false)
	private String delivery_address;

	@Column(name = "role", length = 50, nullable = false)
	private String role;

	@Column(name = "status_id", length = 50, nullable = false, unique = true)
	private String status_id;

	@Column(name = "ssn", length = 50, nullable = false, unique = true)
	private String ssn;

	@Column(name = "created_by", length = 50, nullable = false, unique = true)
	private String createdBy;

	@Column(name = "created_date", length = 50, nullable = false, unique = true)
	private String createdDate;

	
	public User() {
	}

	public User(Long id, String username, String name, String firstname, String lastname, String phone, String email,
			String delivery_address, String role, String status_id, String ssn, String createdBy, String createdDate) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.delivery_address = delivery_address;
		this.role = role;
		this.status_id = status_id;
		this.ssn = ssn;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", phone=" + phone + ", email=" + email + ", delivery_address="
				+ delivery_address + ", role=" + role + ", status_id=" + status_id + ", ssn=" + ssn + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + "]";
	}

}
