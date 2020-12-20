package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Category;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.CategoryService;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.ProductService;


@RestController
public class ProductController {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{userId}/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();

	}
	
	@GetMapping("/{userId}/categories")
	public List<Category> getAllCategory() {
		return categoryService.getAllCategory();

	}
	
	@GetMapping("/{userId}/subcategories")
	public List<SubCategory> getAllSubCategory() {
		return categoryService.getSubAllCategory();
	}

	@GetMapping("/{userId}/{subCategoryId}/products")
	public List<Product> getProductsBySubCategoryId(@PathVariable Long userId, @PathVariable Long subCategoryId) {
		return productService.getProductsBySubCategoryId(subCategoryId);
	}
}
