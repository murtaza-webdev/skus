package com.skus.common.request;

import java.io.Serializable;

public class DepartmentRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer departmentId;
	private String name;
	private String description;
	private Byte coolingLevel;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getCoolingLevel() {
		return coolingLevel;
	}

	public void setCoolingLevel(Byte coolingLevel) {
		this.coolingLevel = coolingLevel;
	}

	@Override
	public String toString() {
		return "DepartmentRequest [departmentId=" + departmentId + ", name=" + name + ", description=" + description
				+ ", coolingLevel=" + coolingLevel + "]";
	}
}