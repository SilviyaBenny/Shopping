package com.shopping.controller.validator;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.config.EntityRequstContext;
import com.shopping.error.ShoppingError;
import com.shopping.exception.ErrorCode;
import com.shopping.exception.ErrorType;
import com.shopping.exception.ValidationException;
import com.shopping.requestjson.DepartmentRequestJson;

@Component
public class DepartmentRequestValidator {

	@Autowired
	private Provider<EntityRequstContext> entityRequstContextProvider;

	public void validateDepartmentRequest(DepartmentRequestJson requestJson) {
		EntityRequstContext entityRequstContext = entityRequstContextProvider.get();

		if (requestJson.getDepartmentName() == null || requestJson.getDepartmentName().trim().length() == 0) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"DepartmentName should not be null or empty"));
		}
		if (requestJson.getDepartmentName().length() > 30) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"DepartmentName length should not be greater than 30"));
		}
		if (requestJson.getCreatedBy() == null || requestJson.getCreatedBy().trim().length() == 0) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"CreatedBy should not be null or empty"));
		}
		if (requestJson.getCreatedBy().length() > 30) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"CreatedBy Length should be less than 30"));
		}
		if (requestJson.getModifiedBy() == null || requestJson.getModifiedBy().trim().length() == 0) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"ModifiedBy should not be null or empty"));
		}
		if (requestJson.getModifiedBy().length() > 30) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"ModifiedBy Length should be less than 30"));
		}
		if (!entityRequstContext.getErrors().isEmpty()) {
			throw new ValidationException(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"Validation of input field");
		}

	}

}
