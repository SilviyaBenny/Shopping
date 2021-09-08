package com.shopping.boservices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.bo.ProductBO;
import com.shopping.config.EntityRequstContext;
import com.shopping.controller.ProductController;
import com.shopping.error.ShoppingError;
import com.shopping.exception.DatabaseException;
import com.shopping.exception.ErrorCode;
import com.shopping.exception.ErrorType;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.mapper.botodto.ProductBOtoDTOMapper;
import com.shopping.mapper.dtotobo.ProductDTOtoBOMapper;
import com.shopping.repository.ProductDAO;
import com.shopping.repository.dto.ProductDTO;
/**
 * 
 * Product BOService class
 *
 */
@Component
@Transactional
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

	@Override
	public ProductBO create(ProductBO bo) {
		LOGGER.info("Incoming request:" + bo);
		ProductDTO dto = boTodtoMapper.mapObject(bo);
		ProductDTO respDTO = productDAO.create(dto);
		ProductBO respBO = dtoToboMapper.mapObject(respDTO);
		LOGGER.info("Outgoing response:" + respBO);
		return respBO;
	}

	@Override
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

	@Override
	public ProductBO getById(int id) {
		LOGGER.info("Incoming request:" + id);
		try {
			ProductDTO productDTO = productDAO.getById(id);
			ProductBO respBO = dtoToboMapper.mapObject(productDTO);
			LOGGER.info("Outgoing response:" + respBO);
			return respBO;
		} catch (EmptyResultDataAccessException e) {

			EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
			entityRequstContext.addError(
					new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION, "Object not found"));
			throw new ItemNotFoundException(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"Item with id " + id + " not found");
		}
	}

	@Override
	public ProductBO update(ProductBO bo, int id) {
		LOGGER.info("Incoming request:" + id);
		try {
			ProductDTO dto = boTodtoMapper.mapObject(bo);
			ProductDTO respDTO = productDAO.update(dto, id);
			ProductBO respBO = dtoToboMapper.mapObject(respDTO);
			LOGGER.info("Outugoing response:" + respBO);
			return respBO;
		} catch (DatabaseException e) {

			EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
			entityRequstContext.addError(
					new ShoppingError(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION, "Object not found"));
			throw new ItemNotFoundException(ErrorCode.SHOPPING_VALIDATION_100, ErrorType.VALIDATION,
					"Item with id " + id + " not found");
		}
	}

	@Override
	public int deleteById(int id) {
		LOGGER.info("Incoming request:" + id);
		int numberofRecords = productDAO.deleteById(id);
		LOGGER.info("Outgoing response:" + numberofRecords);
		return numberofRecords;

	}
}
