package com.skus.common.vos;

import com.skus.common.enums.ErrorCodeEnum;

public class ErrorVO {
	private Integer code;
	private String message;

	public void setError(ErrorCodeEnum codeEnum) {
		setCode(codeEnum.getCode());
		setMessage(codeEnum.getDisplayMessage());
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ErrorVO [code=" + code + ", message=" + message + "]";
	}
}
