package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.shopping.bo.ProductBO;
import com.shopping.boservices.ProductBOServices;
import com.shopping.controller.ProductController;
import com.shopping.controller.validator.ProductRequestValidator;
import com.shopping.mapper.botoresponse.ProductBOtoResponseJsonMapper;
import com.shopping.mapper.requesttobo.ProductRequestJsonToBOMapper;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.responsejson.ProductResponseJson;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerMockTest {

	@InjectMocks
	ProductController productController;
	@Mock
	ProductBOServices productBoService;
	@Mock
	ProductRequestJsonToBOMapper jsontoBO;
	@Mock
	ProductBOtoResponseJsonMapper respJsonMapper;
	@Mock
	ProductRequestValidator validator;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createTest() {
		Date date = new Date();
		ProductResponseJson productResponseJson = new ProductResponseJson();
		productResponseJson.setId(2);
		productResponseJson.setName("Pen");
		productResponseJson.setQuantity(100);
		productResponseJson.setPrice(50);
		productResponseJson.setSku("00B");
		productResponseJson.setDepartmentId(2);
		productResponseJson.setDescription("Stationery Items");
		productResponseJson.setCreatedBy("Jill");
		productResponseJson.setCreatedDate(date);
		productResponseJson.setModifiedBy("Jill");
		productResponseJson.setModifiedDate(date);

		ProductBO productBO = new ProductBO();
		ProductRequestJson productRequestJson = new ProductRequestJson();
		when(productBoService.create(Mockito.<ProductBO>any())).thenReturn(productBO);
		when(jsontoBO.mapObject(Mockito.<ProductRequestJson>any())).thenReturn(productBO);
		when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<ProductResponseJson> resp = productController.create(productRequestJson);
		assertNotNull(resp);

	}

	@Test
	public void getByIdTest() {

		Date date = new Date();
		ProductResponseJson productResponseJson = new ProductResponseJson();
		productResponseJson.setId(2);
		productResponseJson.setName("Book");
		productResponseJson.setQuantity(10);
		productResponseJson.setPrice(20);
		productResponseJson.setSku("00A");
		productResponseJson.setDepartmentId(2);
		productResponseJson.setDescription("Stationery Items");
		productResponseJson.setCreatedBy("Jill");
		productResponseJson.setCreatedDate(date);
		productResponseJson.setModifiedBy("Jill");
		productResponseJson.setModifiedDate(date);

		ProductBO productBO = new ProductBO();
		when(productBoService.getById(Mockito.anyInt())).thenReturn(productBO);
		when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<ProductResponseJson> resp = productController.getById(2);
		assertNotNull(resp);
	}

	@Test
	public void getAllTest() {

		Date date = new Date();
		ProductResponseJson productResponseJson = new ProductResponseJson();
		productResponseJson.setId(2);
		productResponseJson.setName("Book");
		productResponseJson.setQuantity(200);
		productResponseJson.setPrice(100);
		productResponseJson.setSku("00A");
		productResponseJson.setDepartmentId(2);
		productResponseJson.setDescription("Stationery Items");
		productResponseJson.setCreatedBy("Jill");
		productResponseJson.setCreatedDate(date);
		productResponseJson.setModifiedBy("Jill");
		productResponseJson.setModifiedDate(date);

		List<ProductBO> productBOList = new ArrayList<>();
		ProductBO productBO = new ProductBO();
		//productBO.setId(2);
		productBO.setName("Book");
		productBO.setQuantity(200);
		productBO.setPrice(100);
		productBO.setSku("00A");
		productBO.setDepartmentId(2);
		productBO.setDescription("Stationery Items");
		productBO.setCreatedBy("Jill");
		productBO.setCreatedDate(date);
		productBO.setModifiedBy("Jill");
		productBO.setModifiedDate(date);
		
		productBOList.add(productBO);

		when(productBoService.getAll()).thenReturn(productBOList);
		when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<List<ProductResponseJson>> resp = productController.getAll();
		assertNotNull(resp);
	}

	@Test
	public void updateTest() {
		
		Date date = new Date();
		ProductResponseJson productResponseJson = new ProductResponseJson();
		productResponseJson.setId(2);
		productResponseJson.setName("Pen");
		productResponseJson.setQuantity(100);
		productResponseJson.setPrice(5000);
		productResponseJson.setSku("00B");
		productResponseJson.setDepartmentId(2);
		productResponseJson.setDescription("Stationery Items");
		productResponseJson.setModifiedBy("Jill");
		productResponseJson.setModifiedDate(date);
		
		
		ProductBO productBO = new ProductBO();
		ProductRequestJson productRequestJson = new ProductRequestJson();
		when(productBoService.update(Mockito.<ProductBO>any(), Mockito.anyInt())).thenReturn(productBO);
		when(jsontoBO.mapObject(Mockito.<ProductRequestJson>any())).thenReturn(productBO);
		when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<ProductResponseJson> resp = productController.update(2, productRequestJson);
		assertNotNull(resp);
	}

	@Test
	public void deleteByIdTest() {

		when(productBoService.deleteById(Mockito.anyInt())).thenReturn(1);

		ResponseEntity<Void> resp = productController.deleteById(2);
		assertNotNull(resp);
	}

}
