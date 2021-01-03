package com.fitscorp.j2eemobileapi.restservices.restservices.request;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

public class OrderRequest {

	@Valid
	private List<OrderDetailRequest> orderDetails;
	@Valid
	private int paymentMethod;
	@Valid
	private int collectType;
	@Valid
	private String address;
	@Valid
	private BigDecimal subTotal;
	@Valid
	private BigDecimal deliveryFee;
	@Valid
	private BigDecimal total;
	private Long userId;
	
	public OrderRequest() {
	}

	public OrderRequest(List<OrderDetailRequest> orderDetails, int paymentMethod, int collectType, String address, BigDecimal subTotal,
						BigDecimal deliveryFee, BigDecimal total, Long userId) {
		this.orderDetails = orderDetails;
		this.paymentMethod = paymentMethod;
		this.collectType = collectType;
		this.address = address;
		this.subTotal = subTotal;
		this.deliveryFee = deliveryFee;
		this.total = total;
		this.userId = userId;
	}

	public List<OrderDetailRequest> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailRequest> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getCollectType() {
		return collectType;
	}

	public void setCollectType(int collectType) {
		this.collectType = collectType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
}