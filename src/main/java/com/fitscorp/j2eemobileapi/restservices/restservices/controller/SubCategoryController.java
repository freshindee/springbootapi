package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.SubCategoryService;


@RestController
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@GetMapping("/{catId}/sub")
	public List<SubCategory> getAllSubCategory(@PathVariable Long catId) throws NotFoundException {
		Optional<List<SubCategory>> result = subCategoryService.getSubCategoriesByCategoryId(catId);
		if (result.isPresent()) {
			return result.get();
		}
		throw new NotFoundException("There are no sub categories for category id " + catId.toString());
	}
}
