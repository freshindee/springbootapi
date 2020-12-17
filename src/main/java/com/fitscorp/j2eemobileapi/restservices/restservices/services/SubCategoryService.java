package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Category;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserExistsException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.SubCategoryRepository;

@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	public List<SubCategory> getAllUsers() {

		return subCategoryRepository.findAll();

	}

	public SubCategory createUser(SubCategory subCategory) throws UserExistsException{
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

	public SubCategory updateUserById(Long id, SubCategory subCategory) throws NotFoundException {
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
