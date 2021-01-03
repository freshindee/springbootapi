/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitscorp.j2eemobileapi.restservices.restservices.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author User
 */
@Table(name = "fav_product")
@Entity
@NamedQueries({
    @NamedQuery(name = "FavProduct.findAll", query = "SELECT f FROM FavProduct f")})
public class FavProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private BigInteger id;
    @Column(name = "product_id")
    private BigInteger productId;
    @Column(name = "user_id")
    private BigInteger userId;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    public FavProduct() {
    }
    
    public FavProduct(BigInteger productId, BigInteger userId, String createdBy, Date createdDate, Boolean enabled,
			String modifiedBy, Date modifiedDate) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.enabled = enabled;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}



	public FavProduct(BigInteger id, BigInteger userId, BigInteger productId, String createdBy, Date createdDate, Boolean enabled, String modifiedBy,
			Date modifiedDate) {
		this.id = id;
        this.productId = productId;
        this.userId = userId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.enabled = enabled;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}



	public FavProduct(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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
    
    public BigInteger getProductId() {
		return productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
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
        if (!(object instanceof FavProduct)) {
            return false;
        }
        FavProduct other = (FavProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fitscorp.j2eemobileapi.restservices.entities.FavProduct[ id=" + id + " ]";
    }
    
}
