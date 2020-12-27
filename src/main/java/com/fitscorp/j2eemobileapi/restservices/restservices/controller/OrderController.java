package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Order1;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.ConfirmOrderRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.OrderRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.OrderResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.OrderService;


@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public List<Order1> getOrders() throws NotFoundException {
		List<Order1> result = orderService.findAllOrders();
		if (result != null) {
			return result;
		}
		throw new NotFoundException("There are no orders");
	}
	
	@PostMapping("/orders/{userId}")
	public OrderResponse placeAnOrder(@PathVariable Integer userId, @Valid @RequestBody OrderRequest request) throws Exception {
		request.setUserId(userId.longValue());
		Long result = orderService.saveOrder(userId, request);
		if (result != null) {
			return new OrderResponse(result);
		}
		throw new Exception("Failed to place the order");
	}
	
	@PutMapping("/orders/{userId}/acknowledgement")
	public ResponseEntity<?> confirmOrder(@PathVariable Integer userId, @RequestBody ConfirmOrderRequest request) throws Exception {
		Integer result = orderService.saveConfirmOrder(userId, request);
		if (result != null) {
			return ResponseEntity.ok(result);
		}
		throw new NotFoundException("Order confirmation failed");
	}
}
