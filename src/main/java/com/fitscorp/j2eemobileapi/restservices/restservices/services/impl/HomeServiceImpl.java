package com.fitscorp.j2eemobileapi.restservices.restservices.services.impl;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.CategoryDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.HomeDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.PromotionDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Category;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.CategoryRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.SubCategoryRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.HomeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeServiceImpl implements HomeService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public HomeDTO initHome(Integer userId) throws Exception {

        HomeDTO homeDTO = new HomeDTO();
        List<PromotionDTO> promotions = new ArrayList<>();
        List<CategoryDTO> categories = new ArrayList<>();
        List<SubCategory> subCategories = subCategoryRepository.findAllPromotionSubCategories();

        List<Product> products = subCategoryRepository.findAllPromotionProducts();
        System.out.println(products.size());
        
        for (SubCategory subCategory : subCategories) {
        	subCategory.setImages(findAllImages(subCategory.getId()));
            for (Product product : products) {

                PromotionDTO p = new PromotionDTO();
                p.setCategoryId(subCategory.getCategoryId());
                p.setDescription(product.getDescription());
                p.setDiscountedPrice(product.getDiscountedPrice().doubleValue());
                p.setName(product.getName());
                p.setPrice(product.getPrice().doubleValue());
                p.setProductId(product.getId());
                p.setStoreId(subCategory.getStoreId());
                p.setSubCategoryId(subCategory.getId());
                p.setSubcategoryName(subCategory.getName());
                p.setUnit(product.getUnit());
                p.setPromoStartDate(subCategory.getPromotionStartDate());
                p.setPromoEndDate(subCategory.getPromotionEndDate());
                p.setPromoDescription(subCategory.getPromotionDescription());
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
        
        
        
        homeDTO.setPromotions(promotions);
        homeDTO.setCategories(categories);

        return homeDTO;
    }

	public List<String> findAllImages(Long catId) {
		return subCategoryRepository.findAllImages(catId);
	}
	
}
