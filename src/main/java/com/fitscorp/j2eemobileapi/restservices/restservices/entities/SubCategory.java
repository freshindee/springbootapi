package com.fitscorp.j2eemobileapi.restservices.restservices.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SubCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long categoryId;
	private Long storeId;
	private String name;
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
	private Set<File> images;
	private String promoStartDate;
	private String promoEndDate;
	private String promoDescription;
	
	public SubCategory() {}

	public SubCategory(Long id, Long categoryId, Long storeId, String name, Set<File> images, String promoStartDate,
			String promoEndDate, String promoDescription) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.storeId = storeId;
		this.name = name;
		this.images = images;
		this.promoStartDate = promoStartDate;
		this.promoEndDate = promoEndDate;
		this.promoDescription = promoDescription;
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

	public Set<File> getImages() {
		return images;
	}

	public void setImages(Set<File> images) {
		this.images = images;
	}

	public String getPromoStartDate() {
		return promoStartDate;
	}

	public void setPromoStartDate(String promoStartDate) {
		this.promoStartDate = promoStartDate;
	}

	public String getPromoEndDate() {
		return promoEndDate;
	}

	public void setPromoEndDate(String promoEndDate) {
		this.promoEndDate = promoEndDate;
	}

	public String getPromoDescription() {
		return promoDescription;
	}

	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	@Override
	public String toString() {
		return "SubCategory [id=" + id + ", categoryId=" + categoryId + ", storeId=" + storeId + ", name=" + name
				+ ", images=" + images + ", promoStartDate=" + promoStartDate + ", promoEndDate=" + promoEndDate
				+ ", promoDescription=" + promoDescription + "]";
	}
	
}
