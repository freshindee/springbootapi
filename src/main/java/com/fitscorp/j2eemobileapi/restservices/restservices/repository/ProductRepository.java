package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;



@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>{


	@Query(value="SELECT * FROM product WHERE sub_category_id = :subCategoryId", nativeQuery=true)
	List<Product> findProductsBySubCategoryId(Long subCategoryId);
	
	@Query(value="SELECT * FROM product ORDER BY created_date DESC LIMIT 10", nativeQuery=true)
	public List<Product> findTop10ByOrderByIdDesc();

	@Query(value="SELECT * FROM product WHERE sub_category_id = 0", nativeQuery=true)
	public List<Product> findAllPromotions();
}
