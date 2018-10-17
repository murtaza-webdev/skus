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
 * The persistent class for the mst_pincode database table.
 * 
 */
@Entity
@Table(name="mst_pincode")
@NamedQuery(name="MstPincode.findAll", query="SELECT m FROM MstPincode m")
public class MstPincode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pincode;

	@Column(name="area_name")
	private String areaName;

	@Column(name="created_date")
	private LocalDateTime createdDate;

	@Column(name="is_active")
	private Byte isActive;


	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to MstCity
	@ManyToOne
	@JoinColumn(name="city_id")
	private MstCity mstCity;

	//bi-directional many-to-one association to MstStore
	@OneToMany(mappedBy="mstPincode")
	private List<MstStore> mstStores;

	public MstPincode() {
	}

	public Integer getPincode() {
		return this.pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public MstCity getMstCity() {
		return this.mstCity;
	}

	public void setMstCity(MstCity mstCity) {
		this.mstCity = mstCity;
	}

	public List<MstStore> getMstStores() {
		return this.mstStores;
	}

	public void setMstStores(List<MstStore> mstStores) {
		this.mstStores = mstStores;
	}

	public MstStore addMstStore(MstStore mstStore) {
		getMstStores().add(mstStore);
		mstStore.setMstPincode(this);

		return mstStore;
	}

	public MstStore removeMstStore(MstStore mstStore) {
		getMstStores().remove(mstStore);
		mstStore.setMstPincode(null);

		return mstStore;
	}

}