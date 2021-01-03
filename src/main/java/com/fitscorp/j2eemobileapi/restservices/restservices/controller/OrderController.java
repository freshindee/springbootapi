package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.OrderDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.ConfirmOrderRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.OrderRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.OrderHistoryResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.OrderResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/orders/{userId}", params = {"pageNo", "pageSize"})
	public OrderHistoryResponse getOrders(@PathVariable Long userId, @RequestParam Integer pageNo, @RequestParam Integer pageSize) throws Exception {
		Pageable pageableRequest = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "orders_date");
		List<OrderDTO> result = orderService.findAllOrdersByUserId(userId, pageableRequest);
		if (result != null) {
			return new OrderHistoryResponse(result);
		}
		throw new NotFoundException("There are no orders");
	}
	
	@PostMapping("/orders/{userId}")
	public ResponseEntity<OrderResponse> placeAnOrder(@PathVariable Integer userId, @Valid @RequestBody OrderRequest request) throws Exception {
		request.setUserId(userId.longValue());
		Long result = orderService.saveOrder(userId, request);
		if (result != null) {
			return new ResponseEntity<>(new OrderResponse(result), HttpStatus.CREATED);
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
