package com.fitscorp.j2eemobileapi.restservices.restservices.entities;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//Entity 
// and
@Entity  
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", length = 255, nullable = true)
	private String name;

	@Column(name = "username", length = 255, nullable = true)
	private String username;

	@Column(name = "password", length = 255, nullable = true)
	private String password;

	@Column(name = "phone", length = 12, nullable = true)
	private String phone;

	@Column(name = "delivery_address", length = 255, nullable = true)
	private String delivery_address;

	@Column(name = "email_address", length = 255, nullable = true)
	private String email_address;

	@Column(name = "is_email_verified", nullable = true)
	private Boolean is_email_verified;
	
	@Column(name = "status_id", length = 3, nullable = true)
	private int status_id;
	
	@Column(name = "created_by", length = 255, nullable = true)
	private String created_by;
	
	@Column(name = "password_expiry", length = 255, nullable = true)
	private Date password_expiry;
	
	@Column(name = "created_date", length = 255, nullable = true)
	private String created_date;
	
	@Column(name = "modified_by", length = 255, nullable = true)
	private String modified_by;
	
	@Column(name = "enabled",  nullable = true)
	private Boolean enabled;
	
	@Column(name = "modified_date", length = 255, nullable = true)
	private Date modified_date;

	 
	// No Argument Constructor
	public User() {
	}

	// Fields Constructor
	

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public User(Long id, String name, String username, String password, String phone, String delivery_address,
			String email_address, Boolean is_email_verified, int status_id, String created_by, Date password_expiry,
			String created_date, String modified_by, Boolean enabled, Date modified_date) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.delivery_address = delivery_address;
		this.email_address = email_address;
		this.is_email_verified = is_email_verified;
		this.status_id = status_id;
		this.created_by = created_by;
		this.password_expiry = password_expiry;
		this.created_date = created_date;
		this.modified_by = modified_by;
		this.enabled = enabled;
		this.modified_date = modified_date;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public Boolean getIs_email_verified() {
		return is_email_verified;
	}

	public void setIs_email_verified(Boolean is_email_verified) {
		this.is_email_verified = is_email_verified;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getPassword_expiry() {
		return password_expiry;
	}

	public void setPassword_expiry(Date password_expiry) {
		this.password_expiry = password_expiry;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", phone="
				+ phone + ", delivery_address=" + delivery_address + ", email_address=" + email_address
				+ ", is_email_verified=" + is_email_verified + ", status_id=" + status_id + ", created_by=" + created_by
				+ ", password_expiry=" + password_expiry + ", created_date=" + created_date + ", modified_by="
				+ modified_by + ", enabled=" + enabled + ", modified_date=" + modified_date + "]";
	}

	// To String
	
	
}
