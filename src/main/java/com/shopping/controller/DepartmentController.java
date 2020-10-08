package com.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.bo.DepartmentBO;
import com.shopping.boservices.DepartmentBOServices;
import com.shopping.botoresponse.mapper.DepartmentBOtoResponseJsonMapper;
import com.shopping.requestjson.DepartmentRequestJson;
import com.shopping.requesttobo.mapper.DepartmentRequestJsonToBOMapper;
import com.shopping.responsejson.DepartmentResponseJson;

@RequestMapping("/department")
@RestController
public class DepartmentController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private DepartmentBOServices departmentBOServices;
	@Autowired
	private DepartmentRequestJsonToBOMapper requestJsonToBOMapper;
	@Autowired
	private DepartmentBOtoResponseJsonMapper botoResponseMapper;

	@PostMapping()
	public ResponseEntity<DepartmentResponseJson> create(@RequestBody DepartmentRequestJson requestJson) {
		logger.info("Incoming request " + requestJson);
		DepartmentBO bo = requestJsonToBOMapper.mapObject(requestJson);
		DepartmentBO respBO = departmentBOServices.create(bo);
		DepartmentResponseJson respJson = botoResponseMapper.mapObject(respBO);
		logger.info("Outgoing Response " + respJson);
		return new ResponseEntity<DepartmentResponseJson>(respJson, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<DepartmentResponseJson>> getAll() {
		logger.info("Incoming request ");
		List<DepartmentBO> boList = departmentBOServices.getAll();
		List<DepartmentResponseJson> respJsonList = new ArrayList<>();
		for (DepartmentBO departmentBO : boList) {
			DepartmentResponseJson respJson = botoResponseMapper.mapObject(departmentBO);
			respJsonList.add(respJson);
		}
		logger.info("Outgoing Response " + respJsonList);
		return ResponseEntity.status(HttpStatus.OK).body(respJsonList);
	}

	@GetMapping("{id}")
	public ResponseEntity<DepartmentResponseJson> getById(@PathVariable("id") int id) {
		logger.info("Incoming request " + id);
		DepartmentBO respBO = departmentBOServices.getById(id);
		DepartmentResponseJson respJson = botoResponseMapper.mapObject(respBO);
		logger.info("Outgoing Response " + respJson);
		return ResponseEntity.status(HttpStatus.OK).body(respJson);
	}

	@PutMapping("{id}")
	public ResponseEntity<DepartmentResponseJson> update(@PathVariable("id") int id,
			@RequestBody DepartmentRequestJson requestJson) {
		logger.info("Incoming request " + requestJson);
		DepartmentBO departmentBO = requestJsonToBOMapper.mapObject(requestJson);
		DepartmentBO respBO = departmentBOServices.update(departmentBO, id);
		DepartmentResponseJson respJson = botoResponseMapper.mapObject(respBO);
		logger.info("Outgoing Response " + respJson);
		return new ResponseEntity<DepartmentResponseJson>(respJson, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
		logger.info("Incoming Request" + id);
		int numberofRecords = departmentBOServices.deleteById(id);
		logger.info("Outgoing Response" + numberofRecords);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
