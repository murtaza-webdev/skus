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
 * The persistent class for the mst_city database table.
 * 
 */
@Entity
@Table(name="mst_city")
@NamedQuery(name="MstCity.findAll", query="SELECT m FROM MstCity m")
public class MstCity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="city_id")
	private Integer cityId;

	private String country;

	@Column(name="created_date")
	private LocalDateTime createdDate;

	@Column(name="is_active")
	private Byte isActive;

	private String name;

	private String state;

	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	//bi-directional many-to-one association to MstPincode
	@OneToMany(mappedBy="mstCity")
	private List<MstPincode> mstPincodes;

	//bi-directional many-to-one association to MstStore
	@OneToMany(mappedBy="mstCity")
	private List<MstStore> mstStores;

	public MstCity() {
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public LocalDateTime getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<MstPincode> getMstPincodes() {
		return this.mstPincodes;
	}

	public void setMstPincodes(List<MstPincode> mstPincodes) {
		this.mstPincodes = mstPincodes;
	}

	public MstPincode addMstPincode(MstPincode mstPincode) {
		getMstPincodes().add(mstPincode);
		mstPincode.setMstCity(this);

		return mstPincode;
	}

	public MstPincode removeMstPincode(MstPincode mstPincode) {
		getMstPincodes().remove(mstPincode);
		mstPincode.setMstCity(null);

		return mstPincode;
	}

	public List<MstStore> getMstStores() {
		return this.mstStores;
	}

	public void setMstStores(List<MstStore> mstStores) {
		this.mstStores = mstStores;
	}

	public MstStore addMstStore(MstStore mstStore) {
		getMstStores().add(mstStore);
		mstStore.setMstCity(this);

		return mstStore;
	}

	public MstStore removeMstStore(MstStore mstStore) {
		getMstStores().remove(mstStore);
		mstStore.setMstCity(null);

		return mstStore;
	}

}