/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitscorp.j2eemobileapi.restservices.restservices.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class SubCategoryDTO {

    private Long id;
    private Long categoryId;
    private Long storeId;
    private String name;
    private Date promoStartDate;
    private Date promoEndDate;
    private String promoDescription;
    private List<String> images;
	
    public SubCategoryDTO() {}

	public SubCategoryDTO(Long id, Long categoryId, Long storeId, String name, Date promoStartDate,
			Date promoEndDate, String promoDescription, List<String> images) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.storeId = storeId;
		this.name = name;
		this.promoStartDate = promoStartDate;
		this.promoEndDate = promoEndDate;
		this.promoDescription = promoDescription;
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
