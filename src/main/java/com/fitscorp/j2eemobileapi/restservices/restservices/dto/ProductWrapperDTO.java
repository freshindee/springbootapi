package com.fitscorp.j2eemobileapi.restservices.restservices.dto;

import java.util.List;

public class ProductWrapperDTO {
	
	private List<ProductDTO> products;

	public ProductWrapperDTO(List<ProductDTO> products) {
		this.products = products;
	}

	public ProductWrapperDTO() {}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

}
