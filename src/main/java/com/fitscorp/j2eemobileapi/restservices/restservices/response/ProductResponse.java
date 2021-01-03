package com.fitscorp.j2eemobileapi.restservices.restservices.response;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.ProductDTO;

import java.util.List;

public class ProductResponse {
	
	private List<ProductDTO> products;

	public ProductResponse(List<ProductDTO> products) {
		this.products = products;
	}

	public ProductResponse() {}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

}
