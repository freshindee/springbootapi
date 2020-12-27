package com.fitscorp.j2eemobileapi.restservices.restservices.request;

public class ConfirmOrderRequest {
	private Long orderId;
	private String paymentReference;
	private Integer status;
	
	public ConfirmOrderRequest() {}

	public ConfirmOrderRequest(Long orderId, String paymentReference, Integer status) {
		super();
		this.orderId = orderId;
		this.paymentReference = paymentReference;
		this.status = status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
