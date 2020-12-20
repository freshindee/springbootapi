package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
