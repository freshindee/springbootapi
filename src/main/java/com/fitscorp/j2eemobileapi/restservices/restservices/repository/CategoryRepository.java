package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Category;



	
//	@Repository
//	public interface CategoryRepository  extends JpaRepository<Category, Long>{
//
//		//Product fi(String username);
//	}



	@Repository
	public interface CategoryRepository extends JpaRepository<Category, Long>{
	    
	    @Query("SELECT c FROM Category c WHERE c.name!='PROMO'")
	    public List<Category> findAllCategoriesWithoutPromotion() throws Exception;
	    
	}
