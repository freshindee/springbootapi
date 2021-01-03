package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query(value="SELECT o FROM OrderProduct o WHERE o.orderId = :orderId ORDER BY o.createdDate DESC")
    List<OrderProduct> findAllOrderProductsByOrderId(Long orderId);

}
