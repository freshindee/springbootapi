package com.fitscorp.j2eemobileapi.restservices.restservices.dto;

import java.util.List;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.SubCategory;

public class SubCategoryWrapperDTO<T> {
	
	private List<T> subCategories;

	public SubCategoryWrapperDTO(List<T> subCategoryDtos) {
		this.subCategories = subCategoryDtos;
	}

	public SubCategoryWrapperDTO() {}

	public List<T> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<T> subCategories) {
		this.subCategories = subCategories;
	}

}
