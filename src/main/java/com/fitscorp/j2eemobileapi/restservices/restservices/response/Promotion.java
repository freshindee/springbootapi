package com.fitscorp.j2eemobileapi.restservices.restservices.response;

import java.util.List;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.PromotionDTO;

public class Promotion {
	List<PromotionDTO> promotions;

	public Promotion() {
	}

	public Promotion(List<PromotionDTO> promotions) {
		super();
		this.promotions = promotions;
	}

	public List<PromotionDTO> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionDTO> promotions) {
		this.promotions = promotions;
	}
}
