package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.ProductWrapperDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.ProductService;


@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();

	}
	
//	@GetMapping("/categories")
//	public List<Category> getAllCategory() {
//		return categoryService.getAllCategory();
//
//	}
	
//	@GetMapping("/subcategories")
//	public List<SubCategory> getAllSubCategory() {
//		return categoryService.getSubAllCategory();
//	}
	
	@GetMapping("/{subCategoryId}/products")
	public ProductWrapperDTO getProductsBySubCategoryId(@PathVariable Long subCategoryId) {
		try {
			return productService.getProductsBySubCategoryId(subCategoryId);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ProductWrapperDTO(new ArrayList<>());
		}
	}
}
