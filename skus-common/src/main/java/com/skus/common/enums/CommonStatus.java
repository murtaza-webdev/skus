package com.skus.common.enums;

public enum CommonStatus {
	ACTIVE(new Byte("1")), INACTIVE(new Byte("0"));

	private Byte statusCode;

	CommonStatus(Byte statusCode) {
		this.statusCode = statusCode;
	}

	public Byte getStatusCode() {
		return statusCode;
	}
}