package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.ProductRepository;


@Service
public class ProductService {

	
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> getProductsBySubCategoryId(Long subCatId) {
		return productRepository.findProductsBySubCategoryId(subCatId);
	}
	
	

	
}
