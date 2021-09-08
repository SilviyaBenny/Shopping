package com.shopping.controller.resttest;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.shopping.base.TestBase;
import com.shopping.controller.DepartmentController;
import com.shopping.requestjson.DepartmentRequestJson;
import com.shopping.responsejson.DepartmentResponseJson;

public class DepartmentControllerRestTest extends TestBase {

	@Autowired
	private DepartmentController departmentController;

	@Test
	@Order(1)
	public void createTest() {
		Date date = new Date();
		DepartmentRequestJson departmentRequestJson = new DepartmentRequestJson();
		departmentRequestJson.setDepartmentName("Home");
		departmentRequestJson.setDescription("Home Appliances");
		departmentRequestJson.setCreatedBy("Jill");
		departmentRequestJson.setCreatedDate(date);
		departmentRequestJson.setModifiedBy("testUser");
		departmentRequestJson.setModifiedDate(date);

		ResponseEntity<DepartmentResponseJson> resp = departmentController.create(departmentRequestJson);
		assertNotNull(resp);
	}

	@Test
	@Order(2)
	public void getAlltest() {
		ResponseEntity<List<DepartmentResponseJson>> resp = departmentController.getAll();
		assertNotNull(resp);
	}

	@Test
	@Order(3)
	public void getById() {
		ResponseEntity<DepartmentResponseJson> resp = departmentController.getById(2);
		assertNotNull(resp);
	}

	@Test
	@Order(4)
	public void updateTest() {
		Date date = new Date();
		DepartmentRequestJson departmentRequestJson = new DepartmentRequestJson();
		departmentRequestJson.setDepartmentName("Garden");
		departmentRequestJson.setDescription("Gardening Area");
		departmentRequestJson.setCreatedBy("Jill");
		departmentRequestJson.setModifiedBy("testUser");
		departmentRequestJson.setModifiedDate(date);

		ResponseEntity<DepartmentResponseJson> resp = departmentController.update(2, departmentRequestJson);
		assertNotNull(resp);
	}

	@Test
	@Order(5)
	public void deleteByIdTest() {
		ResponseEntity<Void> resp = departmentController.deleteById(2);
		assertNotNull(resp);
	}
}
