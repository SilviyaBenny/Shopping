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

import com.shopping.requestjson.DepartmentRequestJson;
import com.shopping.responsejson.DepartmentResponseJson;
/**
 * DepartmentController class
 * 
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/department")
public interface IDepartmentController {

	/**
	 * Create Department
	 * 
	 * 
	 */
	@PostMapping()
	public ResponseEntity<DepartmentResponseJson> create(@RequestBody DepartmentRequestJson requestJson);

	/**
	 * GetAll Department
	 * 
	 * 
	 */
	@GetMapping()
	public ResponseEntity<List<DepartmentResponseJson>> getAll();

	/**
	 * Get Department ById 
	 * 
	 * 
	 */
	@GetMapping("{id}")
	public ResponseEntity<DepartmentResponseJson> getById(@PathVariable("id") int id);

	/**
	 * Update Department
	 * 
	 * 
	 */
	@PutMapping("{id}")
	public ResponseEntity<DepartmentResponseJson> update(@PathVariable("id") int id,
			@RequestBody DepartmentRequestJson requestJson);

	/**
	 * Delete Department
	 * 
	 * 
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id);
}
