package com.fitscorp.j2eemobileapi.restservices.restservices.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;

public class PromotionDTO {
    
    private Long categoryId;
    private Long storeId;
    private Long productId;
    private Long subCategoryId;
    private String subCategoryName;
    private Date promoStartDate;
    private Date promoEndDate;
    private String promoDescription;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private String unit;
    private List<String> images;
    
    
    public PromotionDTO() {
	}
    
	public PromotionDTO(Long categoryId, Long storeId, Long productId, Long subCategoryId, String subCategoryName,
			Date promoStartDate, Date promoEndDate, String promoDescription, String name, String description,
			BigDecimal price, BigDecimal discountedPrice, String unit, List<String> images) {
		super();
		this.categoryId = categoryId;
		this.storeId = storeId;
		this.productId = productId;
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
		this.promoStartDate = promoStartDate;
		this.promoEndDate = promoEndDate;
		this.promoDescription = promoDescription;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.unit = unit;
		this.images = images;
	}



	public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long string) {
        this.categoryId = string;
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

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subCategoryName = subcategoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

	public void setPromoStartDate(Date promoStartDate) {
		this.promoStartDate = promoStartDate;
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

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
    
}
