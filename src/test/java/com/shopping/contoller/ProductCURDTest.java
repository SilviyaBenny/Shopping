package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shopping.controller.DepartmentController;
import com.shopping.controller.ProductController;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.responsejson.DepartmentResponseJson;
import com.shopping.responsejson.ProductResponseJson;

public class ProductCURDTest extends TestBase {
	@Autowired
	ProductController productController;
	@Autowired
	DepartmentController departmentController;

	@Test
	public void test() {
		Date date = new Date();
		ProductRequestJson productRequestJson = new ProductRequestJson();

		productRequestJson.setName("Sharpner");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		ResponseEntity<List<DepartmentResponseJson>> depResp = departmentController.getAll();
		assertNotNull(depResp);
		assertNotNull(depResp.getBody());
		productRequestJson.setDepartmentId(depResp.getBody().get(0).getId());
		productRequestJson.setDescription("Stationary");
		productRequestJson.setCreatedBy("Jill");
		productRequestJson.setCreatedDate(date);
		productRequestJson.setModifiedBy("testUser");
		productRequestJson.setModifiedDate(date);

		ResponseEntity<ProductResponseJson> resp = productController.create(productRequestJson);
		assertResponse(productRequestJson, resp.getBody());

		int createdId = resp.getBody().getId();

		resp = productController.getById(createdId);
		assertResponse(productRequestJson, resp.getBody());

		productRequestJson.setName("Eraser");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(depResp.getBody().get(1).getId());
		productRequestJson.setDescription("Stationary");
		productRequestJson.setCreatedBy("Jill");
		productRequestJson.setCreatedDate(date);
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
