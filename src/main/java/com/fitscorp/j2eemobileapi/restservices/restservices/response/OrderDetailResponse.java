package com.fitscorp.j2eemobileapi.restservices.restservices.response;


import com.fitscorp.j2eemobileapi.restservices.restservices.dto.ProductDTO;

public class OrderDetailResponse {

	private ProductDTO product;
	private int quantity;

	public OrderDetailResponse() {}

	public OrderDetailResponse(ProductDTO product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}