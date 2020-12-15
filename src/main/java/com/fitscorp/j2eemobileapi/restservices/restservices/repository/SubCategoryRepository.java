package com.fitscorp.j2eemobileapi.restservices.restservices.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{
	SubCategory findByCategoryId(Long categoryId);
}
