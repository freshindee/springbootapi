package com.fitscorp.j2eemobileapi.restservices.restservices.request;

import javax.validation.constraints.NotNull;

public class AuthenticationRequest {

    @NotNull(message = "cannot be empty")
	private String username;
    @NotNull(message = "cannot be empty")
	private String password;
    @NotNull(message = "cannot be empty")
	private String deviceIdentifier;
    @NotNull(message = "cannot be empty")
	private String deviceDetails;
	private String fcmToken;

	public AuthenticationRequest() {}

	public AuthenticationRequest(String username, String password, String deviceIdentifier, String deviceDetails,
			String fcmToken) {
		super();
		this.username = username;
		this.password = password;
		this.deviceIdentifier = deviceIdentifier;
		this.deviceDetails = deviceDetails;
		this.fcmToken = fcmToken;
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

	public String getDeviceIdentifier() {
		return deviceIdentifier;
	}

	public void setDeviceIdentifier(String deviceIdentifier) {
		this.deviceIdentifier = deviceIdentifier;
	}

	public String getDeviceDetails() {
		return deviceDetails;
	}

	public void setDeviceDetails(String deviceDetails) {
		this.deviceDetails = deviceDetails;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", password=" + password + ", deviceIdentifier="
				+ deviceIdentifier + ", deviceDetails=" + deviceDetails + ", fcmToken=" + fcmToken + "]";
	}
}
