package com.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.bo.DepartmentBO;
import com.shopping.boservices.DepartmentBOServices;
import com.shopping.controller.validator.DepartmentRequestValidator;
import com.shopping.mapper.botoresponse.DepartmentBOtoResponseJsonMapper;
import com.shopping.mapper.requesttobo.DepartmentRequestJsonToBOMapper;
import com.shopping.requestjson.DepartmentRequestJson;
import com.shopping.responsejson.DepartmentResponseJson;
/**
 * DepartmentController class
 * 
 *
 */
@RestController
public class DepartmentController implements IDepartmentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentRequestValidator validator;
	@Autowired
	private DepartmentBOServices departmentBOServices;
	@Autowired
	private DepartmentRequestJsonToBOMapper requestJsonToBOMapper;
	@Autowired
	private DepartmentBOtoResponseJsonMapper botoResponseMapper;

	@Override
	public ResponseEntity<DepartmentResponseJson> create(DepartmentRequestJson requestJson) {

		validator.validateDepartmentRequest(requestJson);
		LOGGER.info("Incoming request " + requestJson);
		DepartmentBO bo = requestJsonToBOMapper.mapObject(requestJson);
		DepartmentBO respBO = departmentBOServices.create(bo);
		DepartmentResponseJson respJson = botoResponseMapper.mapObject(respBO);
		LOGGER.info("Outgoing Response " + respJson);
		return new ResponseEntity<DepartmentResponseJson>(respJson, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<DepartmentResponseJson>> getAll() {

		LOGGER.info("Incoming request ");
		List<DepartmentBO> boList = departmentBOServices.getAll();
		List<DepartmentResponseJson> respJsonList = new ArrayList<>();
		for (DepartmentBO departmentBO : boList) {
			DepartmentResponseJson respJson = botoResponseMapper.mapObject(departmentBO);
			respJsonList.add(respJson);
		}
		LOGGER.info("Outgoing Response " + respJsonList);
		return ResponseEntity.status(HttpStatus.OK).body(respJsonList);
	}

	@Override
	public ResponseEntity<DepartmentResponseJson> getById(int id) {

		LOGGER.info("Incoming request " + id);
		DepartmentBO respBO = departmentBOServices.getById(id);
		DepartmentResponseJson respJson = botoResponseMapper.mapObject(respBO);
		LOGGER.info("Outgoing Response " + respJson);
		return ResponseEntity.status(HttpStatus.OK).body(respJson);
	}

	@Override
	public ResponseEntity<DepartmentResponseJson> update(int id, @RequestBody DepartmentRequestJson requestJson) {

		validator.validateDepartmentRequest(requestJson);
		LOGGER.info("Incoming request " + id);
		DepartmentBO departmentBO = requestJsonToBOMapper.mapObject(requestJson);
		DepartmentBO respBO = departmentBOServices.update(departmentBO, id);
		DepartmentResponseJson respJson = botoResponseMapper.mapObject(respBO);
		LOGGER.info("Outgoing Response " + respJson);
		return new ResponseEntity<DepartmentResponseJson>(respJson, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> deleteById(int id) {

		LOGGER.info("Incoming Request" + id);
		int numberofRecords = departmentBOServices.deleteById(id);
		LOGGER.info("Outgoing Response" + numberofRecords);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
