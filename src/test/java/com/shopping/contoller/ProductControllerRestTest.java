package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.shopping.BOtoResponse.mapper.ProductBOtoResponseJsonMapper;
import com.shopping.boservices.DepartmentBOServices;
import com.shopping.boservices.ProductBOServices;
import com.shopping.controller.DepartmentController;
import com.shopping.controller.ProductController;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.requesttobomapper.ProductRequestJsonToBOMapper;
import com.shopping.responsejson.ProductResponseJson;

public class ProductControllerRestTest extends TestBase {

	@Autowired
	ProductController productController;
	@Autowired
	DepartmentController departmentController;
	@Autowired
	DepartmentBOServices departmentBOServices;

	@Autowired
	ProductBOServices productBoService;
	@Autowired
	ProductRequestJsonToBOMapper jsontoBO;
	@Autowired
	ProductBOtoResponseJsonMapper respJsonMapper;

	@Test
	public void createTest() {

		ProductRequestJson productRequestJson = new ProductRequestJson();
		productRequestJson.setName("Book");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(10);

		ResponseEntity<ProductResponseJson> resp = productController.create(productRequestJson);
		assertNotNull(resp);

	}

	@Test
	public void getByIdTest() {

		ResponseEntity<ProductResponseJson> resp = productController.getById(2);
		assertNotNull(resp);
	}

	@Test
	public void getAllTest() {

		ResponseEntity<List<ProductResponseJson>> resp = productController.getAll();
		assertNotNull(resp);
	}

	@Test
	public void updateTest() {
		int id = 5;
		ProductRequestJson productRequestJson = new ProductRequestJson();
		productRequestJson.setName("Pen");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(10);

		ResponseEntity<ProductResponseJson> resp = productController.update(id, productRequestJson);
		assertNotNull(resp);
	}

	@Test
	public void deleteByIdTest() {

		ResponseEntity<Void> resp = productController.deleteById(5);
		assertNotNull(resp);
	}

}
