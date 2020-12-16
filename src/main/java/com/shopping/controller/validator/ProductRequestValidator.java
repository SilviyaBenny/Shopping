package com.shopping.controller.validator;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.config.EntityRequstContext;
import com.shopping.error.ShoppingError;
import com.shopping.exception.ErrorCode;
import com.shopping.exception.ErrorType;
import com.shopping.exception.ValidationException;
import com.shopping.requestjson.ProductRequestJson;

@Component
public class ProductRequestValidator {
	
	@Autowired
	private Provider<EntityRequstContext> entityRequstContextProvider;
	
	public void validateProductRequest(ProductRequestJson requestJson) {
		
		EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
		
			
		if (requestJson.getName() == null || requestJson.getName().trim().length() == 0) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Name should not be null or empty"));
		}
		if(requestJson.getName().length() > 30) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Name length should be less than 30"));
		}
		if(requestJson.getQuantity() == 0 ) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Quantity should not be null"));
		}
		if(requestJson.getPrice() == 0) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Price should not be null"));
		}
		if (requestJson.getSku() == null || requestJson.getSku().trim().length() == 0) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"SKU should not be null or empty"));
		} 
		if (requestJson.getSku().length() > 30) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION, "SKU Length should be less than 30"));
		} 
		if (requestJson.getDescription().length() > 250) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION, "Description Length should be less than 30"));
		} 
		if (!entityRequstContext.getErrors().isEmpty()) {
			throw new ValidationException(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Validation of input field");
		}
		
	}
	public void validateProductUpdate(ProductRequestJson requestJson) {
		
		EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
		
		if (requestJson.getName() == null || requestJson.getName().trim().length() == 0) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Name should not be null or empty"));
		}
		if(requestJson.getName().length() > 30) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Name length should be less than 30"));
		}
		if(requestJson.getQuantity() == 0 ) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Quantity should not be null"));
		}
		if(requestJson.getPrice() == 0) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Price should not be null"));
		}
		if (requestJson.getSku() == null || requestJson.getSku().trim().length() == 0) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"SKU should not be null or empty"));
		} 
		if (requestJson.getSku().length() > 30) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION, "SKU Length should be less than 30"));
		} 
		if (requestJson.getDescription().length() > 250) {
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION, "Description Length should be less than 30"));
		} 
		if (!entityRequstContext.getErrors().isEmpty()) {
			throw new ValidationException(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Validation of input field");
		}
		
	}
	
}
