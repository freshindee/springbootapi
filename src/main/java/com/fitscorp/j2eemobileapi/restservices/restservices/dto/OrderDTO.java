package com.fitscorp.j2eemobileapi.restservices.restservices.dto;

import com.fitscorp.j2eemobileapi.restservices.restservices.response.OrderDetailResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDTO implements Comparable<OrderDTO> {
    private Long orderId;
    private Integer orderType;
    private Integer status;
    private Date orderDate;
    private String deliveryAddress;
    private BigDecimal deliveryFee;
    private BigDecimal subTotal;
    private BigDecimal total;
    private List<OrderDetailResponse> orderDetails;

    public OrderDTO() {
    }

    public OrderDTO(Long orderId, Integer orderType, Integer status, Date orderDate, String deliveryAddress, BigDecimal deliveryFee, BigDecimal subTotal, BigDecimal total, List<OrderDetailResponse> orderDetails) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.deliveryFee = deliveryFee;
        this.subTotal = subTotal;
        this.total = total;
        this.orderDetails = orderDetails;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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

    public List<OrderDetailResponse> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailResponse> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public int compareTo(OrderDTO dto) {
        return this.orderDate.compareTo(dto.orderDate);
    }
}
