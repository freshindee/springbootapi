package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.PromotionDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Category;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.ProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.SubCategoryRepository;

@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public List<SubCategory> getAllSubCategories() {

		return subCategoryRepository.findAll();

	}

	public List<PromotionDTO> getAllPromotions() throws Exception {
		List<SubCategory> subCategories =  subCategoryRepository.findAllPromotionSubCategories();
		List<PromotionDTO> promos = new ArrayList<>();
		if (subCategories == null) {
			throw new NotFoundException("Sub category not found");
		}
		
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

	public SubCategory createSubCategory(SubCategory subCategory) throws UserExistsException{
		//if subCategory exist using id
		Optional<SubCategory> existingSubCategory = subCategoryRepository.findById(subCategory.getId());
	
		//if not exists throw UserExistsException
		if(!existingSubCategory.isPresent()) {
			throw new UserExistsException("Sub Category already exists");
		}
		
	
		return subCategoryRepository.save(subCategory);
	}

	public Optional<SubCategory> getSubCategoryId(Long id) throws NotFoundException {
		Optional<SubCategory> subCategory = subCategoryRepository.findById(id);

		if (!subCategory.isPresent()) {
			throw new NotFoundException("Sub category Not found");
		}

		return subCategory;
	}

	public SubCategory updateSubCategoryById(Long id, SubCategory subCategory) throws NotFoundException {
		Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);

		if (!optionalSubCategory.isPresent()) {
			throw new NotFoundException("Sub Category not found");
		}

		
		return subCategoryRepository.save(subCategory);

	}

	// deleteUserById
	public void deleteSubCategoryById(Long id) {
		Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
		if (!optionalSubCategory.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found in user Repository, provide the correct user id");
		}
	
		subCategoryRepository.deleteById(id);
	}

	public Optional<SubCategory> getSubCategoryById(Long id) throws NotFoundException {
		Optional<SubCategory> subCategory =  subCategoryRepository.findById(id);

		if (!subCategory.isPresent()) {
			throw new NotFoundException("Sub category not found");
		}
		
		List<String> images = findAllImages(subCategory.get().getId());
		subCategory.get().setImages(images);
		return subCategory;
	}

	public Optional<SubCategory> getSubCategoryByCategoryId(Long categoryId) throws NotFoundException {
		Optional<SubCategory> subCategory =  subCategoryRepository.findById(categoryId);

		if (!subCategory.isPresent()) {
			throw new NotFoundException("Sub category not found");
		}
		
		List<String> images = findAllImages(subCategory.get().getId());
		subCategory.get().setImages(images);
		return subCategory;
	}

	public Optional<List<SubCategory>> getSubCategoriesByCategoryId(Long categoryId) {
		Optional<List<SubCategory>> subCategories = subCategoryRepository.findAllByCategoryId(categoryId);
		for (SubCategory cat : subCategories.get()) {
			List<String> images = findAllImages(cat.getCategoryId());
			cat.setImages(images);
		}
		return subCategories;
	}
	
	public List<String> findAllImages(Long catId) {
		return subCategoryRepository.findAllImages(catId);
	}
	
}
