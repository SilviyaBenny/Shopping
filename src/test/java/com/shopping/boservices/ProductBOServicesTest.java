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
import com.shopping.botodao.mapper.ProductBOtoRepositoryDAOMapper;
import com.shopping.daotobo.mapper.ProductRepositoryDAOtoBOMapper;
import com.shopping.repository.ProductRepository;
import com.shopping.repository.dao.ProductDAO;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ProductBOServicesTest {
	@InjectMocks
	ProductBOServices productBoServices;

	@Mock
	ProductRepository productRepository;
	@Mock
	ProductBOtoRepositoryDAOMapper daoMapper;
	@Mock
	ProductRepositoryDAOtoBOMapper daoToBoMapper;
	@Mock
	ProductBOtoResponseJsonMapper respJsonMapper;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void createTest() {

		ProductDAO productDAO = new ProductDAO();
		ProductBO productBO = new ProductBO();
		productBO.setId(1);
		productBO.setName("Pen");
		productBO.setQuantity(100);
		productBO.setPrice(500);
		productBO.setSku("00B");
		productBO.setDepartmentId(30);
		when(productRepository.create(Mockito.<ProductDAO>any())).thenReturn(productDAO);
		when(daoMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productDAO);
		when(daoToBoMapper.mapObject(Mockito.<ProductDAO>any())).thenReturn(productBO);

		ProductBO bo = productBoServices.create(productBO);
		assertNotNull(bo);
	}

	@Test
	public void getByIdTest() {
		ProductDAO productDAO = new ProductDAO();
		ProductBO productBO = new ProductBO();
		productBO.setId(1);
		productBO.setName("Pen");
		productBO.setQuantity(200);
		productBO.setPrice(5000);
		productBO.setSku("00B");
		productBO.setDepartmentId(30);
		when(productRepository.getById(Mockito.anyInt())).thenReturn(productDAO);
		when(daoToBoMapper.mapObject(Mockito.<ProductDAO>any())).thenReturn(productBO);
		ProductBO bo = productBoServices.getById(1);
		assertNotNull(bo);

	}

	@Test
	public void getAllTest() {
		List<ProductDAO> productDAOlist = new ArrayList<>();
		ProductDAO productDAO = new ProductDAO();
		productDAO.setId(1);
		productDAO.setName("Pen");
		productDAO.setQuantity(100);
		productDAO.setPrice(200);
		productDAO.setSku("00C");
		productDAO.setDepartmentId(40);

		productDAOlist.add(productDAO);

		ProductBO productBO = new ProductBO();
		productBO.setId(1);
		productBO.setName("Pen");
		productBO.setQuantity(200);
		productBO.setPrice(5000);
		productBO.setSku("00B");
		productBO.setDepartmentId(30);
		when(productRepository.getAll()).thenReturn(productDAOlist);
		when(daoToBoMapper.mapObject(Mockito.<ProductDAO>any())).thenReturn(productBO);
		List<ProductBO> bo = productBoServices.getAll();
		assertNotNull(bo);

	}

	@Test
	public void updateTest() {

		ProductDAO productDAO = new ProductDAO();
		ProductBO productBO = new ProductBO();
		productBO.setId(1);
		productBO.setName("Pen");
		productBO.setQuantity(200);
		productBO.setPrice(5000);
		productBO.setSku("00B");
		productBO.setDepartmentId(30);
		when(productRepository.update(Mockito.<ProductDAO>any(), Mockito.anyInt())).thenReturn(productDAO);
		when(daoMapper.mapObject(Mockito.<ProductBO>any())).thenReturn(productDAO);
		when(daoToBoMapper.mapObject(Mockito.<ProductDAO>any())).thenReturn(productBO);

		ProductBO bo = productBoServices.update(productBO, 1);
		assertNotNull(bo);
	}

	@Test
	public void deleteById() {
		when(productRepository.deleteById(Mockito.anyInt())).thenReturn(1);
		int bo = productBoServices.deleteById(1);
		assertNotNull(bo);
	}
}
