package com.shopping.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends ShoppingException {

	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public ValidationException(ErrorCode errorCode, ErrorType type, String message) {
		super(message);
		this.errorCode = errorCode;
		this.type = type;
		this.message = message;
	}

	public ValidationException(ErrorCode errorCode, ErrorType type, String message, String detailedMessage) {
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
