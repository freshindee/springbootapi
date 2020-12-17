package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Order1;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
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
}
