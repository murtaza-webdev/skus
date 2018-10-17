package com.skus.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the mst_sku_sub_category database table.
 * 
 */
@Entity
@Table(name="mst_sku_sub_category")
@NamedQuery(name="MstSkuSubCategory.findAll", query="SELECT m FROM MstSkuSubCategory m")
public class MstSkuSubCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sky_sub_category_id")
	private Integer skySubCategoryId;

	@Column(name="care_level")
	private Byte careLevel;

	@Column(name="cost_level")
	private Byte costLevel;

	@Column(name="created_date")
	private LocalDateTime createdDate;

	@Column(name="is_active")
	private Byte isActive;

	@Column(name="margin_level")
	private Byte marginLevel;

	private String name;

	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to MstSku
	@OneToMany(mappedBy="mstSkuSubCategory")
	private List<MstSku> mstSkus;

	//bi-directional many-to-one association to MstSkuCategory
	@ManyToOne
	@JoinColumn(name="sku_category_id")
	private MstSkuCategory mstSkuCategory;

	public MstSkuSubCategory() {
	}

	public Integer getSkySubCategoryId() {
		return this.skySubCategoryId;
	}

	public void setSkySubCategoryId(Integer skySubCategoryId) {
		this.skySubCategoryId = skySubCategoryId;
	}

	public Byte getCareLevel() {
		return this.careLevel;
	}

	public void setCareLevel(Byte careLevel) {
		this.careLevel = careLevel;
	}

	public Byte getCostLevel() {
		return this.costLevel;
	}

	public void setCostLevel(Byte costLevel) {
		this.costLevel = costLevel;
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

	public Byte getMarginLevel() {
		return this.marginLevel;
	}

	public void setMarginLevel(Byte marginLevel) {
		this.marginLevel = marginLevel;
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

	public List<MstSku> getMstSkus() {
		return this.mstSkus;
	}

	public void setMstSkus(List<MstSku> mstSkus) {
		this.mstSkus = mstSkus;
	}

	public MstSku addMstSkus(MstSku mstSkus) {
		getMstSkus().add(mstSkus);
		mstSkus.setMstSkuSubCategory(this);

		return mstSkus;
	}

	public MstSku removeMstSkus(MstSku mstSkus) {
		getMstSkus().remove(mstSkus);
		mstSkus.setMstSkuSubCategory(null);

		return mstSkus;
	}

	public MstSkuCategory getMstSkuCategory() {
		return this.mstSkuCategory;
	}

	public void setMstSkuCategory(MstSkuCategory mstSkuCategory) {
		this.mstSkuCategory = mstSkuCategory;
	}

}