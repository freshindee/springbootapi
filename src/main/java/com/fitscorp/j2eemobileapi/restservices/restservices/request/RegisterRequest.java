package com.fitscorp.j2eemobileapi.restservices.restservices.request;

import javax.validation.constraints.NotNull;

public class RegisterRequest {

    @NotNull(message = "cannot be empty")
	private String name;
    @NotNull(message = "cannot be empty")
	private String email;
    @NotNull(message = "cannot be empty")
	private String phoneNo;
    @NotNull(message = "cannot be empty")
	private String password;

	public RegisterRequest() {}

	public RegisterRequest(@NotNull String name, @NotNull String email, @NotNull String phoneNo,
			@NotNull String password) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "RegisterRequest [name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", password=" + password
				+ "]";
	}
}
