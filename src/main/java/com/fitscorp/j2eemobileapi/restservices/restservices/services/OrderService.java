package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Order1;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.OrderRepository;



@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order1> findAllOrders() {
		return orderRepository.findAll();
	}

	
}