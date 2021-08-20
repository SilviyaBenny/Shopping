package com.shopping.exception;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopping.error.ShoppingError;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingErrorResp implements Serializable {

	private String message;

	private ErrorCode errorCode;

	private ErrorType type;

	@JsonProperty("errorDetails")
	private List<ShoppingError> errorDetails;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorType getType() {
		return type;
	}

	public void setType(ErrorType type) {
		this.type = type;
	}

	public List<ShoppingError> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<ShoppingError> errorDetails) {
		this.errorDetails = errorDetails;
	}

}
