package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
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
		Date date = new Date();
		DepartmentResponseJson departmentResponseJson = new DepartmentResponseJson();
		departmentResponseJson.setDepartmentName("Home");
		departmentResponseJson.setDescription("Home Appliances");
		departmentResponseJson.setCreatedBy("Jill");
		departmentResponseJson.setCreatedDate(date);
		departmentResponseJson.setModifiedBy("Jill");
		departmentResponseJson.setModifiedDate(date);
		
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
		Date date = new Date();
		DepartmentResponseJson departmentResponseJson = new DepartmentResponseJson();
		departmentResponseJson.setDepartmentName("Home");
		departmentResponseJson.setDescription("Home Appliances");
		departmentResponseJson.setCreatedBy("Jill");
		departmentResponseJson.setCreatedDate(date);
		departmentResponseJson.setModifiedBy("Jill");
		departmentResponseJson.setModifiedDate(date);
		
		
		List<DepartmentBO> departmentBOList = new ArrayList<>();
		DepartmentBO departmentBO = new DepartmentBO();
		departmentBO.setDepartmentName("Home");
		departmentBO.setDescription("Home Appliances");
		departmentResponseJson.setCreatedBy("Jill");
		departmentResponseJson.setCreatedDate(date);
		departmentResponseJson.setModifiedBy("Jill");
		departmentResponseJson.setModifiedDate(date);
		
		departmentBOList.add(departmentBO);
		
		when(departmentBOServices.getAll()).thenReturn(departmentBOList);
		when(botoResponseMapper.mapObject(Mockito.<DepartmentBO>any())).thenReturn(departmentResponseJson);
		ResponseEntity<List<DepartmentResponseJson>> resp = departmentController.getAll();
		assertNotNull(resp);
	}
	@Test
	public void getByIdTest() {
		Date date = new Date();
		DepartmentResponseJson departmentResponseJson = new DepartmentResponseJson();
		departmentResponseJson.setDepartmentName("Home");
		departmentResponseJson.setDescription("Home Appliances");
		departmentResponseJson.setCreatedBy("Jill");
		departmentResponseJson.setCreatedDate(date);
		departmentResponseJson.setModifiedBy("Jill");
		departmentResponseJson.setModifiedDate(date);
		
		
		DepartmentBO departmentBO = new DepartmentBO();
		when(departmentBOServices.getById(Mockito.<String>any())).thenReturn(departmentBO);
		when(botoResponseMapper.mapObject(Mockito.<DepartmentBO>any())).thenReturn(departmentResponseJson);
		ResponseEntity<DepartmentResponseJson> resp = departmentController.getById("f470b26f-d1a0-461a-b84a-ad8504a2a685");
		assertNotNull(resp);
	}
	@Test
	public void updateTest() {
		Date date = new Date();
		DepartmentResponseJson departmentResponseJson = new DepartmentResponseJson();
		departmentResponseJson.setDepartmentName("Home");
		departmentResponseJson.setDescription("Home Appliances");
		departmentResponseJson.setCreatedBy("Jill");
		departmentResponseJson.setCreatedDate(date);
		departmentResponseJson.setModifiedBy("Jill");
		departmentResponseJson.setModifiedDate(date);
		
		DepartmentBO departmentBO = new DepartmentBO();
		DepartmentRequestJson departmentRequestJson = new DepartmentRequestJson();
		when(departmentBOServices.update(Mockito.<DepartmentBO>any(),Mockito.<String>any())).thenReturn(departmentBO);
		when(requestJsonToBOMapper.mapObject(Mockito.<DepartmentRequestJson>any())).thenReturn(departmentBO);
		when(botoResponseMapper.mapObject(Mockito.<DepartmentBO>any())).thenReturn(departmentResponseJson);
		
		ResponseEntity<DepartmentResponseJson> resp = departmentController.update("f470b26f-d1a0-461a-b84a-ad8504a2a685",departmentRequestJson);
		assertNotNull(resp);
	}
	@Test
	public void deleteByIdTest() {
		
		when(departmentBOServices.deleteById(Mockito.<String>any())).thenReturn(1);
		ResponseEntity<Void> resp = departmentController.deleteById("f470b26f-d1a0-461a-b84a-ad8504a2a685");
		assertNotNull(resp);
	}
}
