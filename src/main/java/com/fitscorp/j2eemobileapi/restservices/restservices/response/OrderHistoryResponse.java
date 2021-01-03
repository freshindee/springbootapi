package com.fitscorp.j2eemobileapi.restservices.restservices.response;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.OrderDTO;

import java.util.List;

public class OrderHistoryResponse {
    private List<OrderDTO> orders;

    public OrderHistoryResponse(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
