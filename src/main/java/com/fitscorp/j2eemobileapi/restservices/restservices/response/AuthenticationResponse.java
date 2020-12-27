package com.fitscorp.j2eemobileapi.restservices.restservices.response;

public class AuthenticationResponse {

	private Long userId;
	private String name;
	private String password;
	private String phoneNo;
	private String deliveryAddress;
	private String email;
	private int passwordStatus;
	private int status;
	private Settings settings;
	private String accessToken;
	private String refreshToken;
	
	public AuthenticationResponse() {
	}
	
	public AuthenticationResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public AuthenticationResponse(Long userId, String name, String password, String phoneNo, String deliveryAddress,
			String email, int passwordStatus, int status, Settings settings, String accessToken, String refreshToken) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.phoneNo = phoneNo;
		this.deliveryAddress = deliveryAddress;
		this.email = email;
		this.passwordStatus = passwordStatus;
		this.status = status;
		this.settings = settings;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	
}
