package com.shopping.boservices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.shopping.bo.ProductBO;
import com.shopping.botodto.mapper.ProductBOtoDTOMapper;
import com.shopping.config.EntityRequstContext;
import com.shopping.controller.ProductController;
import com.shopping.dtotobo.mapper.ProductDTOtoBOMapper;
import com.shopping.error.ShoppingError;
import com.shopping.exception.DatabaseException;
import com.shopping.exception.ErrorCode;
import com.shopping.exception.ErrorType;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.repository.ProductDAO;
import com.shopping.repository.dto.ProductDTO;

@Component
public class ProductBOServices implements IProductBOServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ProductBOtoDTOMapper boTodtoMapper;
	@Autowired
	private ProductDTOtoBOMapper dtoToboMapper;

	@Autowired
	private Provider<EntityRequstContext> entityRequstContextProvider;

	public ProductBO create(ProductBO bo) {
		LOGGER.info("Incoming request:" + bo);
		ProductDTO dto = boTodtoMapper.mapObject(bo);
		ProductDTO respDTO = productDAO.create(dto);
		ProductBO respBO = dtoToboMapper.mapObject(respDTO);
		LOGGER.info("Outgoing response:" + respBO);
		return respBO;
	}

	public List<ProductBO> getAll() {
		LOGGER.info("Incoming request");
		List<ProductDTO> productDTOList = productDAO.getAll();
		List<ProductBO> boList = new ArrayList<>();
		for (ProductDTO productDTO : productDTOList) {
			ProductBO respBO = dtoToboMapper.mapObject(productDTO);
			boList.add(respBO);
		}
		LOGGER.info("Outgoing response:" + boList);
		return boList;
	}

	public ProductBO getById(String recordId) {
		LOGGER.info("Incoming request:" + recordId);
		try {
			ProductDTO productDTO = productDAO.getById(recordId);
			ProductBO respBO = dtoToboMapper.mapObject(productDTO);
			LOGGER.info("Outgoing response:" + respBO);
			return respBO;
		} catch (EmptyResultDataAccessException e) {

			EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION, "Object not found"));
			throw new ItemNotFoundException(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"Item with id " + recordId + " not found");
		}
	}

	public ProductBO update(ProductBO bo, String recordId) {
		LOGGER.info("Incoming request:" + recordId);
		try {
		ProductDTO dto = boTodtoMapper.mapObject(bo);
		ProductDTO respDTO = productDAO.update(dto, recordId);
		ProductBO respBO = dtoToboMapper.mapObject(respDTO);
		LOGGER.info("Outugoing response:" + respBO);
		return respBO;
		}catch (DatabaseException e) {

			EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
			entityRequstContext.addError(new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION, "Object not found"));
			throw new ItemNotFoundException(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"Item with id " + recordId + " not found");
		}
	}

	public int deleteById(String recordId) {
		LOGGER.info("Incoming request:" + recordId);
		int numberofRecords = productDAO.deleteById(recordId);
		LOGGER.info("Outgoing response:" + numberofRecords);
		return numberofRecords;
		
	}
}
