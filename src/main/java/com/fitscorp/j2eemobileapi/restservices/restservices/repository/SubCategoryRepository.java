package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{

    @Query("SELECT s FROM SubCategory s WHERE s.categoryId = :categoryId")
	List<SubCategory> findByCategoryId(Long categoryId);
	
    @Query("SELECT s FROM SubCategory s WHERE s.isPromo='1'")
    public List<SubCategory> findAllPromotionSubCategories() throws Exception;

	public Optional<List<SubCategory>> findAllByCategoryId(Long categoryId);

    @Query("SELECT p FROM Product p left join SubCategory s on s.id = p.subCategoryId and s.isPromo=1")
	public List<Product> findAllPromotionProducts();

    @Query(nativeQuery = true, value = "SELECT path FROM file WHERE table_id = :tableId AND table_def = :tableDef")
    public List<String> findAllImages(Long tableId, Long tableDef);
}
