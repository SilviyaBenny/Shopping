package com.shopping.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopping.exception.ErrorCode;
import com.shopping.exception.ErrorType;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ShoppingError implements Serializable {

	private ErrorCode errorCode = null;

	private ErrorType type = null;

	private String message = null;

	private String detailedMessage = null;

	public ShoppingError(ErrorCode errorCode, ErrorType type, String message) {

		this.errorCode = errorCode;
		this.type = type;
		this.message = message;
	}

	public ShoppingError(ErrorCode errorCode, ErrorType type, String message, String detailedMessage) {

		this.errorCode = errorCode;
		this.type = type;
		this.message = message;

		this.detailedMessage = detailedMessage;
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
