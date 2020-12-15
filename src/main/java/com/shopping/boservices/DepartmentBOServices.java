package com.shopping.boservices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.shopping.bo.DepartmentBO;
import com.shopping.botodto.mapper.DepartmentBOtoDTOMapper;
import com.shopping.config.EntityRequstContext;
import com.shopping.controller.DepartmentController;
import com.shopping.dtotobo.mapper.DepartmentDTOtoBOMapper;
import com.shopping.error.ShoppingError;
import com.shopping.exception.DatabaseException;
import com.shopping.exception.ErrorCode;
import com.shopping.exception.ErrorType;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.repository.DepartmentDAO;
import com.shopping.repository.dto.DepartmentDTO;

@Component
public class DepartmentBOServices implements IDepartmentBOServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private DepartmentDAO departmentDAO;
	@Autowired
	private DepartmentBOtoDTOMapper boTodtoMapper;
	@Autowired
	private DepartmentDTOtoBOMapper dtoToboMapper;
	@Autowired
	private Provider<EntityRequstContext> entityRequstContextProvider;

	public DepartmentBO create(DepartmentBO bo) {
		LOGGER.info("Incoming Request:" + bo);
		DepartmentDTO dto = boTodtoMapper.mapObject(bo);
		DepartmentDTO respDTO = departmentDAO.create(dto);
		DepartmentBO respBO = dtoToboMapper.mapObject(respDTO);
		LOGGER.info("Outgoing Response:" + respBO);
		return respBO;
	}

	public List<DepartmentBO> getAll() {
		LOGGER.info("Incoming Request:");
		List<DepartmentDTO> dtoList = departmentDAO.getAll();
		List<DepartmentBO> boList = new ArrayList<>();
		for (DepartmentDTO departmentDTO : dtoList) {
			DepartmentBO respBO = dtoToboMapper.mapObject(departmentDTO);
			boList.add(respBO);
		}
		LOGGER.info("Outgoing Response:" + boList);
		return boList;
	}

	public DepartmentBO getById(int id) {
		LOGGER.info("Incoming Request:" + id);
		try {
			DepartmentDTO respDTO = departmentDAO.getById(id);
			DepartmentBO respBO = dtoToboMapper.mapObject(respDTO);
			LOGGER.info("Outgoing response:" + respBO);
			return respBO;
		} catch (EmptyResultDataAccessException e) {

			EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Object not found"));
			throw new ItemNotFoundException(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"Item with id " + id + "not found");
		}
	}

	public DepartmentBO update(DepartmentBO departmentBO, int id) {
		LOGGER.info("Incoming Request:" + id);
		try {
		DepartmentDTO dto = boTodtoMapper.mapObject(departmentBO);
		DepartmentDTO respDTO = departmentDAO.update(dto, id);
		DepartmentBO respBO = dtoToboMapper.mapObject(respDTO);
		LOGGER.info("Outgoing response:" + respBO);
		return respBO;
		}catch (DatabaseException e) {

			EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,"Object not found"));
			throw new ItemNotFoundException(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"Item with id " + id + "not found");
		}
	}

	public int deleteById(int id) {
		LOGGER.info("Incoming Request:" + id);
		int numberofRecords = departmentDAO.deleteById(id);
		LOGGER.info("Outgoing response:" + numberofRecords);
		return numberofRecords;
	}
}
