package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Order1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//	@Repository
//	public interface CategoryRepository  extends JpaRepository<Category, Long>{
//
//		//Product fi(String username);
//	}



	@Repository
	public interface OrderRepository extends JpaRepository<Order1, Long> {

		@Query(value="SELECT * FROM orders WHERE user_id = ?1 ORDER BY orders_date DESC",
				countQuery = "SELECT count(*) FROM orders WHERE user_id = ?1", nativeQuery=true)
		Page<Order1> findAllOrdersByUserId(Long userId, Pageable pageable);

		@Query(value="SELECT * FROM orders WHERE user_id = :userId AND id = :orderId", nativeQuery=true)
		Optional<Order1> findByUserNOrderId(Integer userId, Long orderId);

	}
