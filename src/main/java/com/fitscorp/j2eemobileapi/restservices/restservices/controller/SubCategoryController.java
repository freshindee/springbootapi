package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.SubCategoryDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.SubCategoryWrapperDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.SubCategoryService;


@RestController
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@GetMapping("/{catId}/sub")
	public SubCategoryWrapperDTO<SubCategoryDTO> getAllSubCategory(@PathVariable Long catId) throws NotFoundException {
		return subCategoryService.getSubCategoriesByCategoryId(catId);
	}
	
	@GetMapping("/promotions")
	@ResponseBody
	public SubCategoryWrapperDTO<SubCategory> getPromotionSubCategories() throws Exception {
		return subCategoryService.findAllPromotionSubCategories();
	}
}
