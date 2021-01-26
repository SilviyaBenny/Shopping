package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

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

		Date date = new Date();
		ProductRequestJson productRequestJson = new ProductRequestJson();
		productRequestJson.setName("Book");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(20);
		productRequestJson.setDescription("Stationery Items");
		productRequestJson.setCreatedBy("Jill");
		productRequestJson.setCreatedDate(date);
		productRequestJson.setModifiedBy("testUser");
		productRequestJson.setModifiedDate(date);

		ResponseEntity<ProductResponseJson> resp = productController.create(productRequestJson);
		assertResponse(productRequestJson, resp.getBody());

		String createdId = resp.getBody().getRecordId();

		resp = productController.getById(createdId);
		assertResponse(productRequestJson, resp.getBody());

		productRequestJson.setName("Pen");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(20);
		productRequestJson.setDescription("Stationery Items");
		productRequestJson.setModifiedBy("Jill");
		productRequestJson.setModifiedDate(date);

		resp = productController.update(createdId, productRequestJson);
		assertResponse(productRequestJson, resp.getBody());

		ResponseEntity<Void> deleteResp = productController.deleteById(createdId);
		assertEquals(HttpStatus.NO_CONTENT, deleteResp.getStatusCode());
		assertResponse(productRequestJson, resp.getBody());
		
		try {
			resp = productController.getById(createdId);
		} catch (ItemNotFoundException e) {

		}

	}

	private void assertResponse(ProductRequestJson requestJson, ProductResponseJson responseJson) {
		assertNotNull(requestJson);
		assertNotNull(responseJson);
		assertEquals(requestJson.getName(), responseJson.getName());
		assertEquals(requestJson.getQuantity(), responseJson.getQuantity());
		assertEquals(requestJson.getPrice(), responseJson.getPrice());
		assertEquals(requestJson.getSku(), responseJson.getSku());
		assertEquals(requestJson.getDepartmentId(), responseJson.getDepartmentId());
		assertEquals(requestJson.getDescription(), responseJson.getDescription());
		assertEquals(requestJson.getCreatedBy(), responseJson.getCreatedBy());
		assertEquals(requestJson.getModifiedBy(), responseJson.getModifiedBy());

	}

}
