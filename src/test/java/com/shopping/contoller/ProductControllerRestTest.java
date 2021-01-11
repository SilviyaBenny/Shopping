package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.shopping.controller.ProductController;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.responsejson.ProductResponseJson;

public class ProductControllerRestTest extends TestBase {

	@Autowired
	ProductController productController;
	
	@Test
	@Order(1)
	public void createTest() {

		ProductRequestJson productRequestJson = new ProductRequestJson();
		productRequestJson.setName("Book");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(10);
		productRequestJson.setDescription("Stationery Items");

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

		ResponseEntity<ProductResponseJson> resp = productController.getById("ytu5683746etfayufgya");
		assertNotNull(resp);
	}

	@Test
	@Order(4)
	public void updateTest() {
		
		ProductRequestJson productRequestJson = new ProductRequestJson();
		productRequestJson.setName("Pen");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(10);
		productRequestJson.setDescription("Stationery Items");

		ResponseEntity<ProductResponseJson> resp = productController.update("ytu5683746etfayufgya", productRequestJson);
		assertNotNull(resp);
	}

	@Test
	@Order(5)
	public void deleteByIdTest() {

		ResponseEntity<Void> resp = productController.deleteById("ytu5683746etfayufgya");
		assertNotNull(resp);
	}

}
