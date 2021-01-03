package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.ProductResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();

	}

	@GetMapping("/products/{id}")
	public Product getProductsById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@GetMapping("/{subCategoryId}/products")
	public ProductResponse getProductsBySubCategoryId(@PathVariable Long subCategoryId) {
		try {
			return productService.getProductsBySubCategoryId(subCategoryId);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ProductResponse(new ArrayList<>());
		}
	}

	@GetMapping(value = "/products/{criteria}", params = {"pageNo", "pageSize"})
	public ProductResponse searchProduct(@PathVariable String criteria, 
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) throws Exception {
		Pageable pageableRequest = PageRequest.of(pageNo-1, pageSize, Sort.Direction.DESC, "createdDate");
		try {
			return productService.findProductsByName(criteria, pageableRequest);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ProductResponse(new ArrayList<>());
		}
	}
	
}
