package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Order1;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.OrderProduct;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.OrderProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.OrderRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.ProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.ConfirmOrderRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.OrderDetail;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.OrderRequest;
import com.mysql.cj.x.protobuf.MysqlxCrud.FindOrBuilder;



@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Order1> findAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Order1> findById(Long id) {
		return orderRepository.findById(id);
	}

	public Long saveOrder(Integer userId, OrderRequest request) {
		Order1 order = new Order1();
		order.setPaymentMethod(request.getPaymentMethod());
		order.setCollectType(request.getCollectType());
		order.setDeliveryAddress(request.getAddress());
		order.setDeliveryFee(request.getDeliveryFee());
		order.setSubTotal(request.getSubTotal());
		order.setTotal(request.getTotal());
		order.setUserId(request.getUserId());
		
		Date now = new Date();
		
		order.setOrderDate(now);
		order.setCreatedBy(userId.toString());
		order.setCreatedDate(now);
		
		// Since this is placing order not [completing order]
		order.setStatusId(0);
		order.setEnabled(false);
		
		List<OrderProduct> orderProducts = new ArrayList<>();
		
		for (OrderDetail detail : request.getOrderDetails()) {
			Optional<Product> product = productRepository.findById(detail.getProductId());

			if (product.isPresent()) {
				OrderProduct orderProduct = new OrderProduct();
				
				orderProduct.setOrder(order);
				orderProduct.setCreatedBy(userId.toString());
				orderProduct.setCreatedDate(now);
				orderProduct.setEnabled(false);
				orderProduct.setQuantity(detail.getQuantity());
				orderProduct.setProduct(productRepository.getOne(detail.getProductId()));
				orderProduct.setOrderId(order.getId());
				orderProduct.setProduct(product.get());
				
				orderProducts.add(orderProduct);
			}
			
		}
		order.setOrderProductList(orderProducts);
		Order1 result = orderRepository.save(order);
		return result.getId();
	}

	public Integer saveConfirmOrder(Integer userId, ConfirmOrderRequest request) throws NotFoundException {
		Optional<Order1> existingOrder = findById(request.getOrderId());
		if (existingOrder.isPresent()) {
			existingOrder.get().setPaymentReference(request.getPaymentReference());
			existingOrder.get().setStatusId(request.getStatus());
			orderRepository.save(existingOrder.get());
		}
		throw new NotFoundException("Order confirmation failed!");
	}
	
}