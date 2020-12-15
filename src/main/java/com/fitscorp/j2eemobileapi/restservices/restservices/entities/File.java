package com.fitscorp.j2eemobileapi.restservices.restservices.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long fileType;
	private Long tableId;
	private Long fileProperty;
	private String path;
	private String name;
	private String originalName;
	@ManyToOne
	private SubCategory subCategory;
	
	public File() {}

	public File(Long id, Long fileType, Long tableId, Long fileProperty, String path, String name,
			String originalName, SubCategory subCategory) {
		super();
		this.id = id;
		this.fileType = fileType;
		this.tableId = tableId;
		this.fileProperty = fileProperty;
		this.path = path;
		this.name = name;
		this.originalName = originalName;
		this.subCategory = subCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFileType() {
		return fileType;
	}

	public void setFileType(Long fileType) {
		this.fileType = fileType;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public Long getFileProperty() {
		return fileProperty;
	}

	public void setFileProperty(Long fileProperty) {
		this.fileProperty = fileProperty;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	
}
