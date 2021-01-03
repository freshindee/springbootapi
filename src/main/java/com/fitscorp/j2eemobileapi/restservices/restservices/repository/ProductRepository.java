package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

	@Query(value="SELECT * FROM product WHERE sub_category_id = :subCategoryId ORDER BY created_date DESC", nativeQuery=true)
	List<Product> findProductsBySubCategoryId(Long subCategoryId);

	@Query(value="SELECT * FROM product ORDER BY created_date DESC LIMIT 10", nativeQuery=true)
	List<Product> findTop10ByOrderByIdDesc();

	@Query(value="SELECT * FROM product WHERE sub_category_id = 0 ORDER BY created_date DESC", nativeQuery=true)
	List<Product> findAllPromotions();

	@Query(nativeQuery = true, value = "SELECT name FROM file WHERE table_id = :productId")
	List<String> findAllImages(Long productId);

	@Query(value="SELECT p FROM Product p WHERE p.name LIKE %?1%",
			countQuery = "SELECT count(p) FROM Product p WHERE p.name LIKE %?1%")
	Page<Product> findProductsByName(String criteria, Pageable pageable);

}
