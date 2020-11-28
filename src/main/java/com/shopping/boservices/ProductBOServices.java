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
import com.shopping.botodao.mapper.ProductBOtoRepositoryDAOMapper;
import com.shopping.config.EntityRequstContext;
import com.shopping.controller.ProductController;
import com.shopping.daotobo.mapper.ProductRepositoryDAOtoBOMapper;
import com.shopping.error.ShoppingError;
import com.shopping.exception.ItemNotFoundException;
import com.shopping.repository.ProductRepository;
import com.shopping.repository.dao.ProductDAO;

@Component
public class ProductBOServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductBOtoRepositoryDAOMapper daoMapper;
	@Autowired
	private ProductRepositoryDAOtoBOMapper daoToBoMapper;

	@Autowired
	private Provider<EntityRequstContext> entityRequstContextProvider;

	public ProductBO create(ProductBO bo) {
		LOGGER.info("Incoming request:" + bo);
		ProductDAO dao = daoMapper.mapObject(bo);
		ProductDAO respDao = productRepository.create(dao);
		ProductBO respBO = daoToBoMapper.mapObject(respDao);
		LOGGER.info("Outgoing response:" + respBO);
		return respBO;
	}

	public List<ProductBO> getAll() {
		LOGGER.info("Incoming request");
		List<ProductDAO> productDAOList = productRepository.getAll();
		List<ProductBO> boList = new ArrayList<>();
		for (ProductDAO productDAO : productDAOList) {
			ProductBO respBO = daoToBoMapper.mapObject(productDAO);
			boList.add(respBO);
		}
		LOGGER.info("Outgoing response:" + boList);
		return boList;
	}

	public ProductBO getById(int id) {
		LOGGER.info("Incoming request:" + id);
		try {
			ProductDAO productDAO = productRepository.getById(id);
			ProductBO respBO = daoToBoMapper.mapObject(productDAO);
			LOGGER.info("Outgoing response:" + respBO);
			return respBO;
		} catch (EmptyResultDataAccessException e) {

			EntityRequstContext entityRequstContext = entityRequstContextProvider.get();
			entityRequstContext.addError(new ShoppingError("1000", "Object not found", "VALIDATION"));
			entityRequstContext.addError(new ShoppingError("1001", "Length is out of control", "VALIDATION"));
			throw new ItemNotFoundException("1000", "VALIDATION", "Item with id " + id + "not found");
		}
	}

	public ProductBO update(ProductBO bo, int id) {
		LOGGER.info("Incoming request:" + id);
		ProductDAO dao = daoMapper.mapObject(bo);
		ProductDAO respDao = productRepository.update(dao, id);
		ProductBO respBO = daoToBoMapper.mapObject(respDao);
		LOGGER.info("Outugoing response:" + respBO);
		return respBO;
	}

	public int deleteById(int id) {
		LOGGER.info("Incoming request:" + id);
		LOGGER.info("Outgoing response:" + id);
		return productRepository.deleteById(id);
	}
}
