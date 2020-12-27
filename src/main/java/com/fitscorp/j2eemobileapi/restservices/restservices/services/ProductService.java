package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.ProductDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.ProductWrapperDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.PromotionDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Category;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.FavProduct;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.CategoryRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.ProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.SubCategoryRepository;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public ProductWrapperDTO getProductsBySubCategoryId(Long subCatId) throws NotFoundException {
		List<ProductDTO> productDtos = new ArrayList<>();
		SubCategory subCat;
		Category cat;
		try {
			subCat =  subCategoryRepository.findById(subCatId).get();
			cat =  categoryRepository.findById(subCatId).get();
		} catch (Exception no) {
			throw new NotFoundException("Not found");
		}
		List<Product> products = productRepository.findProductsBySubCategoryId(subCatId);
		
		if (products == null || products.size() == 0) {
			return new ProductWrapperDTO(new ArrayList<>());
		}
		
		for (Product product : products) {
			List<String> images = findAllImages(product.getId());
			product.setImages(images);
			
			ProductDTO prod = new ProductDTO(
				subCat.getCategoryId(), 
				subCat.getStoreId(), 
				product.getId(), 
				product.getSubCategoryId(), 
				cat.getCategoryName(),
				subCat.getName(),
				product.getName(), 
				product.getDescription(),
				product.getPrice(), 
				product.getDiscountedPrice(), 
				product.getUnit(), 
				product.getImages()
			);
			productDtos.add(prod);
		}
		return new ProductWrapperDTO(productDtos);
	}

//	public List<Product> getAllPromotions() {
//		return productRepository.findAllPromotions();
//	}

	public List<PromotionDTO> getAllPromotions() throws Exception {
		List<SubCategory> subCategories =  subCategoryRepository.findAllPromotionSubCategories();
		List<PromotionDTO> promos = new ArrayList<>();
		
		for (SubCategory subCat : subCategories) {
			List<Product> products = productRepository.findProductsBySubCategoryId(subCat.getId());
			
			for (Product product : products) {
				List<String> images = findAllImages(product.getId());
				product.setImages(images);
				
				PromotionDTO promo = new PromotionDTO(
					subCat.getCategoryId(), 
					subCat.getStoreId(), 
					product.getId(), 
					product.getSubCategoryId(), 
					subCat.getName(),
					subCat.getPromotionStartDate(), 
					subCat.getPromotionEndDate(), 
					subCat.getPromotionDescription(), 
					product.getName(), 
					product.getDescription(),
					product.getPrice(), 
					product.getDiscountedPrice(), 
					product.getUnit(), 
					product.getImages()
				);
				promos.add(promo);
			}
			
			List<String> images = findAllImages(subCat.getId());
			subCat.setImages(images);
		}
		return promos;
	}
	
	public ProductWrapperDTO getFavoriteProducts(Long userId) throws NotFoundException {
		List<FavProduct> favProducts = productRepository.findFavoriteProductsByUserId(userId);
		List<ProductDTO> productDtos = new ArrayList<>();
		SubCategory subCat;
		Category cat;
		
		if (favProducts == null || favProducts.size() == 0) {
			return new ProductWrapperDTO(new ArrayList<>());
		}
		
		for (FavProduct favProd : favProducts) {
			Optional<Product> product = productRepository.findById(favProd.getProductId().longValueExact());
			
			if (!product.isPresent()) {
				break;
			}
			
			try {
				subCat =  subCategoryRepository.findById(product.get().getSubCategoryId()).get();
				cat =  categoryRepository.findById(product.get().getSubCategoryId()).get();
			} catch (Exception no) {
				throw new NotFoundException("Not found");
			}
			
			List<String> images = findAllImages(product.get().getId());
			product.get().setImages(images);
			
			ProductDTO prod = new ProductDTO(
				subCat.getCategoryId(), 
				subCat.getStoreId(), 
				product.get().getId(), 
				product.get().getSubCategoryId(), 
				cat.getCategoryName(),
				subCat.getName(),
				product.get().getName(), 
				product.get().getDescription(),
				product.get().getPrice(), 
				product.get().getDiscountedPrice(), 
				product.get().getUnit(), 
				product.get().getImages()
			);
			productDtos.add(prod);
		}
		return new ProductWrapperDTO(productDtos);
	}

	public List<String> findAllImages(Long catId) {
		return subCategoryRepository.findAllImages(catId);
	}
}
