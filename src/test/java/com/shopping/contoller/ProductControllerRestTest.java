package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.shopping.controller.DepartmentController;
import com.shopping.controller.ProductController;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.responsejson.DepartmentResponseJson;
import com.shopping.responsejson.ProductResponseJson;

public class ProductControllerRestTest extends TestBase {

	@Autowired
	ProductController productController;
	@Autowired
	DepartmentController departmentController;

	@Test
	@Order(1)
	public void createTest() {
		Date date = new Date();
		ProductRequestJson productRequestJson = new ProductRequestJson();
		productRequestJson.setName("Box");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		ResponseEntity<List<DepartmentResponseJson>> depResp = departmentController.getAll();
		assertNotNull(depResp);
		assertNotNull(depResp.getBody());
		productRequestJson.setDepartmentId(depResp.getBody().get(0).getId());
		productRequestJson.setDescription("Stationery Items");
		productRequestJson.setCreatedBy("Jill");
		productRequestJson.setCreatedDate(date);
		productRequestJson.setModifiedBy("testUser");
		productRequestJson.setModifiedDate(date);

		ResponseEntity<ProductResponseJson> resp = productController.create(productRequestJson);
		assertNotNull(resp);

	}

	@Test
	@Order(2)
	public void getAllTest() {

		ResponseEntity<List<ProductResponseJson>> resp = productController.getAll();
		assertNotNull(resp);
	}

	@Test
	@Order(3)
	public void getByIdTest() {

		ResponseEntity<ProductResponseJson> resp = productController.getById(3);
		assertNotNull(resp);
	}

	@Test
	@Order(4)
	public void updateTest() {
		Date date = new Date();
		ProductRequestJson productRequestJson = new ProductRequestJson();
		productRequestJson.setName("Highlighters");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		ResponseEntity<List<DepartmentResponseJson>> depResp = departmentController.getAll();
		productRequestJson.setDepartmentId(depResp.getBody().get(0).getId());
		productRequestJson.setDescription("Stationery Items");
		productRequestJson.setCreatedBy("Jill");
		productRequestJson.setCreatedDate(date);
		productRequestJson.setModifiedBy("testUser");
		productRequestJson.setModifiedDate(date);

		ResponseEntity<ProductResponseJson> resp = productController.update(3, productRequestJson);
		assertNotNull(resp);
	}

	@Test
	@Order(5)
	public void deleteByIdTest() {

		ResponseEntity<Void> resp = productController.deleteById(3);
		assertNotNull(resp);
	}

}
