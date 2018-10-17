package com.skus.common.enums;

public enum ErrorCodeEnum {
	SUCCESS(2001, "Success"), FAIL(1001, "Fail"), INTERNAL_SERVER_ERROR(1002, "Internal Server Error"), 
	DATA_NOT_FOUND(1003, "Data not found"), INVALID_DATA_REQUEST(1004, "Invalid request data"),
	CITY_NOT_FOUND(1005, "City not found"), PINCODE_NOT_FOUND(1005, "Pincode not found"), STORE_NOT_FOUND(1005, "Location not found"), 
	DEPARTMENT_NOT_FOUND(1005, "Department not found");

	private Integer code;
	private String displayMessage;

	ErrorCodeEnum(Integer code, String displayMessage) {
		this.code = code;
		this.displayMessage = displayMessage;
	}

	public Integer getCode() {
		return code;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}
}
