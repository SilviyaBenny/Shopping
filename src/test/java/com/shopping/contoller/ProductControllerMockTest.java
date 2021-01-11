package com.shopping.contoller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import com.shopping.BOtoResponse.mapper.ProductBOtoResponseJsonMapper;
import com.shopping.bo.ProductBO;
import com.shopping.boservices.ProductBOServices;
import com.shopping.controller.ProductController;
import com.shopping.controller.validator.ProductRequestValidator;
import com.shopping.requestjson.ProductRequestJson;
import com.shopping.requesttobomapper.ProductRequestJsonToBOMapper;
import com.shopping.responsejson.ProductResponseJson;
@Ignore
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
		ProductResponseJson productResponseJson = new ProductResponseJson();
		//productResponseJson.setRecordId(2);
		productResponseJson.setName("Pen");
		productResponseJson.setQuantity(100);
		productResponseJson.setPrice(50);
		productResponseJson.setSku("00B");
		productResponseJson.setDepartmentId(20);
		productResponseJson.setDescription("Stationery Items");

		ProductBO productBO = new ProductBO();
		//productBO.setDepartmentId(1);
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
		productResponseJson.setName("Book");
		productResponseJson.setQuantity(10);
		productResponseJson.setPrice(20);
		productResponseJson.setSku("00A");
		productResponseJson.setDepartmentId(10);
		productResponseJson.setDescription("Stationery Items");

		ProductBO productBO = new ProductBO();
		when(productBoService.getById(Mockito.<String>any())).thenReturn(productBO);
		when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<ProductResponseJson> resp = productController.getById("ff3e2ace-d188-4183-bdfc-5fb73a17d3d2");
		assertNotNull(resp);
	}

	@Test
	public void getAllTest() {

		ProductResponseJson productResponseJson = new ProductResponseJson();
		productResponseJson.setName("Book");
		productResponseJson.setQuantity(200);
		productResponseJson.setPrice(100);
		productResponseJson.setSku("00A");
		productResponseJson.setDepartmentId(20);
		productResponseJson.setDescription("Stationery Items");

		List<ProductBO> productBOList = new ArrayList<>();
		ProductBO productBO = new ProductBO();
		productBO.setName("Book");
		productBO.setQuantity(200);
		productBO.setPrice(100);
		productBO.setSku("00A");
		productBO.setDepartmentId(10);
		productBO.setDescription("Stationery Items");
		productBOList.add(productBO);

		when(productBoService.getAll()).thenReturn(productBOList);
		when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<List<ProductResponseJson>> resp = productController.getAll();
		assertNotNull(resp);
	}

	@Test
	public void updateTest() {
		ProductResponseJson productResponseJson = new ProductResponseJson();
		productResponseJson.setName("Pen");
		productResponseJson.setQuantity(100);
		productResponseJson.setPrice(5000);
		productResponseJson.setSku("00B");
		productResponseJson.setDepartmentId(20);
		productResponseJson.setDescription("Stationery Items");

		ProductBO productBO = new ProductBO();
		ProductRequestJson productRequestJson = new ProductRequestJson();
		when(productBoService.update(Mockito.<ProductBO>any(), Mockito.<String>any())).thenReturn(productBO);
		when(jsontoBO.mapObject(Mockito.<ProductRequestJson>any())).thenReturn(productBO);
		when(respJsonMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productResponseJson);

		ResponseEntity<ProductResponseJson> resp = productController.update("ff3e2ace-d188-4183-bdfc-5fb73a17d3d2", productRequestJson);
		assertNotNull(resp);
	}

	@Test
	public void deleteByIdTest() {

		when(productBoService.deleteById(Mockito.<String>any())).thenReturn(1);

		ResponseEntity<Void> resp = productController.deleteById("ff3e2ace-d188-4183-bdfc-5fb73a17d3d2");
		assertNotNull(resp);
	}

}
