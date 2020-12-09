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

import com.shopping.BOtoResponse.mapper.ProductBOtoResponseJsonMapper;
import com.shopping.bo.ProductBO;
import com.shopping.boservices.ProductBOServices;
import com.shopping.controller.ProductController;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.requesttobomapper.ProductRequestJsonToBOMapper;
import com.shopping.responsejson.ProductResponseJson;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerMockTest  {

	@InjectMocks
	ProductController productController;
	@Mock
	ProductBOServices productBoService;
	@Mock
	ProductRequestJsonToBOMapper jsontoBO;
	@Mock
	ProductBOtoResponseJsonMapper respJsonMapper;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createTest() {
		ProductResponseJson productResponseJson = new ProductResponseJson();
		productResponseJson.setId(2);
		productResponseJson.setName("Pen");
		productResponseJson.setQuantity(100);
		productResponseJson.setPrice(50);
		productResponseJson.setSku("00B");
		productResponseJson.setDepartmentId(20);

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

		ProductResponseJson productResponseJson = new ProductResponseJson();
		productResponseJson.setId(2);
		productResponseJson.setName("Book");
		productResponseJson.setQuantity(10);
		productResponseJson.setPrice(20);
		productResponseJson.setSku("00A");
		productResponseJson.setDepartmentId(10);

		ProductBO productBO = new ProductBO();
		when(productBoService.getById(Mockito.anyInt())).thenReturn(productBO);
		when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<ProductResponseJson> resp = productController.getById(1);
		assertNotNull(resp);
	}

	@Test
	public void getAllTest() {

	ProductResponseJson productResponseJson = new ProductResponseJson();
	
		productResponseJson.setId(1);
		productResponseJson.setName("Book");
		productResponseJson.setQuantity(200);
		productResponseJson.setPrice(100);
		productResponseJson.setSku("00A");
		productResponseJson.setDepartmentId(20);

		List<ProductBO> productBOList = new ArrayList<>();
		ProductBO productBO = new ProductBO();
		productBO.setId(1);
		productBO.setName("Book");
		productBO.setQuantity(200);
		productBO.setPrice(100);
		productBO.setSku("00A");
		productBO.setDepartmentId(10);
		productBOList.add(productBO);
		
		when(productBoService.getAll()).thenReturn(productBOList);
		when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<List<ProductResponseJson>> resp = productController.getAll();
		assertNotNull(resp);
	}

	@Test
	public void updateTest() {
		int id = 0;
		ProductResponseJson productResponseJson = new ProductResponseJson();
		productResponseJson.setId(3);
		productResponseJson.setName("Pen");
		productResponseJson.setQuantity(100);
		productResponseJson.setPrice(5000);
		productResponseJson.setSku("00B");
		productResponseJson.setDepartmentId(20);

		ProductBO productBO = new ProductBO();
		ProductRequestJson productRequestJson = new ProductRequestJson();
		when(productBoService.update(Mockito.<ProductBO>any(), Mockito.anyInt())).thenReturn(productBO);
		when(jsontoBO.mapObject(Mockito.<ProductRequestJson>any())).thenReturn(productBO);
			when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<ProductResponseJson> resp = productController.update(id, productRequestJson);
		assertNotNull(resp);
	}

	@Test
	public void deleteByIdTest() {

		when(productBoService.deleteById(Mockito.anyInt())).thenReturn(1);

		ResponseEntity<Void> resp = productController.deleteById(1);
		assertNotNull(resp);
	}

}
