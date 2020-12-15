package com.fitscorp.j2eemobileapi.restservices.restservices.dto;

import java.util.List;


public class HomeDTO {
    
    private List<PromotionDTO> promotions;
    private List<CategoryDTO> categories;
    private List<RecommendedDTO> recommendedList;

    public List<PromotionDTO> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<PromotionDTO> promotions) {
        this.promotions = promotions;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<RecommendedDTO> getRecommendedList() {
        return recommendedList;
    }

    public void setRecommendedList(List<RecommendedDTO> recommendedList) {
        this.recommendedList = recommendedList;
    }
    
    
}
