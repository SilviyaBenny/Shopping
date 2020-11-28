package com.shopping.exception;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends ShoppingException {
	
    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    
	private String errorCode = null;
	
	private String type = null;
	
	private String message = null;
	
	private String detailedMessage = null;
	
	public ItemNotFoundException(String errorCode, String type, String message) {
		super(message);
		this.errorCode = errorCode;
		this.type = type;
		this.message = message;
	}
	
	public ItemNotFoundException(String errorCode, String type,  String message,String detailedMessage) {
		super(message);
		this.errorCode = errorCode;
		this.type = type;
		this.message = message;
		this.detailedMessage = detailedMessage;
	}

    
	public HttpStatus getHttpStatus() {
		return httpStatus;
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
