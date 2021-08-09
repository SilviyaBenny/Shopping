package com.shopping.boservices;

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

import com.shopping.bo.DepartmentBO;
import com.shopping.mapper.botodto.DepartmentBOtoDTOMapper;
import com.shopping.mapper.dtotobo.DepartmentDTOtoBOMapper;
import com.shopping.repository.DepartmentDAO;
import com.shopping.repository.dto.DepartmentDTO;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentBOServicesMockTest {
	@InjectMocks
	private DepartmentBOServices departmentBOServices;
	@Mock
	private DepartmentDAO departmentDAO;
	@Mock
	private DepartmentBOtoDTOMapper boTodtoMapper;
	@Mock
	private DepartmentDTOtoBOMapper dtoToboMapper;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void createTest() {

		Date date = new Date();
		DepartmentBO departmentBO = new DepartmentBO();
		departmentBO.setId(2);
		departmentBO.setDepartmentName("Home");
		departmentBO.setDescription("Home Appliances");
		departmentBO.setCreatedBy("Mike");
		departmentBO.setCreatedDate(date);
		departmentBO.setModifiedBy("Mike");
		departmentBO.setModifiedDate(date);
		DepartmentDTO departmentDTO = new DepartmentDTO();
		when(departmentDAO.create(Mockito.<DepartmentDTO>any())).thenReturn(departmentDTO);
		when(boTodtoMapper.mapObject(Mockito.<DepartmentBO>any())).thenReturn(departmentDTO);
		when(dtoToboMapper.mapObject(Mockito.<DepartmentDTO>any())).thenReturn(departmentBO);

		DepartmentBO bo = departmentBOServices.create(departmentBO);
		assertNotNull(bo);
	}

	@Test
	public void getByIdTest() {
		Date date = new Date();
		DepartmentBO departmentBO = new DepartmentBO();
		departmentBO.setId(2);
		departmentBO.setDepartmentName("Home");
		departmentBO.setDescription("Home Appliances");
		departmentBO.setCreatedBy("Mike");
		departmentBO.setCreatedDate(date);
		departmentBO.setModifiedBy("Mike");
		departmentBO.setModifiedDate(date);
		DepartmentDTO departmentDTO = new DepartmentDTO();
		when(departmentDAO.getById(Mockito.anyInt())).thenReturn(departmentDTO);
		when(dtoToboMapper.mapObject(Mockito.<DepartmentDTO>any())).thenReturn(departmentBO);

		DepartmentBO bo = departmentBOServices.getById(2);
		assertNotNull(bo);
	}

	@Test
	public void getAllTest() {
		Date date = new Date();
		List<DepartmentDTO> departmentDTOList = new ArrayList<>();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setId(2);
		departmentDTO.setDepartmentName("Home");
		departmentDTO.setDescription("Home Appliances");
		departmentDTO.setCreatedBy("Mike");
		departmentDTO.setCreatedDate(date);
		departmentDTO.setModifiedBy("Mike");
		departmentDTO.setModifiedDate(date);
		departmentDTOList.add(departmentDTO);

		DepartmentBO departmentBO = new DepartmentBO();
		departmentBO.setId(2);
		departmentBO.setDepartmentName("Home");
		departmentBO.setDescription("Home Appliances");
		departmentBO.setCreatedBy("Mike");
		departmentBO.setCreatedDate(date);
		departmentBO.setModifiedBy("Mike");
		departmentBO.setModifiedDate(date);

		when(departmentDAO.getAll()).thenReturn(departmentDTOList);
		when(dtoToboMapper.mapObject(Mockito.<DepartmentDTO>any())).thenReturn(departmentBO);

		List<DepartmentBO> boList = departmentBOServices.getAll();
		assertNotNull(boList);
	}

	@Test
	public void upadteTest() {
		Date date = new Date();
		DepartmentBO departmentBO = new DepartmentBO();
		departmentBO.setId(2);
		departmentBO.setDepartmentName("Home");
		departmentBO.setDescription("Home Appliances");
		departmentBO.setCreatedBy("Mike");
		departmentBO.setCreatedDate(date);
		departmentBO.setModifiedBy("Mike");
		departmentBO.setModifiedDate(date);
		DepartmentDTO departmentDTO = new DepartmentDTO();
		when(departmentDAO.update(Mockito.<DepartmentDTO>any(), Mockito.anyInt())).thenReturn(departmentDTO);
		when(boTodtoMapper.mapObject(Mockito.<DepartmentBO>any())).thenReturn(departmentDTO);
		when(dtoToboMapper.mapObject(Mockito.<DepartmentDTO>any())).thenReturn(departmentBO);

		DepartmentBO bo = departmentBOServices.update(departmentBO, 2);
		assertNotNull(bo);
	}

	@Test
	public void deleteByIdTest() {
		when(departmentDAO.deleteById(Mockito.anyInt())).thenReturn(1);
		int departmentBO = departmentBOServices.deleteById(2);
		assertNotNull(departmentBO);
	}

}
