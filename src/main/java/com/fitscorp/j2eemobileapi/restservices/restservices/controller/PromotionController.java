package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.Product;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.Promotion;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.ProductService;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.SubCategoryService;

@RestController
public class PromotionController {

	@Autowired
	private ProductService productService;
	
//	@GetMapping("/promotions")
//	public List<Product> getAllPromotions() {
//		return productService.getAllPromotions();
//
//	}

	public Promotion getPromotionProducts() throws Exception {
		return new Promotion();
	}
}
