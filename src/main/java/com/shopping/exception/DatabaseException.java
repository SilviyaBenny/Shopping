package com.shopping.exception;

import org.springframework.http.HttpStatus;

public class DatabaseException extends ShoppingException {

	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	public DatabaseException(ErrorCode errorCode, ErrorType type, String message) {
		super(message);
		this.errorCode = errorCode;
		this.type = type;
		this.message = message;
	}

	public DatabaseException(ErrorCode errorCode, ErrorType type, String message, String detailedMessage) {
		super(message);
		this.errorCode = errorCode;
		this.type = type;
		this.message = message;
		this.detailedMessage = detailedMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
