package com.shopping.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.requestjson.ProductRequestJson;
import com.shopping.responsejson.ProductResponseJson;
/**
 * ProductController class
 * 
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public interface IProductController {

	/**
	 * Create product
	 * 
	 * 
	 */
	@PostMapping()
	public ResponseEntity<ProductResponseJson> create(@RequestBody ProductRequestJson requestJson);

	/**
	 * GetAll product
	 * 
	 * 
	 */
	@GetMapping()
	public ResponseEntity<List<ProductResponseJson>> getAll();

	/**
	 * Get product ById
	 * 
	 * 
	 */
	@GetMapping("{id}")
	public ResponseEntity<ProductResponseJson> getById(@PathVariable("id") int id);

	/**
	 * Update product
	 * 
	 * 
	 */
	@PutMapping("{id}")
	public ResponseEntity<ProductResponseJson> update(@PathVariable("id") int id,
			@RequestBody ProductRequestJson requestJson);

	/**
	 * Delete product
	 * 
	 * 
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id);

}
