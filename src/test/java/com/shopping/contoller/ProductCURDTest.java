package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shopping.controller.ProductController;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.responsejson.ProductResponseJson;

public class ProductCURDTest extends TestBase {
	@Autowired
	ProductController productController;

	@Test
	public void test() {

		ProductRequestJson productRequestJson = new ProductRequestJson();
		productRequestJson.setName("Book");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(20);
		productRequestJson.setDescription("Stationery Items");

		ResponseEntity<ProductResponseJson> resp = productController.create(productRequestJson);
		assertResponse(productRequestJson, resp.getBody());

		int createdId = resp.getBody().getId();

		resp = productController.getById(createdId);
		assertResponse(productRequestJson, resp.getBody());

		productRequestJson.setName("Pen");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(20);
		productRequestJson.setDescription("Stationery Items");

		resp = productController.update(createdId, productRequestJson);
		assertResponse(productRequestJson, resp.getBody());

		ResponseEntity<Void> deleteRsp = productController.deleteById(createdId);
		assertEquals(HttpStatus.NO_CONTENT, deleteRsp.getStatusCode());
		assertResponse(productRequestJson, resp.getBody());

		try {
			resp = productController.getById(createdId);
		} catch (ItemNotFoundException e) {

		}
	}

	private void assertResponse(ProductRequestJson productRequestJson, ProductResponseJson resp) {
		assertNotNull(productRequestJson);
		assertNotNull(resp);
		assertEquals(productRequestJson.getName(), resp.getName());
		assertEquals(productRequestJson.getQuantity(), resp.getQuantity());
		assertEquals(productRequestJson.getPrice(), resp.getPrice());
		assertEquals(productRequestJson.getSku(), resp.getSku());
		assertEquals(productRequestJson.getDepartmentId(), resp.getDepartmentId());
		assertEquals(productRequestJson.getDescription(), resp.getDescription());

	}

}
