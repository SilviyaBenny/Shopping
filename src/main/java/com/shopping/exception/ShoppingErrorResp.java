package com.shopping.exception;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopping.error.ShoppingError;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingErrorResp implements Serializable{
	
	private String message;
	
	private String errorCode;
	
	private String type;
	
	@JsonProperty("errorDetails")
	private List<ShoppingError> errorDetails;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ShoppingError> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<ShoppingError> errorDetails) {
		this.errorDetails = errorDetails;
	}
	

}
