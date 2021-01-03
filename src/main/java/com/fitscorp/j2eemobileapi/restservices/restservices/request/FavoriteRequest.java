package com.fitscorp.j2eemobileapi.restservices.restservices.request;

import java.util.List;

public class FavoriteRequest {
	List<Long> productIds;
	
	public FavoriteRequest() {
	}

	public FavoriteRequest(List<Long> productIds) {
		this.productIds = productIds;
	}

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
}
