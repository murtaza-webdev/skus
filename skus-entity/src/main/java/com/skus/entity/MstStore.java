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

import com.skus.common.enums.CommonStatus;
import com.skus.common.request.StoreRequest;


/**
 * The persistent class for the mst_store database table.
 * 
 */
@Entity
@Table(name="mst_store")
@NamedQuery(name="MstStore.findAll", query="SELECT m FROM MstStore m")
public class MstStore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_id")
	private Integer storeId;

	@Column(name="address_area")
	private String addressArea;

	@Column(name="address_land_mark")
	private String addressLandMark;

	@Column(name="address_line")
	private String addressLine;

	@Column(name="created_date")
	private LocalDateTime createdDate;

	@Column(name="is_active")
	private Byte isActive;

	@Column(name="location_latitude")
	private Double locationLatitude;

	@Column(name="location_longitude")
	private Double locationLongitude;

	private String name;

	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to MapStoreDepartment
	@OneToMany(mappedBy="mstStore")
	private List<MapStoreDepartment> mapStoreDepartments;

	//bi-directional many-to-one association to MstCity
	@ManyToOne
	@JoinColumn(name="city_id")
	private MstCity mstCity;

	//bi-directional many-to-one association to MstPincode
	@ManyToOne
	@JoinColumn(name="pincode")
	private MstPincode mstPincode;

	public MstStore() {
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getAddressArea() {
		return this.addressArea;
	}

	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}

	public String getAddressLandMark() {
		return this.addressLandMark;
	}

	public void setAddressLandMark(String addressLandMark) {
		this.addressLandMark = addressLandMark;
	}

	public String getAddressLine() {
		return this.addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
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

	public Double getLocationLatitude() {
		return this.locationLatitude;
	}

	public void setLocationLatitude(Double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public Double getLocationLongitude() {
		return this.locationLongitude;
	}

	public void setLocationLongitude(Double locationLongitude) {
		this.locationLongitude = locationLongitude;
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
		mapStoreDepartment.setMstStore(this);

		return mapStoreDepartment;
	}

	public MapStoreDepartment removeMapStoreDepartment(MapStoreDepartment mapStoreDepartment) {
		getMapStoreDepartments().remove(mapStoreDepartment);
		mapStoreDepartment.setMstStore(null);

		return mapStoreDepartment;
	}

	public MstCity getMstCity() {
		return this.mstCity;
	}

	public void setMstCity(MstCity mstCity) {
		this.mstCity = mstCity;
	}

	public MstPincode getMstPincode() {
		return this.mstPincode;
	}

	public void setMstPincode(MstPincode mstPincode) {
		this.mstPincode = mstPincode;
	}
	
	public static MstStore createNewEntity(StoreRequest storeReq) {
		MstStore ms = new MstStore();
		ms.setStoreId(null);
		ms.setAddressArea(storeReq.getAddressArea());
		ms.setAddressLandMark(storeReq.getAddressLandMark());
		ms.setAddressLine(storeReq.getAddressLine());
		ms.setLocationLatitude(storeReq.getLocationLatitude());
		ms.setLocationLongitude(storeReq.getLocationLongitude());
		ms.setName(storeReq.getName());
		ms.setCreatedDate(LocalDateTime.now());
		ms.setIsActive(CommonStatus.ACTIVE.getStatusCode());
		return ms;
	}
	
	public void updateEntity(StoreRequest storeReq) {
		if (storeReq.getAddressArea() != null) {
			this.setAddressArea(storeReq.getAddressArea());
		}

		if (storeReq.getAddressLandMark() != null) {
			this.setAddressLandMark(storeReq.getAddressLandMark());
		}

		if (storeReq.getAddressLine() != null) {
			this.setAddressLine(storeReq.getAddressLine());
		}

		if (storeReq.getLocationLatitude() != null) {
			this.setLocationLatitude(storeReq.getLocationLatitude());
		}

		if (storeReq.getLocationLongitude() != null) {
			this.setLocationLongitude(storeReq.getLocationLongitude());
		}

		if (storeReq.getName() != null) {
			this.setName(storeReq.getName());
		}
		this.setUpdatedDate(LocalDateTime.now());
		this.setIsActive(CommonStatus.ACTIVE.getStatusCode());
	}
}