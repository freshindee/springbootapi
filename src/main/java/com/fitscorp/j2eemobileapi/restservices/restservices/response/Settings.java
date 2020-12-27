package com.fitscorp.j2eemobileapi.restservices.restservices.response;

import java.math.BigDecimal;

public class Settings {
	private BigDecimal deliveryFee;

	public Settings() {
	}

	public Settings(BigDecimal deliveryFee) {
		super();
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
}
