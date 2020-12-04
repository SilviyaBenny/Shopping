/*package com.shopping.controller.validator;


import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.config.EntityRequstContext;
import com.shopping.error.ShoppingError;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.exception.ShoppingControllerAdvice;
import com.shopping.repository.ProductRepository;
@Component
public class ProductRequestValidator {
	@Autowired
	private ShoppingControllerAdvice shoppingControllerAdvice;
	@Autowired
	private ProductRepository productRepository;

	public void validateProductrequest(int id) {
		
		int id1=0;
		EntityRequstContext entityRequstContext = shoppingControllerAdvice.getById(id1);
		entityRequstContext.addError(new ShoppingError("1000", "Object not found", "VALIDATION"));
		entityRequstContext.addError(new ShoppingError("1001", "Length is out of control", "VALIDATION"));
			throw new ItemNotFoundException("1000", "VALIDATION", "Item with id " + id1 + "not found");
		
	}
}*/
