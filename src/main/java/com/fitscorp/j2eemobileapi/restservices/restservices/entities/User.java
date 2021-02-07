package com.fitscorp.j2eemobileapi.restservices.restservices.entities;

import javax.persistence.*;
import java.util.Date;

//Entity 
@Entity
@Table(name = "user")
//@Where(clause = "enabled= '1'")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 50, nullable = false, unique = true)
	private Long userId;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "username", length = 255, nullable = true)
	private String username;

	@Column(name = "password", length = 255, nullable = true)
	private String password;

	@Column(name = "phone", length = 12, nullable = true)
	private String phoneNo;

	@Column(name = "delivery_address", length = 255, nullable = true)
	private String address;

	@Column(name = "email_address", length = 255, nullable = true)
	private String email;

	@Column(name = "is_email_verified", nullable = true)
	private Boolean is_email_verified;
	
	@Column(name = "created_by", length = 255, nullable = true)
	private String created_by;
	
	@Column(name = "password_expiry", length = 255, nullable = true)
	private Date password_expiry;
	
	@Column(name = "created_date", length = 255, nullable = true)
	private Date created_date;
	
	@Column(name = "modified_by", length = 255, nullable = true)
	private String modified_by;
	
	@Column(name = "modified_date", length = 255, nullable = true)
	private Date modified_date;
	
	@Column(name = "status_id", length = 50, nullable = false)
	private int passwordStatus;

	@Column(name = "enabled", length = 50, nullable = false)
	private int status;
	@OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id")
//	@Where(clause = "enabled= '1'")
	private UserToken tokens;

	public User() {
	}

	public User(Long userId, String name, String username, String password, String phoneNo, String address,
			String email, Boolean is_email_verified, String created_by, Date password_expiry,
			Date created_date, String modified_by, int status, Date modified_date) {
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phoneNo = phoneNo;
		this.address = address;
		this.email = email;
		this.is_email_verified = is_email_verified;
		this.created_by = created_by;
		this.password_expiry = password_expiry;
		this.created_date = created_date;
		this.modified_by = modified_by;
		this.status = status;
		this.modified_date = modified_date;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public UserToken getTokens() {
		return tokens;
	}

	public void setTokens(UserToken tokens) {
		this.tokens = tokens;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getIs_email_verified() {
		return is_email_verified;
	}

	public void setIs_email_verified(Boolean is_email_verified) {
		this.is_email_verified = is_email_verified;
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(java.util.Date created_date) {
		this.created_date = created_date;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", phoneNo=" + phoneNo + ", address=" + address + ", email=" + email + ", is_email_verified="
				+ is_email_verified + ", created_by=" + created_by + ", password_expiry="
				+ password_expiry + ", created_date=" + created_date + ", modified_by=" + modified_by
				+ ", modified_date=" + modified_date + ", passwordStatus=" + passwordStatus + ", status=" + status
				+ ", tokens=" + tokens + "]";
	}

}
