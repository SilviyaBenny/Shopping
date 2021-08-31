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

import com.shopping.bo.ProductBO;
import com.shopping.boservices.ProductBOServices;
import com.shopping.controller.validator.ProductRequestValidator;
import com.shopping.mapper.botoresponse.ProductBOtoResponseJsonMapper;
import com.shopping.mapper.requesttobo.ProductRequestJsonToBOMapper;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.responsejson.ProductResponseJson;

/**
 * ProductController class
 * 
 *
 */
@RestController

public class ProductController implements IProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductRequestValidator validator;
	@Autowired
	private ProductBOServices productBoService;
	@Autowired
	private ProductRequestJsonToBOMapper jsontoBO;
	@Autowired
	private ProductBOtoResponseJsonMapper respJsonMapper;

	@Override
	public ResponseEntity<ProductResponseJson> create(ProductRequestJson requestJson) {

		validator.validateProductRequest(requestJson);
		LOGGER.info("Incoming Request" + requestJson);
		ProductBO bo = jsontoBO.mapObject(requestJson);
		ProductBO respBo = productBoService.create(bo);
		ProductResponseJson responseJson = respJsonMapper.mapObject(respBo);
		LOGGER.info("Outgoing Response " + responseJson);
		return new ResponseEntity<ProductResponseJson>(responseJson, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductResponseJson>> getAll() {

		LOGGER.info("Incoming Request");
		List<ProductBO> boList = productBoService.getAll();
		List<ProductResponseJson> respJsonList = new ArrayList<>();
		for (ProductBO productBO : boList) {
			ProductResponseJson responseJson = respJsonMapper.mapObject(productBO);
			respJsonList.add(responseJson);
		}
		LOGGER.info("Outgoing Response " + respJsonList);
		return ResponseEntity.status(HttpStatus.OK).body(respJsonList);
	}

	@Override
	public ResponseEntity<ProductResponseJson> getById(int id) {

		LOGGER.info("Incoming Request" + id);
		ProductBO respBo = productBoService.getById(id);
		ProductResponseJson responseJson = respJsonMapper.mapObject(respBo);
		LOGGER.info("Outgoing Response " + responseJson);
		return ResponseEntity.status(HttpStatus.OK).body(responseJson);
	}

	@Override
	public ResponseEntity<ProductResponseJson> update(int id, @RequestBody ProductRequestJson requestJson) {

		validator.validateProductRequest(requestJson);
		LOGGER.info("Incoming Request" + id);
		ProductBO bo = jsontoBO.mapObject(requestJson);
		ProductBO respBo = productBoService.update(bo, id);
		ProductResponseJson responseJson = respJsonMapper.mapObject(respBo);
		LOGGER.info("Outgoing Response " + responseJson);
		return new ResponseEntity<ProductResponseJson>(responseJson, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> deleteById(int id) {

		LOGGER.info("Incoming Request" + id);
		int numberofRecords = productBoService.deleteById(id);
		LOGGER.info("Outgoing Response" + numberofRecords);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
