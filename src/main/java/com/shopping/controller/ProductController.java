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

import com.shopping.BO.ProductBO;
import com.shopping.BOservices.ProductBOServices;
import com.shopping.BOtoResponse.mapper.ProductBOtoResponseJsonMapper;
import com.shopping.RequesttoBO.mapper.ProductRequestJsonToBOMapper;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.responsejson.ProductResponseJson;

@RestController
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductBOServices productBoService;

	@PostMapping()
	public ResponseEntity<ProductResponseJson> create(@RequestBody ProductRequestJson requestJson) {
		logger.info("Incoming Request" + requestJson);
		ProductRequestJsonToBOMapper jsontoBO = new ProductRequestJsonToBOMapper();
		ProductBO bo = jsontoBO.mapObject(requestJson);
		ProductBO respBo = productBoService.create(bo);
		ProductBOtoResponseJsonMapper respJsonMapper = new ProductBOtoResponseJsonMapper();
		ProductResponseJson responseJson = respJsonMapper.mapObject(respBo);
		logger.info("Outgoing Response " + responseJson);
		return new ResponseEntity<ProductResponseJson>(responseJson, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<ProductResponseJson>> getAll() {
		logger.info("Incoming Request");
		List<ProductBO> boList = productBoService.getAll();
		ProductBOtoResponseJsonMapper respJsonMapper = new ProductBOtoResponseJsonMapper();
		List<ProductResponseJson> respJsonList = new ArrayList<>();
		for (ProductBO productBO : boList) {
			ProductResponseJson responseJson = respJsonMapper.mapObject(productBO);
			respJsonList.add(responseJson);
		}
		logger.info("Outgoing Response " + respJsonList);
		return ResponseEntity.status(HttpStatus.OK).body(respJsonList);
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductResponseJson> getById(@PathVariable("id") int id) {
		logger.info("Incoming Request" + id);
		ProductBO respBo = productBoService.getById(id);
		ProductBOtoResponseJsonMapper respJsonMapper = new ProductBOtoResponseJsonMapper();
		ProductResponseJson responseJson = respJsonMapper.mapObject(respBo);
		logger.info("Outgoing Response " + responseJson);
		return ResponseEntity.status(HttpStatus.OK).body(responseJson);
	}

	@PutMapping("{id}")
	public ResponseEntity<ProductResponseJson> update(@PathVariable("id") int id,
			@RequestBody ProductRequestJson requestJson) {

		logger.info("Incoming Request" + id);
		ProductRequestJsonToBOMapper jsontoBO = new ProductRequestJsonToBOMapper();
		ProductBO bo = jsontoBO.mapObject(requestJson);
		ProductBO respBo = productBoService.update(bo, id);
		ProductBOtoResponseJsonMapper respJsonMapper = new ProductBOtoResponseJsonMapper();
		ProductResponseJson responseJson = respJsonMapper.mapObject(respBo);
		logger.info("Outgoing Response " + responseJson);
		return new ResponseEntity<ProductResponseJson>(responseJson, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
		logger.info("Incoming Request" + id);
		int numberofRecords = productBoService.deleteById(id);
		logger.info("Outgoing Response" + numberofRecords);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
