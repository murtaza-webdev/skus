package com.skus.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the map_store_department database table.
 * 
 */
@Entity
@Table(name="map_store_department")
@NamedQuery(name="MapStoreDepartment.findAll", query="SELECT m FROM MapStoreDepartment m")
public class MapStoreDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_department_id")
	private Integer storeDepartmentId;

	@Column(name="created_date")
	private LocalDateTime createdDate;

	@Column(name="is_active")
	private Byte isActive;

	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to MstDepartment
	@ManyToOne
	@JoinColumn(name="department_id")
	private MstDepartment mstDepartment;

	//bi-directional many-to-one association to MstStore
	@ManyToOne
	@JoinColumn(name="store_id")
	private MstStore mstStore;

	//bi-directional many-to-one association to MapStoreDepartmentSku
	@OneToMany(mappedBy="mapStoreDepartment")
	private List<MapStoreDepartmentSku> mapStoreDepartmentSkus;

	public MapStoreDepartment() {
	}

	public Integer getStoreDepartmentId() {
		return this.storeDepartmentId;
	}

	public void setStoreDepartmentId(Integer storeDepartmentId) {
		this.storeDepartmentId = storeDepartmentId;
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

	public MstDepartment getMstDepartment() {
		return this.mstDepartment;
	}

	public void setMstDepartment(MstDepartment mstDepartment) {
		this.mstDepartment = mstDepartment;
	}

	public MstStore getMstStore() {
		return this.mstStore;
	}

	public void setMstStore(MstStore mstStore) {
		this.mstStore = mstStore;
	}

	public List<MapStoreDepartmentSku> getMapStoreDepartmentSkus() {
		return this.mapStoreDepartmentSkus;
	}

	public void setMapStoreDepartmentSkus(List<MapStoreDepartmentSku> mapStoreDepartmentSkus) {
		this.mapStoreDepartmentSkus = mapStoreDepartmentSkus;
	}

	public MapStoreDepartmentSku addMapStoreDepartmentSkus(MapStoreDepartmentSku mapStoreDepartmentSkus) {
		getMapStoreDepartmentSkus().add(mapStoreDepartmentSkus);
		mapStoreDepartmentSkus.setMapStoreDepartment(this);

		return mapStoreDepartmentSkus;
	}

	public MapStoreDepartmentSku removeMapStoreDepartmentSkus(MapStoreDepartmentSku mapStoreDepartmentSkus) {
		getMapStoreDepartmentSkus().remove(mapStoreDepartmentSkus);
		mapStoreDepartmentSkus.setMapStoreDepartment(null);

		return mapStoreDepartmentSkus;
	}

}