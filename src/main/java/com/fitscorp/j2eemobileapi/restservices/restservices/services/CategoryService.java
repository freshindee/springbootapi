package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Category;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.CategoryRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CategoryService {

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	

	public List<Category> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		for (Category cat : categories) {
			List<String> images = findAllImages(cat.getCategoryId(), 2L);
			cat.setImages(images);
		}
		return categories;
	}
	
	public List<SubCategory> getSubAllCategory() {
		return subCategoryRepository.findAll();
	}

	public List<String> findAllImages(Long catId, Long tableDef) {
		return categoryRepository.findAllImages(catId, tableDef);
	}
	
}