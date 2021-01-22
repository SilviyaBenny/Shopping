package com.shopping.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.requestjson.DepartmentRequestJson;
import com.shopping.responsejson.DepartmentResponseJson;

@RequestMapping("/department")
public interface IDepartmentController {

	@PostMapping()
	public ResponseEntity<DepartmentResponseJson> create(@RequestBody DepartmentRequestJson requestJson);
	@GetMapping()
	public ResponseEntity<List<DepartmentResponseJson>> getAll();
	@GetMapping("{recordId}")
	public ResponseEntity<DepartmentResponseJson> getById(@PathVariable("recordId") String recordId);
	@PutMapping("{recordId}")
	public ResponseEntity<DepartmentResponseJson> update(@PathVariable("recordId") String recordId,
			@RequestBody DepartmentRequestJson requestJson);
	@DeleteMapping("{recordId}")
	public ResponseEntity<Void> deleteById(@PathVariable("recordId") String recordId);
}
