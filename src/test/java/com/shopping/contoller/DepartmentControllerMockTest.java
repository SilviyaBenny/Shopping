package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.shopping.BOtoResponse.mapper.DepartmentBOtoResponseJsonMapper;
import com.shopping.bo.DepartmentBO;
import com.shopping.boservices.DepartmentBOServices;
import com.shopping.controller.DepartmentController;
import com.shopping.controller.validator.DepartmentRequestValidator;
import com.shopping.requestjson.DepartmentRequestJson;
import com.shopping.requesttobomapper.DepartmentRequestJsonToBOMapper;
import com.shopping.responsejson.DepartmentResponseJson;
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class DepartmentControllerMockTest {

	@InjectMocks
	DepartmentController departmentController;
	@Mock
	private DepartmentBOServices departmentBOServices;
	@Mock
	private DepartmentRequestJsonToBOMapper requestJsonToBOMapper;
	@Mock
	private DepartmentBOtoResponseJsonMapper botoResponseMapper;
	@Mock
	private DepartmentRequestValidator validator;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void createTest() {
		DepartmentResponseJson departmentResponseJson = new DepartmentResponseJson();
		departmentResponseJson.setId(2);
		departmentResponseJson.setDepartmentName("Home");
		departmentResponseJson.setDescription("Home Appliances");
		
		DepartmentBO departmentBO = new DepartmentBO();
		DepartmentRequestJson departmentRequestJson = new DepartmentRequestJson();
		when(departmentBOServices.create(Mockito.<DepartmentBO>any())).thenReturn(departmentBO);
		when(requestJsonToBOMapper.mapObject(Mockito.<DepartmentRequestJson>any())).thenReturn(departmentBO);
		when(botoResponseMapper.mapObject(Mockito.<DepartmentBO>any())).thenReturn(departmentResponseJson);
		
		ResponseEntity<DepartmentResponseJson> resp = departmentController.create(departmentRequestJson);
		assertNotNull(resp);
	}
	@Test
	public void getAllTest() {
		DepartmentResponseJson departmentResponseJson = new DepartmentResponseJson();
		departmentResponseJson.setId(1);
		departmentResponseJson.setDepartmentName("Home");
		departmentResponseJson.setDescription("Home Appliances");
		
		List<DepartmentBO> departmentBOList = new ArrayList<>();
		DepartmentBO departmentBO = new DepartmentBO();
		departmentBO.setId(1);
		departmentBO.setDepartmentName("Home");
		departmentBO.setDescription("Home Appliances");
		
		departmentBOList.add(departmentBO);
		
		when(departmentBOServices.getAll()).thenReturn(departmentBOList);
		when(botoResponseMapper.mapObject(Mockito.<DepartmentBO>any())).thenReturn(departmentResponseJson);
		ResponseEntity<List<DepartmentResponseJson>> resp = departmentController.getAll();
		assertNotNull(resp);
	}
	@Test
	public void getByIdTest() {
		DepartmentResponseJson departmentResponseJson = new DepartmentResponseJson();
		departmentResponseJson.setId(2);
		departmentResponseJson.setDepartmentName("Home");
		departmentResponseJson.setDescription("Home Appliances");
		
		DepartmentBO departmentBO = new DepartmentBO();
		when(departmentBOServices.getById(Mockito.anyInt())).thenReturn(departmentBO);
		when(botoResponseMapper.mapObject(Mockito.<DepartmentBO>any())).thenReturn(departmentResponseJson);
		ResponseEntity<DepartmentResponseJson> resp = departmentController.getById(1);
		assertNotNull(resp);
	}
	@Test
	public void updateTest() {
		int id=0;
		DepartmentResponseJson departmentResponseJson = new DepartmentResponseJson();
		departmentResponseJson.setId(2);
		departmentResponseJson.setDepartmentName("Home");
		departmentResponseJson.setDescription("Home Appliances");
		
		DepartmentBO departmentBO = new DepartmentBO();
		DepartmentRequestJson departmentRequestJson = new DepartmentRequestJson();
		when(departmentBOServices.update(Mockito.<DepartmentBO>any(),Mockito.anyInt())).thenReturn(departmentBO);
		when(requestJsonToBOMapper.mapObject(Mockito.<DepartmentRequestJson>any())).thenReturn(departmentBO);
		when(botoResponseMapper.mapObject(Mockito.<DepartmentBO>any())).thenReturn(departmentResponseJson);
		
		ResponseEntity<DepartmentResponseJson> resp = departmentController.update(id,departmentRequestJson);
		assertNotNull(resp);
	}
	@Test
	public void deleteByIdTest() {
		
		when(departmentBOServices.deleteById(Mockito.anyInt())).thenReturn(1);
		ResponseEntity<Void> resp = departmentController.deleteById(1);
		assertNotNull(resp);
	}
}
