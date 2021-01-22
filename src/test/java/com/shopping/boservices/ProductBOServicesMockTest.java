package com.shopping.boservices;

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

import com.shopping.BOtoResponse.mapper.ProductBOtoResponseJsonMapper;
import com.shopping.bo.ProductBO;
import com.shopping.botodto.mapper.ProductBOtoDTOMapper;
import com.shopping.dtotobo.mapper.ProductDTOtoBOMapper;
import com.shopping.repository.ProductDAO;
import com.shopping.repository.dto.ProductDTO;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ProductBOServicesMockTest {
	@InjectMocks
	ProductBOServices productBoServices;

	@Mock
	ProductDAO productDAO;
	@Mock
	ProductBOtoDTOMapper boTodtoMapper;
	@Mock
	ProductDTOtoBOMapper dtoToboMapper;
	@Mock
	ProductBOtoResponseJsonMapper respJsonMapper;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void createTest() {

		ProductDTO productDTO = new ProductDTO();
		ProductBO productBO = new ProductBO();
		productBO.setName("Pen");
		productBO.setQuantity(100);
		productBO.setPrice(500);
		productBO.setSku("00B");
		productBO.setDepartmentId(30);
		when(productDAO.create(Mockito.<ProductDTO>any())).thenReturn(productDTO);
		when(boTodtoMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productDTO);
		when(dtoToboMapper.mapObject(Mockito.<ProductDTO>any())).thenReturn(productBO);

		ProductBO bo = productBoServices.create(productBO);
		assertNotNull(bo);
	}

	@Test
	public void getByIdTest() {
		ProductDTO productDTO = new ProductDTO();
		ProductBO productBO = new ProductBO();
		productBO.setName("Pen");
		productBO.setQuantity(200);
		productBO.setPrice(5000);
		productBO.setSku("00B");
		productBO.setDepartmentId(30);
		when(productDAO.getById(Mockito.<String>any())).thenReturn(productDTO);
		when(dtoToboMapper.mapObject(Mockito.<ProductDTO>any())).thenReturn(productBO);
		ProductBO bo = productBoServices.getById("ff3e2ace-d188-4183-bdfc-5fb73a17d3d2");
		assertNotNull(bo);

	}

	@Test
	public void getAllTest() {
		List<ProductDTO> productDTOlist = new ArrayList<>();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("Pen");
		productDTO.setQuantity(100);
		productDTO.setPrice(200);
		productDTO.setSku("00C");
		productDTO.setDepartmentId(40);

		productDTOlist.add(productDTO);

		ProductBO productBO = new ProductBO();
		productBO.setName("Pen");
		productBO.setQuantity(200);
		productBO.setPrice(5000);
		productBO.setSku("00B");
		productBO.setDepartmentId(30);
		when(productDAO.getAll()).thenReturn(productDTOlist);
		when(dtoToboMapper.mapObject(Mockito.<ProductDTO>any())).thenReturn(productBO);
		List<ProductBO> bo = productBoServices.getAll();
		assertNotNull(bo);

	}

	@Test
	public void updateTest() {

		ProductDTO productDTO = new ProductDTO();
		ProductBO productBO = new ProductBO();
		productBO.setName("Pen");
		productBO.setQuantity(200);
		productBO.setPrice(5000);
		productBO.setSku("00B");
		productBO.setDepartmentId(30);
		when(productDAO.update(Mockito.<ProductDTO>any(), Mockito.<String>any())).thenReturn(productDTO);
		when(boTodtoMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productDTO);
		when(dtoToboMapper.mapObject(Mockito.<ProductDTO>any())).thenReturn(productBO);

		ProductBO bo = productBoServices.update(productBO, "ff3e2ace-d188-4183-bdfc-5fb73a17d3d2");
		assertNotNull(bo);   
	}

	@Test
	public void deleteById() {
		when(productDAO.deleteById(Mockito.<String>any())).thenReturn(1);
		int bo= productBoServices.deleteById("ff3e2ace-d188-4183-bdfc-5fb73a17d3d2");
		assertNotNull(bo);
	}
}
