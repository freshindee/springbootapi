package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.OrderDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.ProductDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Order1;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.OrderProduct;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.OrderProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.OrderRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.ProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.ConfirmOrderRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.OrderDetailRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.OrderRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.OrderDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    public Iterable<Order1> findAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order1> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<OrderDTO> findAllOrdersByUserId(Long userId, Pageable pageable) throws Exception {
        Page<Order1> orders = orderRepository.findAllOrdersByUserId(userId, pageable);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order1 order : orders) {
        	List<OrderDetailResponse> orderDetailResponse = new ArrayList<>();
        	List<OrderProduct> orderProducts = orderProductRepository.findAllOrderProductsByOrderId(order.getId());
        	for (OrderProduct orderProduct : orderProducts) {
				if (orderProduct.getProductId() != null) {
                    Product productOrdered = productRepository.getOne(orderProduct.getProductId());
                    ProductDTO productDTO = new ProductDTO(
                            0L,
                            0L,
                            productOrdered.getId(),
                            productOrdered.getSubCategoryId(),
                            "",
                            "",
                            productOrdered.getName(),
                            productOrdered.getDescription(),
                            productOrdered.getPrice(),
                            productOrdered.getDiscountedPrice(),
                            productOrdered.getUnit(),
                            false,
                            findAllImages(productOrdered.getId())
                    );
                    orderDetailResponse.add(new OrderDetailResponse(productDTO, orderProduct.getQuantity()));
                }
			}

            orderDTOs.add(
				new OrderDTO(
					order.getId(),
					order.getCollectType(),
					order.getStatusId(),
					order.getOrderDate(),
					order.getDeliveryAddress(),
					order.getSubTotal(),
					order.getDeliveryFee(),
					order.getTotal(),
                    orderDetailResponse)
            );
        }
//        Collections.sort(orderDTOs, Collections.reverseOrder()); //TODO: SQL Query (not working strangely)
        return orderDTOs;
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
        order.setStatusId(1);
        order.setEnabled(false);

        List<OrderProduct> orderProducts = new ArrayList<>();

        Order1 resultingOrder = orderRepository.save(order);
        System.out.println("Order id" + resultingOrder.getId());

        for (OrderDetailRequest detail : request.getOrderDetails()) {
            Optional<Product> product = productRepository.findById(detail.getProductId());

            if (product.isPresent()) {
                OrderProduct orderProduct = new OrderProduct();

                orderProduct.setCreatedBy(userId.toString());
                orderProduct.setCreatedDate(now);
                orderProduct.setEnabled(false);
                orderProduct.setQuantity(detail.getQuantity());
                orderProduct.setOrderId(resultingOrder.getId());
                orderProduct.setProductId(product.get().getId());

                orderProducts.add(orderProduct);
            }

        }
//        order.setOrderProductList(orderProducts);
        List<OrderProduct> orderProductResult = orderProductRepository.saveAll(orderProducts);
        System.out.printf("Placed %d products to be ordered", orderProductResult.size());
        return resultingOrder.getId();
    }

    public Integer saveConfirmOrder(Integer userId, ConfirmOrderRequest request) throws NotFoundException {
        Optional<Order1> existingOrder = orderRepository.findByUserNOrderId(userId, request.getOrderId());
        if (existingOrder.isPresent()) {
            existingOrder.get().setPaymentReference(request.getPaymentReference());
            existingOrder.get().setStatusId(request.getStatus());
            existingOrder.get().setEnabled(true);
            return orderRepository.save(existingOrder.get()).getStatusId();
        }
        throw new NotFoundException("Order confirmation failed!");
    }

    public List<String> findAllImages(Long prodId) {
        return productRepository.findAllImages(prodId);
    }
}