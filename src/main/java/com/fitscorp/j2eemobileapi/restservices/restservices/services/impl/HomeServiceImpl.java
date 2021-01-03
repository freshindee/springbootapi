package com.fitscorp.j2eemobileapi.restservices.restservices.services.impl;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.CategoryDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.HomeDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.PromotionDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.RecommendedDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Category;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.CategoryRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.FavProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.ProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.SubCategoryRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HomeServiceImpl implements HomeService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
	private ProductRepository productRepository;

    @Autowired
    private FavProductRepository favProductRepository;

    @Override
    public HomeDTO initHome(Integer userId) throws Exception {

        HomeDTO homeDTO = new HomeDTO();
        List<PromotionDTO> promotions = new ArrayList<>();
        List<CategoryDTO> categories = new ArrayList<>();
        List<RecommendedDTO> recommends = new ArrayList<>();
        
        List<SubCategory> subCategories = subCategoryRepository.findAllPromotionSubCategories();
        
        for (SubCategory subCategory : subCategories) {
			List<Product> products = productRepository.findProductsBySubCategoryId(subCategory.getId());
			
        	subCategory.setImages(findAllImages(subCategory.getId()));
            for (Product product : products) {

                PromotionDTO p = new PromotionDTO();
                p.setCategoryId(subCategory.getCategoryId());
                p.setDescription(product.getDescription());
                p.setDiscountedPrice(product.getDiscountedPrice());
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setProductId(product.getId());
                p.setStoreId(subCategory.getStoreId());
                p.setSubCategoryId(subCategory.getId());
                p.setSubcategoryName(subCategory.getName());
                p.setUnit(product.getUnit());
                p.setPromoStartDate(subCategory.getPromotionStartDate());
                p.setPromoEndDate(subCategory.getPromotionEndDate());
                p.setPromoDescription(subCategory.getPromotionDescription());

                Boolean isFavorite = favProductRepository
                        .findIfProductIsFavoriteByProductId(product.getId()) != null;

                p.setIsFavorite(isFavorite);

            	p.setImages(findAllImages(product.getId()));
                promotions.add(p);

            }

        }

        List<Category> categoryList = categoryRepository.findAllCategoriesWithoutPromotion();
        
        for (Category category : categoryList) {
            CategoryDTO c = new CategoryDTO();
            c.setCategoryId(category.getCategoryId());
            c.setStoreId(category.getStoreId());
            c.setCategoryName(category.getCategoryName());
        	c.setImages(findAllImages(category.getCategoryId()));
            categories.add(c);
        }
        
        List<Product> latestProds = productRepository.findTop10ByOrderByIdDesc();
        for (Product product : latestProds) {
        	SubCategory subCategory = subCategoryRepository.findById(product.getSubCategoryId()).get();
        	Category category = categoryRepository.getOne(subCategory.getCategoryId());

            RecommendedDTO p = new RecommendedDTO();
            p.setCategoryId(subCategory.getCategoryId());
            p.setCategoryName(category.getCategoryName());
            p.setDescription(product.getDescription());
            p.setDiscountedPrice(product.getDiscountedPrice());
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setProductId(product.getId());
            p.setStoreId(subCategory.getStoreId());
            p.setSubCategoryId(subCategory.getId());
            p.setSubcategoryName(subCategory.getName());
            p.setUnit(product.getUnit());
            p.setPromoStartDate(subCategory.getPromotionStartDate());
            p.setPromoEndDate(subCategory.getPromotionEndDate());
            p.setPromoDescription(subCategory.getPromotionDescription());
        	p.setImages(findAllImages(product.getId()));

            Boolean isFavorite = favProductRepository
                    .findIfProductIsFavoriteByProductId(product.getId()) != null;

            p.setIsFavorite(isFavorite);
        	recommends.add(p);

        }
        
        homeDTO.setPromotions(promotions);
        homeDTO.setCategories(categories);
        homeDTO.setRecommendedList(recommends);

        return homeDTO;
    }

	public List<String> findAllImages(Long catId) {
		return subCategoryRepository.findAllImages(catId);
	}
	
}
