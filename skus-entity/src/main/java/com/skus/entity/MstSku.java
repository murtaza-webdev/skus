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
 * The persistent class for the mst_sku database table.
 * 
 */
@Entity
@Table(name="mst_sku")
@NamedQuery(name="MstSku.findAll", query="SELECT m FROM MstSku m")
public class MstSku implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sku_id")
	private Integer skuId;

	@Column(name="care_level")
	private Byte careLevel;

	@Column(name="cost_level")
	private Byte costLevel;

	@Column(name="created_date")
	private LocalDateTime createdDate;

	private String description;

	@Column(name="is_active")
	private Byte isActive;

	@Column(name="margin_level")
	private Byte marginLevel;

	private String name;

	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to MapStoreDepartmentSku
	@OneToMany(mappedBy="mstSku")
	private List<MapStoreDepartmentSku> mapStoreDepartmentSkus;

	//bi-directional many-to-one association to MstSkuSubCategory
	@ManyToOne
	@JoinColumn(name="sky_sub_category_id")
	private MstSkuSubCategory mstSkuSubCategory;

	public MstSku() {
	}

	public Integer getSkuId() {
		return this.skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<MapStoreDepartmentSku> getMapStoreDepartmentSkus() {
		return this.mapStoreDepartmentSkus;
	}

	public void setMapStoreDepartmentSkus(List<MapStoreDepartmentSku> mapStoreDepartmentSkus) {
		this.mapStoreDepartmentSkus = mapStoreDepartmentSkus;
	}

	public MapStoreDepartmentSku addMapStoreDepartmentSkus(MapStoreDepartmentSku mapStoreDepartmentSkus) {
		getMapStoreDepartmentSkus().add(mapStoreDepartmentSkus);
		mapStoreDepartmentSkus.setMstSku(this);

		return mapStoreDepartmentSkus;
	}

	public MapStoreDepartmentSku removeMapStoreDepartmentSkus(MapStoreDepartmentSku mapStoreDepartmentSkus) {
		getMapStoreDepartmentSkus().remove(mapStoreDepartmentSkus);
		mapStoreDepartmentSkus.setMstSku(null);

		return mapStoreDepartmentSkus;
	}

	public MstSkuSubCategory getMstSkuSubCategory() {
		return this.mstSkuSubCategory;
	}

	public void setMstSkuSubCategory(MstSkuSubCategory mstSkuSubCategory) {
		this.mstSkuSubCategory = mstSkuSubCategory;
	}

}