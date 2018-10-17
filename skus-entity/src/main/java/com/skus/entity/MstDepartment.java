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

import com.skus.common.enums.CommonStatus;
import com.skus.common.request.DepartmentRequest;
import com.skus.common.request.StoreRequest;


/**
 * The persistent class for the mst_department database table.
 * 
 */
@Entity
@Table(name="mst_department")
@NamedQuery(name="MstDepartment.findAll", query="SELECT m FROM MstDepartment m")
public class MstDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="department_id")
	private Integer departmentId;

	@Column(name="cooling_level")
	private Byte coolingLevel;

	@Column(name="created_date")
	private LocalDateTime createdDate;

	private String description;

	@Column(name="is_active")
	private Byte isActive;

	private String name;

	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to MapStoreDepartment
	@OneToMany(mappedBy="mstDepartment")
	private List<MapStoreDepartment> mapStoreDepartments;

	public MstDepartment() {
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Byte getCoolingLevel() {
		return this.coolingLevel;
	}

	public void setCoolingLevel(Byte coolingLevel) {
		this.coolingLevel = coolingLevel;
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

	public List<MapStoreDepartment> getMapStoreDepartments() {
		return this.mapStoreDepartments;
	}

	public void setMapStoreDepartments(List<MapStoreDepartment> mapStoreDepartments) {
		this.mapStoreDepartments = mapStoreDepartments;
	}

	public MapStoreDepartment addMapStoreDepartment(MapStoreDepartment mapStoreDepartment) {
		getMapStoreDepartments().add(mapStoreDepartment);
		mapStoreDepartment.setMstDepartment(this);

		return mapStoreDepartment;
	}

	public MapStoreDepartment removeMapStoreDepartment(MapStoreDepartment mapStoreDepartment) {
		getMapStoreDepartments().remove(mapStoreDepartment);
		mapStoreDepartment.setMstDepartment(null);

		return mapStoreDepartment;
	}
	
	public static MstDepartment createNewEntity(DepartmentRequest departmentRequest) {
		MstDepartment mstDepartment = new MstDepartment();
		mstDepartment.setName(departmentRequest.getName());
		mstDepartment.setCoolingLevel(departmentRequest.getCoolingLevel());
		mstDepartment.setDescription(departmentRequest.getDescription());
		mstDepartment.setCreatedDate(LocalDateTime.now());
		mstDepartment.setIsActive(CommonStatus.ACTIVE.getStatusCode());
		return mstDepartment;
	}

	public void updateEntity(DepartmentRequest departmentReq) {
		if (departmentReq.getName() != null) {
			this.setName(departmentReq.getName());
		}
		if (departmentReq.getDescription() != null) {
			this.setDescription(departmentReq.getDescription());
		}
		if (departmentReq.getCoolingLevel() != null) {
			this.setCoolingLevel(departmentReq.getCoolingLevel());
		}
		this.setUpdatedDate(LocalDateTime.now());
		this.setIsActive(CommonStatus.ACTIVE.getStatusCode());
	}
	
}