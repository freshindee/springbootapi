package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Order1;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.OrderRequest;



	
//	@Repository
//	public interface CategoryRepository  extends JpaRepository<Category, Long>{
//
//		//Product fi(String username);
//	}



	@Repository
	public interface OrderRepository extends JpaRepository<Order1, Long>{
	    
	}
