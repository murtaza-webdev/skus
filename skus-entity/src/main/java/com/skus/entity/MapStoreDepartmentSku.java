package com.skus.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the map_store_department_sku database table.
 * 
 */
@Entity
@Table(name="map_store_department_sku")
@NamedQuery(name="MapStoreDepartmentSku.findAll", query="SELECT m FROM MapStoreDepartmentSku m")
public class MapStoreDepartmentSku implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_department_sku_id")
	private Integer storeDepartmentSkuId;

	@Column(name="created_date")
	private LocalDateTime createdDate;

	@Column(name="is_active")
	private Byte isActive;

	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to MstSku
	@ManyToOne
	@JoinColumn(name="sku_id")
	private MstSku mstSku;

	//bi-directional many-to-one association to MapStoreDepartment
	@ManyToOne
	@JoinColumn(name="store_department_id")
	private MapStoreDepartment mapStoreDepartment;

	public MapStoreDepartmentSku() {
	}

	public Integer getStoreDepartmentSkuId() {
		return this.storeDepartmentSkuId;
	}

	public void setStoreDepartmentSkuId(Integer storeDepartmentSkuId) {
		this.storeDepartmentSkuId = storeDepartmentSkuId;
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

	public LocalDateTime getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public MstSku getMstSku() {
		return this.mstSku;
	}

	public void setMstSku(MstSku mstSku) {
		this.mstSku = mstSku;
	}

	public MapStoreDepartment getMapStoreDepartment() {
		return this.mapStoreDepartment;
	}

	public void setMapStoreDepartment(MapStoreDepartment mapStoreDepartment) {
		this.mapStoreDepartment = mapStoreDepartment;
	}

}