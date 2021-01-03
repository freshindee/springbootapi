package com.fitscorp.j2eemobileapi.restservices.restservices.request;

public class ForgotPasswordRequest {
	private String email;
	private String phone;
	
	public ForgotPasswordRequest() {
	}
	
	public ForgotPasswordRequest(String email, String phone) {
		super();
		this.email = email;
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
