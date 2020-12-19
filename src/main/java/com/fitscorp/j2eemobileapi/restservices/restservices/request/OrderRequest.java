package com.fitscorp.j2eemobileapi.restservices.restservices.request;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequest {

	private List<OrderDetail> orderDetail;
	private int paymentMethod;
	private int collectType;
	private String address;
	private BigDecimal subTotal;
	private BigDecimal deliveryFee;
	private BigDecimal total;
	
	public OrderRequest() {
	}

	public OrderRequest(List<OrderDetail> orderDetail, int paymentMethod, int collectType, String address, BigDecimal subTotal,
			BigDecimal deliveryFee, BigDecimal total) {
		this.orderDetail = orderDetail;
		this.paymentMethod = paymentMethod;
		this.collectType = collectType;
		this.address = address;
		this.subTotal = subTotal;
		this.deliveryFee = deliveryFee;
		this.total = total;
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
}