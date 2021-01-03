package com.fitscorp.j2eemobileapi.restservices.restservices.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RecommendedDTO {
    
    private Long categoryId;
    private Long storeId;
    private Long productId;
    private String name;
    private String categoryName;
    private Long subCategoryId;
    private String subcategoryName;
    private String description;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private String unit;
    private Date promoStartDate;
    private Date promoEndDate;
    private String promoDescription;
    private Boolean isFavorite;
    private List<String> images;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long long1) {
        this.categoryId = long1;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long id) {
        this.subCategoryId = id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getPromoStartDate() {
        return promoStartDate;
    }

    public void setPromoStartDate(Date date) {
        this.promoStartDate = date;
    }

    public Date getPromoEndDate() {
        return promoEndDate;
    }

    public void setPromoEndDate(Date promoEndDate) {
        this.promoEndDate = promoEndDate;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
    
}
