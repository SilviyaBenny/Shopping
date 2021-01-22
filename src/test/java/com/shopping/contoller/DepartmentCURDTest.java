package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
		DepartmentRequestJson departmentRequestJson = new DepartmentRequestJson();
		departmentRequestJson.setDepartmentName("Home");
		departmentRequestJson.setDescription("Home Appliances");

		ResponseEntity<DepartmentResponseJson> resp = departmentController.create(departmentRequestJson);
		assertRequest(departmentRequestJson, resp.getBody());

		String createdId = resp.getBody().getRecordId();

		resp = departmentController.getById(createdId);
		assertRequest(departmentRequestJson, resp.getBody());

		departmentRequestJson.setDepartmentName("Garden");
		departmentRequestJson.setDescription("Home Appliances");

		resp = departmentController.update(createdId, departmentRequestJson);
		assertRequest(departmentRequestJson, resp.getBody());

		ResponseEntity<Void> deleteResp = departmentController.deleteById(createdId);
		assertEquals(HttpStatus.NO_CONTENT, deleteResp.getStatusCode());

		try {
			resp = departmentController.getById(createdId);
		} catch (ItemNotFoundException e) {

		}

	}

	private void assertRequest(DepartmentRequestJson departmentRequestJson, DepartmentResponseJson resp) {
		assertNotNull(departmentRequestJson);
		assertNotNull(resp);
		assertEquals(departmentRequestJson.getDepartmentName(), resp.getDepartmentName());
		assertEquals(departmentRequestJson.getDescription(), resp.getDescription());

	}
}
