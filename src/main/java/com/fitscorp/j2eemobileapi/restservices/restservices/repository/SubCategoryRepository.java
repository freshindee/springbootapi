package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{

	SubCategory findByCategoryId(Long categoryId);
	
    @Query("SELECT s FROM SubCategory s WHERE s.category.name='PROMO'")
    public List<SubCategory> findAllPromotionSubCategories() throws Exception;
    
}
