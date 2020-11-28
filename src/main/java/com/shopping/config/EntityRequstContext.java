package com.shopping.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.shopping.error.ShoppingError;

@Service
@Scope("request")
public class EntityRequstContext {
	
	private List<ShoppingError> errors = new ArrayList<>();

	public List<ShoppingError> getErrors() {
		return errors;
	}

	public void addError(ShoppingError errors) {
		this.errors.add(errors);
	}
	
}
