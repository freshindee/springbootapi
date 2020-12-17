package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Order1;



	
//	@Repository
//	public interface CategoryRepository  extends JpaRepository<Category, Long>{
//
//		//Product fi(String username);
//	}



	@Repository
	public interface OrderRepository extends JpaRepository<Order1, Long>{
	    
//	    @Query("SELECT c FROM Order c WHERE c.categoryName != 'PROMO'")
//	    public List<Order> findAllOrders();
	    
	}
