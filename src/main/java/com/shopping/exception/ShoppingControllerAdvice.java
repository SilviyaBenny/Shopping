package com.shopping.exception;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shopping.config.EntityRequstContext;

@ControllerAdvice
public class ShoppingControllerAdvice {
	
	@Autowired
	private Provider<EntityRequstContext> entityRequstContextProvider;
	
	@ExceptionHandler(value = ItemNotFoundException.class)	
	public ResponseEntity<Object> exception(ItemNotFoundException infe){
		
		ShoppingErrorResp errorResp = new ShoppingErrorResp();
		EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
		
		if(entityRequstContext.getErrors() != null && !entityRequstContext.getErrors().isEmpty()) {
			errorResp.setErrorDetails(entityRequstContext.getErrors());
		}
		
		errorResp.setErrorCode(infe.getErrorCode());
		errorResp.setType(infe.getType());
		errorResp.setMessage(infe.getMessage());
		
		return ResponseEntity.status(infe.getHttpStatus()).body(errorResp);
		
	}

}
