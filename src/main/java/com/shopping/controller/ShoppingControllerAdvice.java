package com.shopping.controller;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shopping.config.EntityRequstContext;
import com.shopping.exception.ShoppingErrorResp;
import com.shopping.exception.ShoppingException;

@ControllerAdvice
public class ShoppingControllerAdvice {
	
	@Autowired
	private Provider<EntityRequstContext> entityRequstContextProvider;
	
	@ExceptionHandler(value = ShoppingException.class)	
	public ResponseEntity<Object> exception(ShoppingException shopexcep){
		
		ShoppingErrorResp errorResp = new ShoppingErrorResp();
		EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
		
		if(entityRequstContext.getErrors() != null && !entityRequstContext.getErrors().isEmpty()) {
			errorResp.setErrorDetails(entityRequstContext.getErrors());
		}
		
		errorResp.setErrorCode(shopexcep.getErrorCode());
		errorResp.setType(shopexcep.getType());
		errorResp.setMessage(shopexcep.getMessage());
		
		return ResponseEntity.status(shopexcep.getHttpStatus()).body(errorResp);
		
	}

}
