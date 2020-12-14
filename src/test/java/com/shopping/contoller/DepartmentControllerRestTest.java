package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.shopping.controller.DepartmentController;
import com.shopping.requestjson.DepartmentRequestJson;
import com.shopping.responsejson.DepartmentResponseJson;

public class DepartmentControllerRestTest extends TestBase{

	@Autowired
	private DepartmentController departmentController;
	
	@Test
	public void createTest() {
		DepartmentRequestJson departmentRequestJson = new DepartmentRequestJson();
		departmentRequestJson.setDepartmentName("Home");
		departmentRequestJson.setDescription("Home Appliances");
		
		ResponseEntity<DepartmentResponseJson> resp = departmentController.create(departmentRequestJson);
		assertNotNull(resp);
	}
	
	@Test
	public void getAlltest() {
		ResponseEntity<List<DepartmentResponseJson>> resp = departmentController.getAll();
		assertNotNull(resp);
	}
	
	@Test
	public void getById() {
		ResponseEntity<DepartmentResponseJson> resp = departmentController.getById(1);
		assertNotNull(resp);
	}
	
	@Test
	public void updateTest() {
		int id = 2;
		DepartmentRequestJson departmentRequestJson = new DepartmentRequestJson();
		departmentRequestJson.setDepartmentName("Garden");
		departmentRequestJson.setDescription("Gardening Area");
		ResponseEntity<DepartmentResponseJson> resp = departmentController.update(id, departmentRequestJson);
		assertNotNull(resp);
	}
	
	@Test
	public void deleteByIdTest() {
		ResponseEntity<Void> resp = departmentController.deleteById(3);
		assertNotNull(resp);
	}
}
