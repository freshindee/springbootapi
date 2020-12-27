package com.fitscorp.j2eemobileapi.restservices.restservices.request;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

public class OrderRequest {

	@Valid
	private List<OrderDetail> orderDetail;
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

	public OrderRequest(List<OrderDetail> orderDetail, int paymentMethod, int collectType, String address, BigDecimal subTotal,
			BigDecimal deliveryFee, BigDecimal total, Long userId) {
		this.orderDetail = orderDetail;
		this.paymentMethod = paymentMethod;
		this.collectType = collectType;
		this.address = address;
		this.subTotal = subTotal;
		this.deliveryFee = deliveryFee;
		this.total = total;
		this.userId = userId;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetail;
	}

	public void setOrderDetails(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
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