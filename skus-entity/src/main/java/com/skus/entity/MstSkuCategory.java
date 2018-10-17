package com.skus.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the mst_sku_category database table.
 * 
 */
@Entity
@Table(name="mst_sku_category")
@NamedQuery(name="MstSkuCategory.findAll", query="SELECT m FROM MstSkuCategory m")
public class MstSkuCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sku_category_id")
	private Integer skuCategoryId;

	@Column(name="created_date")
	private LocalDateTime createdDate;

	@Column(name="is_active")
	private Byte isActive;

	private String name;

	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to MstSkuSubCategory
	@OneToMany(mappedBy="mstSkuCategory")
	private List<MstSkuSubCategory> mstSkuSubCategories;

	public MstSkuCategory() {
	}

	public Integer getSkuCategoryId() {
		return this.skuCategoryId;
	}

	public void setSkuCategoryId(Integer skuCategoryId) {
		this.skuCategoryId = skuCategoryId;
	}

	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<MstSkuSubCategory> getMstSkuSubCategories() {
		return this.mstSkuSubCategories;
	}

	public void setMstSkuSubCategories(List<MstSkuSubCategory> mstSkuSubCategories) {
		this.mstSkuSubCategories = mstSkuSubCategories;
	}

	public MstSkuSubCategory addMstSkuSubCategory(MstSkuSubCategory mstSkuSubCategory) {
		getMstSkuSubCategories().add(mstSkuSubCategory);
		mstSkuSubCategory.setMstSkuCategory(this);

		return mstSkuSubCategory;
	}

	public MstSkuSubCategory removeMstSkuSubCategory(MstSkuSubCategory mstSkuSubCategory) {
		getMstSkuSubCategories().remove(mstSkuSubCategory);
		mstSkuSubCategory.setMstSkuCategory(null);

		return mstSkuSubCategory;
	}

}