package com.shopping.boservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.bo.ProductBO;
import com.shopping.botodao.mapper.ProductBOtoRepositoryDAOMapper;
import com.shopping.daotobo.mapper.ProductRepositoryDAOtoBOMapper;
import com.shopping.repository.ProductRepository;
import com.shopping.repository.dao.ProductDAO;

@Component
public class ProductBOServices {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	ProductBOtoRepositoryDAOMapper daoMapper;
	@Autowired
	ProductRepositoryDAOtoBOMapper daoToBoMapper;

	public ProductBO create(ProductBO bo) {
		
		ProductDAO dao = daoMapper.mapObject(bo);
		ProductDAO respDao = productRepository.create(dao);
		ProductBO respBO = daoToBoMapper.mapObject(respDao);
		return respBO;
	}

	public List<ProductBO> getAll() {

		List<ProductDAO> productDAOList = productRepository.getAll();
		List<ProductBO> boList = new ArrayList<>();
		for (ProductDAO productDAO : productDAOList) {
			ProductBO respBO = daoToBoMapper.mapObject(productDAO);
			boList.add(respBO);
		}
		return boList;
	}

	public ProductBO getById(int id) {

		ProductDAO productDAO = productRepository.getById(id);
		ProductBO respBO = daoToBoMapper.mapObject(productDAO);
		return respBO;
	}

	public ProductBO update(ProductBO bo, int id) {

		ProductDAO dao = daoMapper.mapObject(bo);
		ProductDAO respDao = productRepository.update(dao, id);
		ProductBO respBO = daoToBoMapper.mapObject(respDao);
		return respBO;
	}

	public int deleteById(int id) {
		return productRepository.deleteById(id);
	}
}
