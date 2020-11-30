package com.shopping.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ShoppingError implements Serializable {
	
	private String errorCode = null;
	
	private String type = null;
	
	private String message = null;
	
	private String detailedMessage = null;
	
	
	
	public ShoppingError(String errorCode, String type, String message) {
		
		this.errorCode = errorCode;
		this.type = type;
		this.message = message;

	}
	
	public ShoppingError(String errorCode,String type,  String message, String detailedMessage) {
		
		this.errorCode = errorCode;
		this.type = type;
		this.message = message;

		this.detailedMessage = detailedMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getDetailedMessage() {
		return detailedMessage;
	}
	
	

}
