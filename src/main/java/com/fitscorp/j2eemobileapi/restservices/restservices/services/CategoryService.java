package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Category;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.CategoryRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.SubCategoryRepository;



@Service
public class CategoryService {

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	

	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
	
	public List<SubCategory> getSubAllCategory() {
		return subCategoryRepository.findAll();
	}

	
}