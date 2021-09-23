package com.shopping.exception;

import org.springframework.http.HttpStatus;

public class ShoppingException extends RuntimeException {
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	protected ErrorCode errorCode = null;

	protected ErrorType type = null;

	protected String message = null;

	protected String detailedMessage = null;


	public ShoppingException(String message) {

		super(message);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public ErrorType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getDetailedMessage() {
		return detailedMessage;
	}

}
