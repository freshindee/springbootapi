package com.fitscorp.j2eemobileapi.restservices.restservices.response;

public class OrderResponse {
	
	public Long orderId;
	
	public OrderResponse() {}

	public OrderResponse(Long orderId) {
		super();
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
