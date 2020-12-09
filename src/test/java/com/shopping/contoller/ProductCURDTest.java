package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.shopping.BOtoResponse.mapper.ProductBOtoResponseJsonMapper;
import com.shopping.boservices.ProductBOServices;
import com.shopping.controller.ProductController;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.requesttobomapper.ProductRequestJsonToBOMapper;
import com.shopping.responsejson.ProductResponseJson;

public class ProductCURDTest extends TestBase {
	@Autowired
	ProductController productController;
	@Autowired
	ProductBOServices productBoService;
	@Autowired
	ProductRequestJsonToBOMapper jsontoBO;
	@Autowired
	ProductBOtoResponseJsonMapper respJsonMapper;

	@Test

	public void test() {

		ProductRequestJson productRequestJson = new ProductRequestJson();
		productRequestJson.setName("Book");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(20);

		ResponseEntity<ProductResponseJson> resp = productController.create(productRequestJson);
		assertRequest(productRequestJson, resp.getBody());
		
	    int createdId = resp.getBody().getId();
		
		resp = productController.getById(createdId);
		assertRequest(productRequestJson, resp.getBody());

		
		productRequestJson.setName("Pen");
		productRequestJson.setQuantity(90);
		productRequestJson.setPrice(100);
		productRequestJson.setSku("00B");
		productRequestJson.setDepartmentId(20);

		resp = productController.update(createdId, productRequestJson);
		assertRequest(productRequestJson, resp.getBody());

		productController.deleteById(createdId);
		assertRequest(productRequestJson, resp.getBody());

		try {
			resp = productController.getById(createdId);
		}catch (ItemNotFoundException e) {
			
		}
	}

	private void assertRequest(ProductRequestJson productRequestJson, ProductResponseJson resp) {
		assertNotNull(productRequestJson);
		assertNotNull(resp);
		assertEquals(productRequestJson.getName(), resp.getName());
		assertEquals(productRequestJson.getQuantity(), resp.getQuantity());
		assertEquals(productRequestJson.getPrice(), resp.getPrice());
		assertEquals(productRequestJson.getSku(), resp.getSku());
		assertEquals(productRequestJson.getDepartmentId(), resp.getDepartmentId());

	}

}
