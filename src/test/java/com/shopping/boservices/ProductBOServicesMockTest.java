package com.shopping.boservices;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.shopping.bo.ProductBO;
import com.shopping.mapper.botodto.ProductBOtoDTOMapper;
import com.shopping.mapper.botoresponse.ProductBOtoResponseJsonMapper;
import com.shopping.mapper.dtotobo.ProductDTOtoBOMapper;
import com.shopping.repository.ProductDAO;
import com.shopping.repository.dto.ProductDTO;

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
		
		Date date = new Date();
		ProductDTO productDTO = new ProductDTO();
		ProductBO productBO = new ProductBO();
		productBO.setId(2);
		productBO.setName("Pen");
		productBO.setQuantity(100);
		productBO.setPrice(500);
		productBO.setSku("00B");
		productBO.setDepartmentId(2);
		productBO.setDescription("Stationary");
		productBO.setCreatedBy("Jill");
		productBO.setCreatedDate(date);
		productBO.setModifiedBy("Jill");
		productBO.setModifiedDate(date);
		when(productDAO.create(Mockito.<ProductDTO>any())).thenReturn(productDTO);
		when(boTodtoMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productDTO);
		when(dtoToboMapper.mapObject(Mockito.<ProductDTO>any())).thenReturn(productBO);

		ProductBO bo = productBoServices.create(productBO);
		assertNotNull(bo);
	}

	@Test
	public void getByIdTest() {
		
		Date date = new Date();
		ProductDTO productDTO = new ProductDTO();
		ProductBO productBO = new ProductBO();
		productBO.setId(2);
		productBO.setName("Pen");
		productBO.setQuantity(200);
		productBO.setPrice(5000);
		productBO.setSku("00B");
		productBO.setDepartmentId(2);
		productBO.setDescription("Stationary");
		productBO.setCreatedBy("Jill");
		productBO.setCreatedDate(date);
		productBO.setModifiedBy("Jill");
		productBO.setModifiedDate(date);
		when(productDAO.getById(Mockito.anyInt())).thenReturn(productDTO);
		when(dtoToboMapper.mapObject(Mockito.<ProductDTO>any())).thenReturn(productBO);
		ProductBO bo = productBoServices.getById(2);
		assertNotNull(bo);

	}

	@Test
	public void getAllTest() {
		
		Date date = new Date();
		List<ProductDTO> productDTOlist = new ArrayList<>();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(1);
		productDTO.setName("Pen");
		productDTO.setQuantity(100);
		productDTO.setPrice(200);
		productDTO.setSku("00C");
		productDTO.setDepartmentId(2);
		productDTO.setDescription("Stationary");
		productDTO.setCreatedBy("Jill");
		productDTO.setCreatedDate(date);
		productDTO.setModifiedBy("Jill");
		productDTO.setModifiedDate(date);

		productDTOlist.add(productDTO);

		ProductBO productBO = new ProductBO();
		productBO.setId(1);
		productBO.setName("Pen");
		productBO.setQuantity(200);
		productBO.setPrice(5000);
		productBO.setSku("00B");
		productBO.setDepartmentId(2);
		productBO.setDescription("Stationary");
		productBO.setCreatedBy("Jill");
		productBO.setCreatedDate(date);
		productBO.setModifiedBy("Jill");
		productBO.setModifiedDate(date);
		when(productDAO.getAll()).thenReturn(productDTOlist);
		when(dtoToboMapper.mapObject(Mockito.<ProductDTO>any())).thenReturn(productBO);
		List<ProductBO> bo = productBoServices.getAll();
		assertNotNull(bo);

	}

	@Test
	public void updateTest() {
		
		Date date = new Date();
		ProductDTO productDTO = new ProductDTO();
		ProductBO productBO = new ProductBO();
		productBO.setId(2);
		productBO.setName("Pen");
		productBO.setQuantity(200);
		productBO.setPrice(500);
		productBO.setSku("00B");
		productBO.setDepartmentId(2);
		productBO.setDescription("Stationary");
		productBO.setCreatedBy("Jill");
		productBO.setCreatedDate(date);
		productBO.setModifiedBy("Jill");
		productBO.setModifiedDate(date);
		when(productDAO.update(Mockito.<ProductDTO>any(), Mockito.anyInt())).thenReturn(productDTO);
		when(boTodtoMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productDTO);
		when(dtoToboMapper.mapObject(Mockito.<ProductDTO>any())).thenReturn(productBO);

		ProductBO bo = productBoServices.update(productBO, 2);
		assertNotNull(bo);   
	}

	@Test
	public void deleteById() {
		when(productDAO.deleteById(Mockito.anyInt())).thenReturn(1);
		int bo= productBoServices.deleteById(2);
		assertNotNull(bo);
	}

}
