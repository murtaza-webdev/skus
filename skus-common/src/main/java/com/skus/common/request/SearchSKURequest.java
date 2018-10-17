package com.skus.common.request;

import java.io.Serializable;

public class SearchSKURequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String location;
	private String department;
	private String category;
	private String subCategory;

	public SearchSKURequest() {
		
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "SearchSKURequest [location=" + location + ", department=" + department + ", category=" + category
				+ ", subCategory=" + subCategory + "]";
	}
}