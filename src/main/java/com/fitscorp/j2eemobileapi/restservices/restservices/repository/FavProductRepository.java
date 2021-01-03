package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.FavProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavProductRepository extends JpaRepository<FavProduct, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM fav_product WHERE user_id = :userId ORDER BY created_date DESC")
    List<FavProduct> findFavoriteProductsByUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM fav_product WHERE user_id = :userId AND product_id = :productId")
    List<FavProduct> findFavoriteProductsProductId(Long productId, Long userId);

    @Query(nativeQuery = true, value = "SELECT id FROM fav_product WHERE product_id = :prodId LIMIT 1")
    Long findIfProductIsFavoriteByProductId(Long prodId); // TODO: user id need to match to get favorite

}
