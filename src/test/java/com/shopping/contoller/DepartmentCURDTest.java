package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shopping.controller.DepartmentController;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.requestjson.DepartmentRequestJson;
import com.shopping.responsejson.DepartmentResponseJson;

public class DepartmentCURDTest extends TestBase {

	@Autowired
	private DepartmentController departmentController;

	@Test
	public void test() {
		Date date = new Date();
		DepartmentRequestJson departmentRequestJson = new DepartmentRequestJson();
		departmentRequestJson.setDepartmentName("Home");
		departmentRequestJson.setDescription("Home Appliances");
		departmentRequestJson.setCreatedBy("Jill");
		departmentRequestJson.setCreatedDate(date);
		departmentRequestJson.setModifiedBy("testUser");
		departmentRequestJson.setModifiedDate(date);


		ResponseEntity<DepartmentResponseJson> resp = departmentController.create(departmentRequestJson);
		assertRequest(departmentRequestJson, resp.getBody());

		int createdId = resp.getBody().getId();

		resp = departmentController.getById(createdId);
		assertRequest(departmentRequestJson, resp.getBody());

		departmentRequestJson.setDepartmentName("Garden");
		departmentRequestJson.setDescription("Home Appliances");
		departmentRequestJson.setModifiedBy("Jill");
		departmentRequestJson.setModifiedDate(date);

		resp = departmentController.update(createdId, departmentRequestJson);
		assertRequest(departmentRequestJson, resp.getBody());

		ResponseEntity<Void> deleteResp = departmentController.deleteById(createdId);
		assertEquals(HttpStatus.NO_CONTENT, deleteResp.getStatusCode());

		try {
			resp = departmentController.getById(createdId);
		} catch (ItemNotFoundException e) {

		}

	}

	private void assertRequest(DepartmentRequestJson requestJson, DepartmentResponseJson responseJson) {
		assertNotNull(requestJson);
		assertNotNull(responseJson);
		assertEquals(requestJson.getDepartmentName(), responseJson.getDepartmentName());
		assertEquals(requestJson.getDescription(), responseJson.getDescription());
		assertEquals(requestJson.getCreatedBy(), responseJson.getCreatedBy());
		assertEquals(requestJson.getModifiedBy(), responseJson.getModifiedBy());


	}
}
