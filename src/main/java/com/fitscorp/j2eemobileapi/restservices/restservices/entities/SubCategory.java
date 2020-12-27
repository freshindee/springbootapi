package com.fitscorp.j2eemobileapi.restservices.restservices.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
*
* @author User
*/
@Entity
@Table(name = "sub_category")
@NamedQueries({
   @NamedQuery(name = "SubCategory.findAll", query = "SELECT s FROM SubCategory s")})
public class SubCategory implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "id")
   private Long id;
   @Column(name = "name")
   private String name;
   @JsonIgnore
   @Column(name = "is_promo")
   private Integer isPromo;
   @Column(name = "promotion_start_date")
   @Temporal(TemporalType.TIMESTAMP)
   private Date promotionStartDate;
   @Column(name = "promotion_end_date")
   @Temporal(TemporalType.TIMESTAMP)
   private Date promotionEndDate;
   @Column(name = "promotion_description")
   private String promotionDescription;
   @JsonIgnore
   @Column(name = "created_by")
   private String createdBy;
   @JsonIgnore
   @Column(name = "created_date")
   @Temporal(TemporalType.TIMESTAMP)
   private Date createdDate;
   @JsonIgnore
   @Column(name = "enabled")
   private Boolean enabled;
   @JsonIgnore
   @Column(name = "modified_by")
   private String modifiedBy;
   @JsonIgnore
   @Column(name = "modified_date")
   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedDate;
   private Long categoryId;
   @Column(name = "store_id")
   private Long storeId;
   @ElementCollection(targetClass=String.class)
   private List<String> images;

   public SubCategory() {
   }

   public SubCategory(Long id) {
       this.id = id;
   }

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public Integer getIsPromo() {
       return isPromo;
   }

   public void setIsPromo(Integer isPromo) {
       this.isPromo = isPromo;
   }

   public Date getPromotionStartDate() {
       return promotionStartDate;
   }

   public void setPromotionStartDate(Date promotionStartDate) {
       this.promotionStartDate = promotionStartDate;
   }

   public Date getPromotionEndDate() {
       return promotionEndDate;
   }

   public void setPromotionEndDate(Date promotionEndDate) {
       this.promotionEndDate = promotionEndDate;
   }

   public String getPromotionDescription() {
       return promotionDescription;
   }

   public void setPromotionDescription(String promotionDescription) {
       this.promotionDescription = promotionDescription;
   }

   public String getCreatedBy() {
       return createdBy;
   }

   public void setCreatedBy(String createdBy) {
       this.createdBy = createdBy;
   }

   public Date getCreatedDate() {
       return createdDate;
   }

   public void setCreatedDate(Date createdDate) {
       this.createdDate = createdDate;
   }

   public Boolean getEnabled() {
       return enabled;
   }

   public void setEnabled(Boolean enabled) {
       this.enabled = enabled;
   }

   public String getModifiedBy() {
       return modifiedBy;
   }

   public void setModifiedBy(String modifiedBy) {
       this.modifiedBy = modifiedBy;
   }

   public Date getModifiedDate() {
       return modifiedDate;
   }

   public void setModifiedDate(Date modifiedDate) {
       this.modifiedDate = modifiedDate;
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

	
public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

@Override
   public int hashCode() {
       int hash = 0;
       hash += (id != null ? id.hashCode() : 0);
       return hash;
   }

   @Override
   public boolean equals(Object object) {
       // TODO: Warning - this method won't work in the case the id fields are not set
       if (!(object instanceof SubCategory)) {
           return false;
       }
       SubCategory other = (SubCategory) object;
       if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
           return false;
       }
       return true;
   }

   @Override
   public String toString() {
       return "com.fitscorp.j2eemobileapi.restservices.entities.SubCategory[ id=" + id + " ]";
   }
   
}

//@Entity
//@Table(name="sub_category")
//@NamedQuery(name="SubCategory.findAll", query="SELECT s FROM SubCategory s")
//public class SubCategory implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	private String id;
//
//	@Column(name="created_by")
//	private String createdBy;
//
//	
//	@Column(name="created_date")
//	private Date createdDate;
//
//	private byte enabled;
//
//	@Column(name="is_promo")
//	private byte isPromo;
//
//	@Column(name="modified_by")
//	private String modifiedBy;
//
//	
//	@Column(name="modified_date")
//	private Date modifiedDate;
//
//	private String name;
//
//	@Column(name="promotion_description")
//	private String promotionDescription;
//
//	
//	@Column(name="promotion_end_date")
//	private Date promotionEndDate;
//
//	
//	@Column(name="promotion_start_date")
//	private Date promotionStartDate;
//
//	//bi-directional many-to-one association to Category
//	@ManyToOne
//	private Category category;
//
//	
//	public SubCategory() {
//	}
//	
//	
//	public SubCategory(String id, String createdBy, Date createdDate, byte enabled, byte isPromo, String modifiedBy,
//			Date modifiedDate, String name, String promotionDescription, Date promotionEndDate, Date promotionStartDate,
//			Category category) {
//		super();
//		this.id = id;
//		this.createdBy = createdBy;
//		this.createdDate = createdDate;
//		this.enabled = enabled;
//		this.isPromo = isPromo;
//		this.modifiedBy = modifiedBy;
//		this.modifiedDate = modifiedDate;
//		this.name = name;
//		this.promotionDescription = promotionDescription;
//		this.promotionEndDate = promotionEndDate;
//		this.promotionStartDate = promotionStartDate;
//		this.category = category;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public byte getEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(byte enabled) {
//		this.enabled = enabled;
//	}
//
//	public byte getIsPromo() {
//		return isPromo;
//	}
//
//	public void setIsPromo(byte isPromo) {
//		this.isPromo = isPromo;
//	}
//
//	public String getModifiedBy() {
//		return modifiedBy;
//	}
//
//	public void setModifiedBy(String modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}
//
//	public Date getModifiedDate() {
//		return modifiedDate;
//	}
//
//	public void setModifiedDate(Date modifiedDate) {
//		this.modifiedDate = modifiedDate;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getPromotionDescription() {
//		return promotionDescription;
//	}
//
//	public void setPromotionDescription(String promotionDescription) {
//		this.promotionDescription = promotionDescription;
//	}
//
//	public Date getPromotionEndDate() {
//		return promotionEndDate;
//	}
//
//	public void setPromotionEndDate(Date promotionEndDate) {
//		this.promotionEndDate = promotionEndDate;
//	}
//
//	public Date getPromotionStartDate() {
//		return promotionStartDate;
//	}
//
//	public void setPromotionStartDate(Date promotionStartDate) {
//		this.promotionStartDate = promotionStartDate;
//	}
//
//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}
//	
//	
//	
//}
