package com.skus.common.request;

import java.io.Serializable;

public class StoreRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String addressArea;
	private String addressLandMark;
	private String addressLine;
	private Double locationLatitude;
	private Double locationLongitude;
	private String name;
	private String cityName;
	private Integer pincode;

	public StoreRequest() {
		
	}
	
	public StoreRequest(Integer storeId, String addressArea, String addressLandMark, String addressLine,
			Double locationLatitude, Double locationLongitude, String name, String cityName, Integer pincode) {
		super();
		this.storeId = storeId;
		this.addressArea = addressArea;
		this.addressLandMark = addressLandMark;
		this.addressLine = addressLine;
		this.locationLatitude = locationLatitude;
		this.locationLongitude = locationLongitude;
		this.name = name;
		this.cityName = cityName;
		this.pincode = pincode;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getAddressArea() {
		return addressArea;
	}

	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}

	public String getAddressLandMark() {
		return addressLandMark;
	}

	public void setAddressLandMark(String addressLandMark) {
		this.addressLandMark = addressLandMark;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public Double getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(Double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public Double getLocationLongitude() {
		return locationLongitude;
	}

	public void setLocationLongitude(Double locationLongitude) {
		this.locationLongitude = locationLongitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "StoreRequest [storeId=" + storeId + ", addressArea=" + addressArea + ", addressLandMark="
				+ addressLandMark + ", addressLine=" + addressLine + ", locationLatitude=" + locationLatitude
				+ ", locationLongitude=" + locationLongitude + ", name=" + name + ", cityName=" + cityName
				+ ", pincode=" + pincode + "]";
	}
}